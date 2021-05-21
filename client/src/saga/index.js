import { takeEvery, all } from 'redux-saga/effects';

import { GET_COUNT ,GET_COUNT1,DELETE_COUNT,UPDATE_COUNT,SAVE_COUNT} from '../types';
import { getCount ,getCount1,deleteCount,saveCount,updateCount} from './counterSaga';

export function* watchCounts() {
console.log("dddd")
    yield all([
        takeEvery(GET_COUNT, getCount),

    ]);
    yield all([
        takeEvery(GET_COUNT1, getCount1),

    ]);
    yield all([
        takeEvery(SAVE_COUNT, saveCount),

    ]);
    yield all([
        takeEvery(DELETE_COUNT, deleteCount),

    ]);
    yield all([
        takeEvery(UPDATE_COUNT, updateCount),

    ]);
}