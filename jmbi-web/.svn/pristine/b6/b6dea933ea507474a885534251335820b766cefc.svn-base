let address = require('../../../config/address.config')
import axios from 'axios'

// https://github.com/mzabriskie/axios
axios.defaults.baseURL = address.SERVER_ADDRESS;
//axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let CancelToken = axios.CancelToken;
export let requestCancels = [];

// // Add a request interceptor
axios.interceptors.request.use(function (config) {
  // Do something before request is sent
  config.cancelToken = new CancelToken(function executor(c) {
    // An executor function receives a cancel function as a parameter
    requestCancels.push(c);
  })

  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

export function fetch(url) {
  return axios.get(url)
}
