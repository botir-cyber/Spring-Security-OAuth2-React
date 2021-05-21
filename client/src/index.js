import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './pages/App';
import reportWebVitals from './reportWebVitals';
import LifeSycle from "./pages/LifeSycle";
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactstrapExample from './pages/ReactstrapExample';

import RoutePage from './pages/RoutePage';
import AuthExample from './pages/AuthExample';
import CustomAuth from './pages/CustomAuth';
import CodeBoxRegister from './pages/CodeBoxRegister';
import Login from './pages/Login';
import createStore from './store'
import {Provider} from 'react-redux'

import store from './store';
let cars=[{color:"red",count:2},{color:"green",count:1},{color:"blue",count:0}]
ReactDOM.render(
  <Provider store={store}>

    <App/>
  </Provider>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
