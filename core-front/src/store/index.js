import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    apiLoading: false,
    apiError: false,
    apiErrorMessage: '',
    sessionId: '',
  },
  mutations: {
    invisibleApiError(state) {
      state.apiError = false;
    },
    visibleApiError(state, message) {
      state.apiError = true;
      state.apiErrorMessage = message;
    },
  },
  actions: {
    async CALL_API({ state, commit }, apiFunc) {
      try {
        state.apiLoading = true;
        return await apiFunc();
      } catch (error) {
        commit('visibleApiError', error.response.data.message);
        return error;
      } finally {
        state.apiLoading = false;
      }
    },
  },
  modules: {},
});
