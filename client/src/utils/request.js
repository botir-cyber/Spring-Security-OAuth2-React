import axios from 'axios'
import {cloneDeep, isEmpty} from 'lodash'
import pathToRegexp from 'path-to-regexp'
// import {message} from 'antd'
// import {CANCEL_REQUEST_MESSAGE} from 'utils/constant'
import qs from 'qs'
let STORAGE_NAME="code-box-token"

const {CancelToken} = axios;
window.cancelRequest = new Map()

export default function request(options) {
  let {data, url, method = 'get'} = options

  let cloneData = cloneDeep(data);
console.log(data)

  try {
    let domain = ''
    const urlMatch = url.match(/[a-zA-z]+:\/\/[^/]*/)
    if (urlMatch) {

      [domain] = urlMatch
      url = url.slice(domain.length)
    }

    const match = pathToRegexp.parse(url)
    url = pathToRegexp.compile(url)(data)

    for (const item of match) {
      if (item instanceof Object && item.name in cloneData) {
        delete cloneData[item.name]
      }
    }
    url = domain + url
  } catch (e) {
  }
  data = data ? data : {path: ''}
  options.url =
    method.toLocaleLowerCase() === 'get' ?
      (data.path
        ? `${url}${isEmpty(cloneData) ? '' : '/'}${cloneData.path}`
        : `${url}${isEmpty(cloneData) ? '' : '?'}${qs.stringify(cloneData)}`)
      : url;
  if (method.toLocaleLowerCase() === 'delete')
    options.url =
      method.toLocaleLowerCase() === 'delete'
        ? `${url}${isEmpty(cloneData) ? '' : '/'}${cloneData.id}`
        : url;
  if (method.toLocaleLowerCase() === 'put' && cloneData.path)
    options.url = `${url}${isEmpty(cloneData) ? '' : '/'}${cloneData.path}`;
  if (method.toLocaleLowerCase() === 'patch' && cloneData.path)
    options.url = `${url}${isEmpty(cloneData) ? '' : '/'}${cloneData.path}`;
  options.cancelToken = new CancelToken(cancel => {
    window.cancelRequest.set(Symbol(Date.now()), {
      pathname: window.location.pathname,
      cancel,
    })
  })

  axios.defaults.headers.common['Authorization'] = localStorage.getItem(STORAGE_NAME);

  try {

    if (data.payload.fileUpload) {
      options.headers = {
        "Content-Type": "multipart/form-data"
      };
      const formData = new FormData();
      if (data.payload.file.length > 1) {
        Array.from(data.payload.file).forEach((file, i) => formData.append('files' + i, file));
      } else {
        formData.append('file', data.payload.file[0]);
      }
      delete data.payload.file;
      formData.append("type", data.payload.type);
      options.data = formData;
    }

    delete data.fileUpload;

  } catch (e) {

  }
   console.log(options)
options.url="http://localhost"+options.url
  return axios(options)
    .then(response => {

      const {statusText, status, data} = response
      let result = {}
      if (typeof data === 'object') {
        result = data
        if (Array.isArray(data)) {
          result.list = data
        }
      } else {
        result.data = data
      }

      return Promise.resolve({
        success: true,
        message: statusText,
        statusCode: status,
        ...result,
      })
    })
    .catch(error => {
      const {response, message} = error

      // if (String(message) === CANCEL_REQUEST_MESSAGE) {
      //   return {
      //     success: false,
      //   }
      // }

      let msg;
      let statusCode;

      if (response && response instanceof Object) {
        const {data, statusText} = response
        statusCode = response.status
        msg = data.message || statusText
      } else {
        statusCode = 600
        msg = error.message || 'Network Error'
      }

      /* eslint-disable */
      return Promise.resolve({
        success: false,
        statusCode,
        message: msg,
      })
    })
}
