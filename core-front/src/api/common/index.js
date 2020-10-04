import axios from 'axios';
import { setInterceptors } from './interceptors.js';

function createInstanceWithAuth(url) {
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}`,
    headers: {
      'Content-Type': 'application/json',
    },
    withCredentials: true,
  });
  return setInterceptors(instance);
}

export const accounts = createInstanceWithAuth('api/accounts');
export const hashtags = createInstanceWithAuth('api/hashtags');
export const feeds = createInstanceWithAuth('api/feeds');
