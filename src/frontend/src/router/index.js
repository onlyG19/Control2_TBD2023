import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: ()=> import('../views/Login.vue')
    },
    {
      path: '/task',
      name: 'task',
      component: ()=> import('../views/Task.vue')
    }
  ]
})

export default router
