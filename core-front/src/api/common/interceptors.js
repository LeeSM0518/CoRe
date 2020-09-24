import store from '@/store/index.js';

export function setInterceptors(instance) {
  instance.interceptors.request.use(
    function(config) {
      return config;
    },
    function(error) {
      return Promise.reject(error);
    },
  );

  instance.interceptors.response.use(
    function(response) {
      // TODO response의 status가 403이고 message가 "Access Denied"이면 로그인 페이지로 이동 구현
      return response;
    },
    function(error) {
      return Promise.reject(error);
    },
  );
  return instance;
}
