import {Component} from 'react';
import Container from "reactstrap/es/Container";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {Button, Card, CardBody, CardSubtitle, CardText, CardTitle, FormGroup} from "reactstrap";
import axios from "axios";
import gen from "../services/index"

let url = "http://localhost/api"

class Login extends Component {
    constructor() {
        super();
        this.state = {
            isLogin: false, languages: [],
            fileDownloadUri: "",
            editedLanguageId: undefined,
            deletedId: undefined,
        }
    }

    componentDidMount() {
        if (localStorage.getItem("code-box-token")) {
            this.setState({isLogin: true})
            axios.defaults.headers.common['Authorization'] = localStorage.getItem("code-box-token");
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = "http://localhost:3000/"
            //     axios.defaults.headers.common['Access-Control-Allow-Credentials'] =true
            // let fun=  gen("GET /api/language")
            //  fun(null)
            axios.get(url + "/language").then(res => {
                console.log(res)

                if (res?.data?._embedded?.list) {
                    this.setState({languages: res.data._embedded.list})

                }

            })
        }
    }

    handleSubmit = (e, errors, values) => {

        axios.post(url + "/auth/login", values).then(res => {
            if (res.data) {
                localStorage.setItem("code-box-token", res.data.tokenType + " " + res.data.accessToken)
            }
        })

    }
    getSection = (id) => {
        axios.get(url + "/section/findByUri?id=" + id).then(res => {
            if (res.data) {
                localStorage.setItem("code-box-token", res.data.tokenType + " " + res.data.accessToken)
            }
        })
    }

    render() {
        const {isLogin, editedLanguageId, languages, deletedId} = this.state
        console.log(languages)
        const editLanguage = (language) => {
            if (editedLanguageId) {
                this.setState({editedLanguageId: undefined})

            } else {
                this.setState({editedLanguageId: language})
            }
        }

        const createLanguage = (language) => {
            if (editedLanguageId) {
                this.setState({editedLanguageId: undefined})

            } else {
                this.setState({
                    editedLanguageId: {
                        name: "",
                        description: ""
                    }
                })
            }
        }
        const deleteLanguage = (id) => {

            axios.delete(url + "/language/" + id).then(res => {

                axios.get(url + "/language").then(res => {
                    console.log(res)

                    if (res?.data?._embedded?.list) {
                        this.setState({languages: res.data._embedded.list})

                    }

                })


            })

        }
        const editLanguageRequest = (e, errors, values, id) => {

            values.path = id
            axios.put(url + "/language/" + id, values).then(res => {
                if (res.data) {
                    axios.get(url + "/language").then(res => {
                        console.log(res)

                        if (res?.data?._embedded?.list) {
                            this.setState({languages: res.data._embedded.list})

                        }

                    })
                }


            })
        }
        const createLanguageRequest = (e, errors, values) => {
            axios.post(url + "/language", values).then(res => {
                if (res.data) {
                    axios.get(url + "/language").then(res => {
                        console.log(res)

                        if (res?.data?._embedded?.list) {
                            this.setState({languages: res.data._embedded.list})

                        }

                    })
                }
            })
        }
        const upload = (e) => {
            console.log(e.target.files)
            let fun = gen("POST file")
            fun({
                payload: {
                    file: e.target.files,
                    fileUpload: true
                }

            }).then(res => {
                console.log(res)
                this.setState({fileDownloadUri: res.fileDownloadUri})
                console.log(this.state.fileDownloadUri)
            })

        }
        // file: e.target.files,
        //   fileUpload: true


        return (
            <div>
                <Container>

                    {isLogin ? <div className="bg-danger" style={{backgroundColor: "red"}}>
                        {languages.map((item, index) => {
                            return <Card>
                                <CardBody>
                                    <CardTitle tag="h5">{item.name}</CardTitle>
                                    <CardSubtitle tag="h6" className="mb-2 text-muted">{index}</CardSubtitle>
                                    <CardText>{item.description}
                                    </CardText>
                                    <Button onClick={() => editLanguage(item)}>Edit</Button>
                                    <Button onClick={() => createLanguage()}>New</Button>
                                    <Button onClick={() => deleteLanguage(item.id)}>Delete</Button>

                                    <Button onClick={() => this.getSection(item.name)}>Button</Button>
                                </CardBody>
                            </Card>

                        })}
                        {editedLanguageId ? <AvForm
                            onSubmit={(e, errors, values) => editedLanguageId.id ? editLanguageRequest(e, errors, values, editedLanguageId.id) : createLanguageRequest(e, errors, values)}>
                            {/* With AvField */}

                            <AvField name="name" label="name" defaultValue={editedLanguageId.name} required/>
                            {/*  <AvField name="firstName" label="Ism" required/>
                        <AvField name="lastName" label="Familiya" required/> */}
                            <AvField name="description" label="description" defaultValue={editedLanguageId.description}
                                     required/>
                            {/* <AvField type="password" name="rePassword" label="Re-Password" required/> */}

                            <FormGroup>
                                <Button>Submit</Button>
                            </FormGroup>
                        </AvForm> : ""}

                    </div> : <AvForm onSubmit={this.handleSubmit}>
                        {/* With AvField */}

                        <AvField name="phoneNumber" label="Telefon raqam" required/>
                        {/*  <AvField name="firstName" label="Ism" required/>
                        <AvField name="lastName" label="Familiya" required/> */}
                        <AvField type="password" name="password" label="Password" required/>
                        {/* <AvField type="password" name="rePassword" label="Re-Password" required/> */}

                        <FormGroup>
                            <Button>Submit</Button>
                        </FormGroup>
                    </AvForm>}
                    <input type="file" onChange={upload}/>
                    <img src={this.state.fileDownloadUri}/>
                </Container>
                <img src="http://localhost/api/file/96f126a0-15e4-4d83-a46d-d7322edf9058" alt=""/>
            </div>
        );
    }
}

export default Login;