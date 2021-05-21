import {Component} from 'react';
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
import {connect} from 'react-redux'
import {AvField, AvForm} from 'availity-reactstrap-validation';
import {Button, FormGroup} from 'reactstrap';
import Container from "reactstrap/es/Container";
import axios from "axios";

class CodeBoxRegister extends Component {
    constructor(props) {
        super(props);


        this.state = {};
    }

    handleSubmit = (event, errors, values) => {

        //if (values.password===values.rePassword&&errors.length===0){
        //http://10.10.10.211/api/auth/login
        //{phoneNumber:"998944155945"
        // password:"root123"}
        ///
        const config = {
            headers: {
                //'Content-Type': 'application/x-www-form-urlencoded',
                "Access-Control-Allow-Origin": "*",
                "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzZjIzNjM1Ni03NzY2LTQ5Y2MtODE0OC0xNDdiNDJlZTlmYjAiLCJpYXQiOjE2MTQzNTMwNzQsImV4cCI6MTYxNDk1Nzg3NH0.Et5TaN1DNw3l_YqLPwwmzFuVytkL_G78KdaICCdiNf3YLtilDWOY1ufR9EhASH3u-Zs8v4FbVpL3hNA6BhWGYA",
               // "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS"
                // {password: "root123"
                // phoneNumber: "+998944155945"}
            }
        };
        axios.post('http://localhost/api/auth/login',values)
            .then(function (response) {
                console.log(response);

            })
            .catch(function (error) {
                console.log(error);
            });
        //}else {
        // alert("xato")
        //   }

    }

    render() {
        return (
            <div>
                <Container>
                    Login
                    <AvForm onSubmit={this.handleSubmit}>
                        {/* With AvField */}

                        <AvField name="phoneNumber" label="Telefon raqam" required/>
                        {/*  <AvField name="firstName" label="Ism" required/>
                        <AvField name="lastName" label="Familiya" required/> */}
                        <AvField type="password" name="password" label="Password" required/>
                        {/* <AvField type="password" name="rePassword" label="Re-Password" required/> */}

                        <FormGroup>
                            <Button>Submit</Button>
                        </FormGroup>
                    </AvForm>
                </Container>
            </div>
        );
    }
}


const mapStateToProps = state => {
        console.log(state)
        return {...state}
    }
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
    setState: obj => dispatch(setState(obj)),
});


export default connect(mapStateToProps, mapDispatchToProps)(CodeBoxRegister);
