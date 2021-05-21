import React,{Component} from "react";
import PropTypes from "prop-types";
import { Link ,useParams} from "react-router-dom";
import {withRouter} from 'react-router';
//import { Button } from 'reactstrap';
class Counter extends Component {
  constructor(props) {
    super(props);
    console.log(props.counter)
    this.state = {
    };
  }


  render() {
  
    console.log(this.props)
     const {counter,count,text,getValue}= this.props
    // const count=this.props.count
    // let counter=this.props.counter
    return (
      <div style={{backgroundColor:"green"}}>
        register
        {counter}
        <input  type="text" value={text} onChange={(e)=>getValue(e,1)}/>
        <Link to="/">Home</Link>
        <button onClick={this.props.changeHeader}>click</button>
      </div>
    );
  }
}

export default Counter;
 //{//<Page />} 
