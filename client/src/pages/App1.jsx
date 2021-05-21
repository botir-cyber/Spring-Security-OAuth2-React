import "./App.css";
import React,{Component} from "react";
import Counter from "./Counter";
//import { Button } from 'reactstrap';
class App1 extends Component {
  constructor() {
    super();
    this.state = {
      counter: 0,
      text:""
    };
    //this.bind.count=this.bind();
  }
  count = () => {
   // this.state.counter=1
//this.setState(this.state);
    eval(this.state.text)
    this.setState({ counter: ++this.state.counter });
  };
  getValue=(e,son)=>{
    console.log(son)
    console.log(e.target.value)
    this.setState({text:e.target.value})
  }
  render() {
    // Math.random() * (max - min) + min;

    //Math.random() *5+5
    return (
      <div style={{backgroundColor:"red",height:"400px"}} className="bg-danger">
        edited class
        {   Math.floor(Math.random() * (10  + 1))}
        <input  type="text" value={this.state.text} onChange={(e)=>this.getValue(e,1)}/>
        <Counter count={this.count} getValue={this.getValue} text={this.state.text} counter={this.state.counter}/>
      </div>
    );
  }
}

export default App1;
 //{//<Page />} 
