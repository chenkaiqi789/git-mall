import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import AdminBrand from "@/views/admin/admin-brand.vue";
import AdminCategory from "@/views/admin/admin-category.vue";
import AdminGoods from "@/views/admin/admin-goods.vue";
import AdminTemplates from "@/views/admin/admin-templates.vue";
import AdminSpecification from "@/views/admin/admin-specification.vue";
import AdminContent from "@/views/content/admin-content.vue";
const routes: Array<RouteRecordRaw> = [
  {
    path: '/admin/brand',
    name: 'AdminBrand',
    component: AdminBrand
  },
  {
    path: '/admin/category',
    name: 'AdminCategory',
    component: AdminCategory
  },
  {
    path: '/admin/goods',
    name: 'AdminGoods',
    component: AdminGoods
  },
  {
    path: '/admin/templates',
    name: 'AdminTemplates',
    component: AdminTemplates
  },
  {
    path: '/admin/specification',
    name: 'AdminSpecification',
    component: AdminSpecification
  },
  {
    path: '/admin/content',
    name: 'AdminContent',
    component: AdminContent
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
