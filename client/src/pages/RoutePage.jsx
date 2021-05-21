import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useParams,
} from "react-router-dom";
import { Row } from "reactstrap";
import App1 from "./App1";
import Cabinet from "./Cabinet";
import Counter from "./Counter";
import MyComponent from "./MyComponent";
import ReactstrapExample from "./ReactstrapExample";
import Register from "./Register";
class RoutePage extends Component {
  constructor() {
    super();
  
    this.state = {
     name:"",
     email:"",
     surname:"",
     password:"",
     users:JSON.parse(localStorage.getItem("users"))!=null?JSON.parse(localStorage.getItem("users")):[]
    };
  }
  
  changeName=(e)=>{
this.setState({name:e.target.value})
  }
  
  addUser = (user) => {
    this.state.users.push(user);
    this.setState({ users: this.state.users });
    localStorage.setItem("users",JSON.stringify(this.state.users))
  };
  render() {
    const changeHeader = () => {
      this.setState({ userHeader: !this.state.userHeader });
    };

    return (
      <Router>
        <div>
       
       <Switch>
            <Route
              path="/name/:name"
              render={({ match }) => <Cabinet name={match.params.name} users={this.state.users} />}
            />

            <Route path="/">
              <Register changeName={this.changeName}  addUser={this.addUser}/>
            </Route>
          </Switch>
        </div>
      </Router>
    );
  }
}
export default RoutePage;