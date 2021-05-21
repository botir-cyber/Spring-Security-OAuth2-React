import { compose, applyMiddleware, createStore } from 'redux';
import rootReducer from './reducer/index'
import {watchCounts} from "./saga/index"
import createSagaMiddleware from "redux-saga";

const sagaMiddleware = createSagaMiddleware();
export default  createStore(rootReducer, applyMiddleware(sagaMiddleware));
sagaMiddleware.run(watchCounts);
