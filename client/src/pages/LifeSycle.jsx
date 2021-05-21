import React, {Component} from 'react';

class LifeSycle extends Component {
    componentWillUnmount() {
        console.log("0")
    }

    constructor(props) {
        super(props);
        this.state = {
            count: 0,
            disabled:false
        }
        console.log(props)
    }

    componentDidMount() {
        console.log("3")
    }

    setCount =()=> {
        console.log("clicked ")
        this.setState({count: ++this.state.count})
    }
componentDidUpdate(prevProps, prevState, snapshot) {
        console.log(prevState,"prevState didUpdate")
        console.log(this.state,"this.state didUpdate")

    ///       0    0
    //if (!this.state.disabled&&this.state.count===10){
   //     this.setState({disabled:true})
   // }
}
componentWillUpdate(nextProps, nextState, nextContext) {
    console.log(nextState,"prevState WillUpdate")
    console.log(this.state,"this.state WillUpdate")
    if (!this.state.disabled&&nextState.count===10){
        this.setState({disabled:true})
    }

}
shouldComponentUpdate(nextProps, nextState, nextContext) {
        if (nextState.count>4){
            return  false
        }
        return true;
}

    render() {
       let {cars}=this.props
        console.log(new Date())
        console.log(this.state.disabled)
    cars=cars.map(el=>{
         el.count=0
         return el
     })
        return (
            <div>
                {cars.map(el =>{
                    return <div>
                        color:{el.color}
                        <br/>
                        count:{el.count}
                    </div>
                })}
                {this.state.count}
<button onClick={this.setCount} disabled={this.state.disabled}>Click</button>
            </div>
        );
    }
}

export default LifeSycle;