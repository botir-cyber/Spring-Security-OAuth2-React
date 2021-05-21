import React, {Component} from "react";
import {BrowserRouter as Router, Route, Switch,} from "react-router-dom";
import Cabinet from "./Cabinet";
import Counter from "./Counter";
import Header from "../components/Header";
import Register from "./Register";
import {connect} from 'react-redux'
import {
    addCount,
    custom,
    deleteCount,
    getCount,
    getCount1,
    minusCount,
    saveCount,
    updateCount
} from '../actions/counterAction'

class CustomAuth extends Component {
  constructor(){
      super()
      this.state={
        windowHeight: undefined,
        windowWidth: undefined,
          users:[
            {
                roles:["ADMIN","USER"],
                userName:"Aziz",
                password:"123456789"
            },
             {
                roles:["ADMIN"],
                userName:"Aziz1",
                password:"123456789"
            },
            {
                roles:["USER"],
                userName:"Aziz2",
                password:"123456789"
            },
            {
                userName:"Aziz3",
                password:"123456789"
            },
            {
                userName:"Aziz4",
                password:"123456789"
            },
            {
                userName:"Aziz5",
                password:"123456789"
            },
          ],
          user:undefined
      }
  }
  componentDidMount() {
      console.log(this.props)
      console.log(this.props.getCount1)
      this.props.getCount1()
   //   this.props.deleteCount()
   //   this.props.updateCount()
    //  this.props.saveCount()
  }

    isRegistered=(user)=>{
     let users= this.state.users.filter(el=>{return el.userName==user.userName})
     console.log(users)
     if (users.length>0) {
         this.setState({user:users[0]})
        return true
     }else{
         alert("Ro'yhatdan o'ting!!!")
         return false
     }
  }
  setCustomAuthState=(obj)=>this.setState(obj)
  render() {

    return (
      <Router>
        <div>
       <Header setCustomAuthState={this.setCustomAuthState} user={this.state.user}/>
<button onClick={this.props.minusCount}>-</button>{this.props.counter.count}<button onClick={this.props.addCount}>+</button>
     
     <input type="number" onChange={(e)=>{this.props.getCount(e.target.value)}}/>
       <Switch>
            <Route path="/ss">
              <Register isRegistered={this.isRegistered}/>
            </Route>
            <Route path="/cabinet">
              <Cabinet />
            </Route>
            <Route path="/admin">
              <Counter />
            </Route>
            <Route path="/register">
              <Counter />
            </Route>
            <Route path="/">
{this.state.windowWidth<576?"mobil":"desktop"}
            </Route>
      </Switch>
        </div>
      </Router>
    );
  }
}




const mapStateToProps = state => 
{  
  console.log(state)
  return {...state}}
;

const mapDispatchToProps = dispatch => ({
    addCount: () => dispatch(addCount()),
    minusCount: () => dispatch(minusCount()),
    custom: number => dispatch(custom(number)),
    getCount: number => dispatch(getCount(number)),
    getCount1: number => dispatch(getCount1(number)),
    deleteCount: number => dispatch(deleteCount(number)),
    updateCount: number => dispatch(updateCount(number)),
    saveCount: number => dispatch(saveCount(number)),
});


export default connect(mapStateToProps,mapDispatchToProps)(CustomAuth);