<template>
  <p>
    <a-button type="primary" @click="add">添加</a-button>
    &nbsp;
    <a-button type="primary" @click="refresh">刷新</a-button>
  </p>
  <a-table :row-selection="rowSelection"
           :columns="columns"
           :data-source="datasourceList"
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
      <a-button type="primary" @click="edit(record)">编辑</a-button>
      <a-popconfirm
          title="确定删除吗?"
          ok-text="是"
          cancel-text="否"
          @confirm="del(record.id)"
      >
        <a-button type="danger">删除</a-button>
      </a-popconfirm>
    </template>
  </a-table>

  <a-modal v-model:visible="modalVisiable"
           title="规格编辑"
           @ok="handleModalOk(specEntity.tbSpecification.id)"
           ok-text="确定"
           cancel-text="取消" width="560px">
    <a-form
        :model="specEntity"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
    >
      <a-form-item label="规格名称">
        <a-input v-model:value="specEntity.tbSpecification.specName"/>
      </a-form-item>
      <a-form-item label="规格选项">
        <a-button type="primary" @click="addTableRow">新增规格选项</a-button>
        <table id="tb01" width="340px">
          <thead>
          <tr>
            <td>规格选项</td>
            <td>排序</td>
            <td>操作</td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="pojo in specEntity.specificationOptionList" v-bind:key="pojo.id">
            <td>
              <a-input v-model:value="pojo.optionName"/>
            </td>
            <td>
              <a-input v-model:value="pojo.orders"/>
            </td>
            <td>
              <a-button type="danger" @click="deleTableRow($index)">删除</a-button>
            </td>
          </tr>
          </tbody>
        </table>
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";


export default defineComponent({
  name: 'AdminSpecification',
  setup() {
    const datasourceList = ref();
    const specification = ref();
    const columns = [
      {
        title: '规格id',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: '规格名称',
        dataIndex: 'specName',
        key: 'specName',
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
    const specEntity = ref<any>({
      specificationOptionList: [],
      tbSpecification: {}
    });
    const loading = ref(false);
    const modalVisiable = ref(false);
    //名牌查询展示
    const specificationQuery = (params: any) => {
      axios.get('http://localhost:8899/ms/findBySpecification', {
        params: {
          page: params.page,
          size: params.size
        }
      }).then((response) => {
        loading.value = false;
        datasourceList.value = response.data.data.list;
        pagination.value.current = params.page;
        pagination.value.total = response.data.data.total;
        console.log(response.data.data.total)
      })
    };
    //分页按钮
    const handerTableChang = (pagination: any) => {
      specificationQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    };
    //打开编辑和添加的模态框
    const add = () => {
      modalVisiable.value = true;
      specEntity.value.specificationOptionList = []; //初始化
      specEntity.value.tbSpecification = {}; //初始化
    };
    //刷新页面
    const refresh = (pagination: any) => {
      specificationQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    };
    //添加和修改的提交
    const handleModalOk = (id: number) => {
      if ("undefined" === typeof id) {
        //添加
        axios.post("http://localhost:8899/ms/saveSpecification", specEntity.value).then((response) => {
          console.log("返回值" + response.data.code)
          if (response.data.code === 200) {
            modalVisiable.value = false;
            specificationQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize
            })
          }
        })
      } else {
        //修改
        axios.post("http://localhost:8899/ms/updateSpecification", specEntity.value).then((response) => {
          console.log("返回值" + response.data.code)
          if (response.data.code === 200) {
            modalVisiable.value = false;
            specificationQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize
            })
          }
        })
      }
    };
    //编辑
    const edit = (record: any) => {
      modalVisiable.value = true;
      specEntity.value.specificationOptionList = []; //初始化
      specEntity.value.tbSpecification = {}; //初始化
      axios.get('http://localhost:8899/ms/findBySpecificationOption/' + record.id).then((response) => {
        specEntity.value.specificationOptionList = response.data.data;
        specEntity.value.tbSpecification = record;
      })
    };
    //删除
    const del = (id: number) => {
      axios.get('http://localhost:8899/ms/delSpecification/' + id).then((response) => {
        if (response.data.code === 200) {
          modalVisiable.value = false;
          specificationQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          })
        }
      })
    };
    //删除新行
    const deleTableRow = (index: number) => {
      specEntity.value.specificationOptionList.splice(index, 1);
    };
    //添加新行
    const addTableRow = () => {
      specEntity.value.specificationOptionList.push({});
    };
    //第一次加载
    onMounted(() => {
      specificationQuery({
        page: 1,
        size: 5
      })
    });
    //交互接口
    return {
      datasourceList, //查询数据
      columns, //表格展示
      specEntity,//展示规格及分类
      specificationQuery,
      pagination,//分页数据
      loading,
      handerTableChang, //分页按钮
      modalVisiable,  //模态框
      specification, //specification  对象
      add,//打开模态框
      edit,
      handleModalOk, //添加和修改方法
      del,//删除
      refresh,//刷新
      deleTableRow,
      addTableRow
    }
  }
});
</script>