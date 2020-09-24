import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/login',
    name: 'Login',
    components: {
      header: null, // TODO 나중에 지우기
      body: () => import('@/views/LoginPage.vue'),
    },
  },
  {
    path: '/signup',
    name: 'SignUp',
    components: {
      body: () => import('@/views/SignUpPage.vue'),
    },
  },
  {
    path: '/password',
    name: 'ResetPassword',
    components: {
      body: () => import('@/views/PasswordResetPage.vue'),
    },
  },
  {
    path: '/',
    name: 'Home',
    components: {
      header: () => import('@/components/Header.vue'),
      body: () => import('@/views/HomePage.vue'),
    },
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
