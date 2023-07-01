import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store";

Vue.use(VueRouter)

const routes = [

  {
    path: '/teacher',
    name: 'Teacher',
    component: () => import('../views/teacher/teacher'),
    redirect: '/qualification',
    children: [
      {
        path: '/qualification',
        name: '运动员资质审核',
        component: () => import('../views/teacher/Qualification')
      },
      {
        path: '/record',
        name: '成绩登记',
        component: () => import('../views/teacher/Record')
      },
      {
        path: '/project',
        name: '项目信息',
        component: () => import('../views/teacher/Project')
      },
      {
        path: '/tperson',
        name: '教师信息',
        component: () => import('../views/teacher/TPerson')
      },
      {
        path: '/tpassword',
        name: '修改密码',
        component: () => import('../views/teacher/TPassword')
      },
    ]
  },
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/admin',
    component: () => import('../views/Manager'),
    redirect:"/Home",
    children: [
      {
        path:'/home',
        name:'首页',
        component:  () => import('../views/Home'),
      },
      {
        path:'/user',
        name:'教师管理',
        component:  () => import('../views/User'),
      },
      {
        path:'/student',
        name:'学生管理',
        component:  () => import('../views/Student'),
      },
    ]
  },
  {
    path: '/athlete',
    component: () => import('../views/athlete/athlete'),
    redirect: '/event',
    children: [
      {
        path: '/event',
        name: '赛事报名',
        component:()=> import('../views/athlete/SignEvent')
      },
      {
        path: '/grade',
        name: '成绩查看',
        component:()=>import('../views/athlete/Grade')
      },
      {
        path:"/person",
        name: '个人信息',
        component:()=>import("../views/athlete/Person")
      },
      {
        path:"/password",
        name: '修改密码 ',
        component:()=>import("../views/athlete/Password")
      },

    ]
  },

  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})
router.beforeEach((to,from,next) =>{
  localStorage.setItem("currentPathName",to.name) //设置当前路由名称,在Header组件种去使用
  store.commit('setPath')
  next()
})

export default router
