import request from '../utils/request'
//import { apiPrefix } from 'utils/config'

import api from './api'

const gen = params => {
  let url = "http://localhost/api/" + params
  let method = 'GET'


  const paramsArray = params.split(' ')
  if (paramsArray.length === 2) {
    method = paramsArray[0]
    url = "http://localhost/api/" + paramsArray[1]


  }
  console.log(url);
  return function(data) {
    return request({
      url,
      data,
      method,
    })
  }
}

export  default  gen;
