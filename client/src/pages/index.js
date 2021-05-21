import React, { Component } from 'react';

import { Redirect ,withRouter} from 'react-router-dom'
import {STORAGE_NAME} from "../types";
import {
    addCount,
    custom,
    deleteCount,
    getCount,
    getCount1,
    minusCount,
    saveCount,
    setState,
    updateCount
} from "../actions";

class Index extends Component {
// class OAuth2RedirectHandler extends Component {
    getUrlParameter(name) {
        console.log(name)
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        var results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    render() {
        console.log(this.props.location)
        const token = this.getUrlParameter('token');
        const error = this.getUrlParameter('error');

        if(token) {
            localStorage.setItem(STORAGE_NAME,"Bearer "+ token);
            return <Redirect to={{
                pathname: "/",
                state: { from: this.props.location }
            }}/>;
        } else {
            return <Redirect to={{
                pathname: "/login",
                state: {
                    from: this.props.location,
                    error: error
                }
            }}/>;
        }
    }
}
const mapStateToProps = state => {
        console.log(state)
        return {...state}
    }
;

const mapDispatchToProps = dispatch => ({
});
export default withRouter(Index);

