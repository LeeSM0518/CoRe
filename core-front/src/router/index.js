import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/login',
    name: 'Login',
    components: {
      header: null, // TODO 나중에 지우기
      body: () => import('@/views/Login.vue'),
    },
  },
  {
    path: '/signup',
    name: 'SignUp',
    components: {
      body: () => import('@/views/SignUp.vue'),
    },
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
