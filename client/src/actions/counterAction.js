
import { GET_COUNT,GET_COUNT1,SAVE_COUNT,UPDATE_COUNT,DELETE_COUNT  } from "../types";
export const addCount=()=>{
    return{
        type:'ADD_COUNT',
    }
}

export const minusCount=()=>{
    return{
        type:"MINUS_COUNT",
    }

}

export const custom=(number)=>{
    return{
        type:"CUSTOM",
        payload:number
    }
}
export const setState=(obj)=>{
    return{
        type:"SET_STATE",
        payload:obj
    }
}
export const getCount = () => {
    console.log(" dispatch(getCount(number)")
    return { type: GET_COUNT }
}
export const getCount1= () => {
    console.log(" dispatch(getCount(number)")
    return { type: GET_COUNT1 }
}
export const saveCount= () => {
    console.log(" dispatch(getCount(number)")
    return { type: SAVE_COUNT }
}
export const updateCount= () => {
    console.log(" dispatch(getCount(number)")
    return { type: UPDATE_COUNT }
}
export const deleteCount= () => {
    console.log(" dispatch(getCount(number)")
    return { type: DELETE_COUNT }
}

