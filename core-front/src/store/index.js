import Vue from 'vue';
import Vuex from 'vuex';
import { getMemberFromCookie } from '@/utils/cookies.js';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    apiLoading: false,
    apiError: false,
    apiErrorMessage: '',
    sessionId: '',
    memberName: getMemberFromCookie() || '',
  },
  getters: {
    getMemberName(state) {
      return state.memberName;
    },
  },
  mutations: {
    invisibleApiError(state) {
      state.apiError = false;
    },
    visibleApiError(state, message) {
      state.apiError = true;
      state.apiErrorMessage = message;
    },
    setMemberName(state, name) {
      state.memberName = name;
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
