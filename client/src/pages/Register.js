import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useParams,
} from "react-router-dom";

import { Button, Container, Row } from "reactstrap";
import App1 from "./App1";
import Counter from "./Counter";
import MyComponent from "./MyComponent";
import ReactstrapExample from "./ReactstrapExample";
import { AvForm, AvField } from "availity-reactstrap-validation";

class Register extends Component {
    constructor(){
        super();
        this.state={
            path:""
        }
    }
    handleValidSubmit=(event, values, errors)=> {

 
           let registered= this.props.isRegistered(values)
         //  if (registered) {
         //   this.setState({path:'/cabinet'})
         //   document.getElementById("changePage").click();
         //  }else{
         //   this.setState({path:'/register'})
         //   document.getElementById("changePage").click();
         //  }
          
     

      }
      componentDidMount(){
       
      }
    
  render() {
    return (
      <div>
       <Container>
       <AvForm
          onValidSubmit={this.handleValidSubmit}
          onInvalidSubmit={this.handleInvalidSubmit}
        >
          <AvField
            type="text"
            label="Username"
            name="userName"
            onChange={this.props.changeName}
            placeholder="Ism"
            validate={{
              required: { value: true, errorMessage: "Username yozilishi shart!" },
            }}
          />
        
          <AvField
            type="password"
            label="password"
            name="password"
            placeholder="password"
            validate={{
              required: { value: true, errorMessage: "password yozilishi shart!" },
            }}
          />
  

          <Button color="primary">Submit</Button>
        </AvForm>
        <Link to={this.state.path} id="changePage" style={{visibility:"hidden"}}></Link>
   </Container>    
          </div>
    );
  }
}

export default Register;
