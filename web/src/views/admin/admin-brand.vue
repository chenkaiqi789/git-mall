<template>
  <p>
    <a-button type="primary" @click="addBrand">添加</a-button>
    &nbsp;
    <a-button type="primary" @click="refresh">刷新</a-button>
  </p>
  <a-table :row-selection="rowSelection"
           :columns="columns"
           :data-source="brandList"
           :row-key="record=>record.id"
           :pagination="pagination"
           :loading="loading"
           @change="handerTableChang">
    <template #bodyCell="{ column, text }">
      <template v-if="column.dataIndex === 'name'">
        <a>{{ text }}</a>
      </template>
    </template>
    <template v-slot:action="{text,record}">
      <a-button type="primary" @click="editBrand(record)">编辑</a-button>
      <a-popconfirm
          title="确定删除吗?"
          ok-text="是"
          cancel-text="否"
          @confirm="delBrand(record.id)"
      >
        <a-button type="danger">删除</a-button>
      </a-popconfirm>
    </template>
  </a-table>

  <a-modal v-model:visible="modalVisiable"
           title="品牌编辑"
           @ok="handleModalOk"
           ok-text="确定"
           cancel-text="取消">
    <a-form
        :model="news"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
    >
      <a-form-item label="品牌名称">
        <a-input v-model:value="brand.name"/>
      </a-form-item>
      <a-form-item label="品牌首字母">
        <a-input v-model:value="brand.firstChar"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import type {TableProps, TableColumnType} from 'ant-design-vue';
import axios from "axios";
import {message} from "ant-design-vue";


export default defineComponent({
  name: 'AdminBrand',
  setup() {
    const brandList = ref();
    const brand = ref();
    const columns = [
      {
        title: '品牌id',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: '品牌名称',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '品牌首字母',
        dataIndex: 'firstChar',
        key: 'firstChar',
      },
      {
        title: '操作',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ];
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0
    });
    const loading = ref(false);
    const modalVisiable = ref(false);
    //名牌查询展示
    const brandQuery = (params: any) => {
      axios.get('http://localhost:8899/ms/findByTbBrand', {
        params: {
          page: params.page,
          size: params.size
        }
      }).then((response) => {
        loading.value = false;
        brandList.value = response.data.data.list;
        pagination.value.current = params.page;
        pagination.value.total = response.data.data.total;
        console.log("总页数" + response.data.data.total)
      })
    };
    //分页按钮
    const handerTableChang = (pagination: any) => {
      brandQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    };
    //打开编辑和添加的模态框
    const addBrand = () => {
      modalVisiable.value = true;
      brand.value = {} //初始化
    };
    //刷新页面
    const refresh = (pagination: any) => {
      brandQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    };
    //添加和修改
    const handleModalOk = () => {
      axios.post("http://localhost:8899/ms/saveBrand", brand.value).then((response) => {
        console.log("返回值" + response.data.code)
        if (response.data.code === 200) {
          modalVisiable.value = false;
          brandQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          })
        }
      })
    };
    //编辑
    const editBrand = (record: any) => {
      modalVisiable.value = true;
      brand.value = record;
    };
    //删除
    const delBrand = (id: number) => {
      axios.get('http://localhost:8899/ms/delBrand/' + id).then((response) => {
        if (response.data.code === 200) {
          modalVisiable.value = false;
          brandQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          })
        }
      })
    }
    //第一次加载
    onMounted(() => {
      brandQuery({
        page: 1,
        size: 5
      })
    });
    //交互接口
    return {
      brandList, //查询数据
      columns, //表格展示
      brandQuery, //查询方法
      pagination,//分页数据
      loading,
      handerTableChang, //分页按钮
      modalVisiable,  //模态框
      brand, //brand对象
      addBrand,//打开模态框
      editBrand,
      handleModalOk, //添加和修改方法
      delBrand,//删除
      refresh //刷新
    }


  }
});
</script>