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
import Counter from "./Counter";
import MyComponent from "./MyComponent";
import ReactstrapExample from "./ReactstrapExample";
import Index from "./index";
import {GOOGLE_AUTH_URL} from "../types";
class App extends Component {
  constructor() {
    super();
    this.state = {
      userHeader: true,
    };
  }
  render() {
    const changeHeader = () => {
      this.setState({ userHeader: !this.state.userHeader });
    };

    return (
      <Router>
        <div>
          {this.state.userHeader ? (
            <nav>
              <ul>
                <li>
                  <Link to="/">Home</Link>
                </li>
                <li>
                  <Link to="/elasticPage/Aziz/1">About</Link>
                </li>
                <li>
                  <Link to="/users">Users</Link>
                </li>
              </ul>
            </nav>
          ) : (
            "siz user emassiz"
          )}

         <img src="./assets/logo512.png" height="100" width="100" alt=""/>   
           <Row>
               <MyComponent md={4}>
               <div>
                   <h4> 
                       col-4 
                   </h4>
               </div>
               </MyComponent>
               <MyComponent md={4}>
                 col-4
               </MyComponent>
               <MyComponent md={4}>
                 col-4
               </MyComponent>
               <MyComponent md={4}>
                 col-4
               </MyComponent>
               
               </Row>
            <a href={GOOGLE_AUTH_URL}>
                <button className="registerMainBtn2 mt-4">
                      <span className="">
                        <span className="d-flex align-items-center justify-content-center h-100">

                             <span className="">
                            <img src="/assets/icons/google.png" alt=""/>
                              </span>
                          <span className="ml-3 text">
                            Google orqali kirish
                          </span>
                        </span>
                      </span>
                </button>
            </a>
          {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
          <Switch>
            <Route
              path="/elasticPage/:id/:order"
              render={({ match }) => <Counter id={match} />}
            />

            <Route
              path="/oauth2/redirect"
              render={({ match }) => <Index id={match} />}
            />

            <Route path="/users">
              <ReactstrapExample />
            </Route>
            <Route path="/">
              <App1 />
            </Route>
          </Switch>
        </div>
      </Router>
    );
  }
}
export default App;
