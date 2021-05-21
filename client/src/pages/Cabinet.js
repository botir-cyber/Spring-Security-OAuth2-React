import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useParams,
} from "react-router-dom";
import { Col, Row } from "reactstrap";
import App1 from "./App1";
import Counter from "./Counter";
import MyComponent from "./MyComponent";
import ReactstrapExample from "./ReactstrapExample";
class Cabinet extends Component {
constructor(){
  super();
  this.state={
    user:{
        name:"",
        surname:"",
        email:"",
        password:"",
     }
    
  }
}
componentDidMount(){
 // this.state.user=
 if (this.props.users) {
  let elements=this.props.users.filter(el=>el.name==this.props.name)
  console.log(this.props.users)
  if (elements.length>0) {
   this.setState({user:elements[0]})
  }
 }

  

}
  render() {
    

    return (
    <div>
      dddd
           <Row>
             <Col md={4}>
               <img src="" alt="" width="400" height="500"/>
             </Col>
             <Col md={8}>
              <h4>
                {this.state.user.name}
              </h4>
              <h4>
                {this.state.user.surname}
              </h4>
              <h4>
                {this.state.user.email}
              </h4>
              
             </Col>
           </Row>
    </div>
    );
  }
}
export default Cabinet;