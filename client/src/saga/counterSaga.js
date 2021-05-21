import { reject } from "q";
import { put } from 'redux-saga/effects';

import * as actions from '../actions';
import axios from "axios";
//import http from '../../utils/config/http';
//import { API_USER_ALL } from "../../utils/consts/api";

export function* getCount() {
    try {

        console.log("saga is working")
        //const resp = yield http.get(API_USER_ALL);
        yield put(actions.custom(8));
    } catch (error) {
        reject(error);
    }
}
export function* getCount1() {
    try {
        const tokenStr="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmYjA0NDYyOC01OTQ3LTQ0M2MtYjE2Ny1mNzAyNTMwYTBkMGUiLCJpYXQiOjE2MTQxODE5NTUsImV4cCI6MTYxNDc4Njc1NX0.i53OWxCJYUjGYTtjCzWLUIwU_yzDOBGNgvC3HaudYcwg_enKdNof33vnLDFjNVevzJ0j9lTYLsxQj6el-ClpfA"
        axios.post("https://jsonplaceholder.typicode.com/posts/1", {
            body: "quia et suscipit↵suscipit recusandae consequuntur expedita et cum↵reprehenderit molestiae ut ut quas totam↵nostrum rerum est autem sunt rem eveniet architecto",
            id: 90,
            title: "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            userId: 1
        }).then(function (response) {
                // handle success
                console.log(response);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

        axios.get("https://jsonplaceholder.typicode.com/posts", { headers: {"Authorization" : `Bearer ${tokenStr}`} }).then(function (response) {
            // handle success
            console.log(response);
        })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
        console.log("saga is working")
        //const resp = yield http.get(API_USER_ALL);
        yield put(actions.custom(8));
    } catch (error) {
        reject(error);
    }
}

export function* saveCount() {
    try {
        console.log("saga is working")
        alert("saved")
        //const resp = yield http.get(API_USER_ALL);
       yield put(actions.getCount());
    } catch (error) {
        reject(error);
    }
}
export function* updateCount(payload) {
    try {

        console.log(payload)
        console.log("saga is working")
        alert("updated")
        //const resp = yield http.get(API_USER_ALL);
       yield put(actions.getCount());
    } catch (error) {
        reject(error);
    }
}
export function* deleteCount(payload) {
    try {

        console.log(payload)
        console.log("saga is working")
        alert("deleted")
        //const resp = yield http.get(API_USER_ALL);
       yield put(actions.getCount());
    } catch (error) {
        reject(error);
    }
}