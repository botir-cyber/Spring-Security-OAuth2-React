const initiailState = {
    count: 0,
    step:0,
    lastName:"unknown",
    firsName:"unknown",
}
export const counter = (state = initiailState, action) => {
    //action.payload  1-> 7   {type,payload}
    switch (action.type) {
        case 'ADD_COUNT':
            return {
                ...state,
                count: ++state.count,

            }
        case 'MINUS_COUNT':
            return {
                ...state,
                count: --state.count
            }
        case 'CUSTOM':
            return {
                ...state,
                count: action.payload
            }
        case 'SET_STATE':

            return {
                ...state,
                ...action.payload
            }
        default:
            return state
    }
}
