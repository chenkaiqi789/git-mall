<template>
  <a-layout id="components-layout-demo-top-side">
    <the-header/>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
            v-model:selectedKeys="selectedKeys2"
            v-model:openKeys="openKeys"
            mode="inline"
            :style="{ height: '100%', borderRight: 0 }"
        >
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                首页
              </span>
            </template>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <laptop-outlined/>
                商家管理
              </span>
            </template>
            <a-menu-item key="5">option5</a-menu-item>
            <a-menu-item key="6">option6</a-menu-item>
            <a-menu-item key="7">option7</a-menu-item>
            <a-menu-item key="8">option8</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <notification-outlined/>
                商品管理
              </span>
            </template>
            <a-menu-item key="admin/brand">
              <router-link to="/admin/brand">品牌管理</router-link>
            </a-menu-item>
            <a-menu-item key="admin/specification">
              <router-link to="/admin/specification">规格管理</router-link>
            </a-menu-item>
            <a-menu-item key="admin/templates">
              <router-link to="/admin/templates">模板管理</router-link>
            </a-menu-item>
            <a-menu-item key="admin/category">
              <router-link to="/admin/category">分类管理</router-link>
            </a-menu-item>
            <a-menu-item key="admin/goods">
              <router-link to="/admin/goods">商品审核</router-link>
            </a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub4">
            <template #title>
              <span>
                <user-outlined/>
                广告管理
              </span>
            </template>
            <a-menu-item key="admin/content">
              <router-link to="/admin/content">广告类别</router-link>
            </a-menu-item>
            <a-menu-item key="2">广告内容</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <!--切换页面-->
          <router-view/>
        </a-layout-content>
      </a-layout>
    </a-layout>
    <the-footer/>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import TheHeader from '@/components/this-header.vue';
import TheFooter from '@/components/this-footer.vue';

export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
    TheFooter
  }

});
</script>

<style>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>