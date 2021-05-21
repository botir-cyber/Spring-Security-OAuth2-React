import React,{Component} from "react";


class MyComponent extends Component {
    render(){
  return (
   <div className={`col-md-${this.props.md} `+`col-md-${this.props.sm} `}>
     {this.props.children}
   </div>  
  );
}
}
export default MyComponent;