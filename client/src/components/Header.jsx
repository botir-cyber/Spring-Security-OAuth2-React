import { render } from "@testing-library/react";
import React, { Component } from "react";
import {
    withRouter ,
  useLocation,
  
  Link
} from "react-router-dom";
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    NavbarText
  } from 'reactstrap';



class  Header extends Component {
   constructor(props){
       super();
       this.state={
      
        isOpen:false,
        path:"/"
       }
       
   }
   resize = () => {
    return window.innerWidth;
  }
  handleResize = () => this.props.setCustomAuthState({
    windowHeight: window.innerHeight,
    windowWidth: window.innerWidth
  });
  componentWillUnmount() {
    window.removeEventListener('resize', this.handleResize)
  }

 


   componentDidMount(){
    this.handleResize();
    window.addEventListener('resize', this.handleResize)
    window.addEventListener('scroll', this.listenToScroll)

    this.login(this.props.user)
   }
componentDidUpdate(prevProps, prevState, snapshot) {
  if (prevProps.location.pathname !== this.props.location.pathname) {
    window.scrollTo(0, 0);
  }
  
if (prevProps!==this.props&&this.props.user!=prevProps.user) {
    this.login(this.props.user)
}
}

    login = (user) => {
        if (!this.props.location.pathname.includes("admin")&&!this.props.location.pathname.includes("cabinet")) {
            
        }else{
            if (user) {
                if (user.roles.length>0) {
                    console.log(user.roles)
                    console.log(user.roles.includes("ADMIN"))
                    if (user.roles.includes("ADMIN")) {
                        this.props.history.push("/admin")
                    }else if(user.roles.includes("USER")){
                        this.props.history.push("/cabinet")
                    }
    
                }else{
                    this.props.history.push("/404")
                }
            }else{
             this.props.history.push("/ss")
       
            }
        }
       
        
      
    }
  render(){
   let {isOpen}=this.state

    const toggle = () => {this.setState({isOpen:!isOpen});}
  
   
    return (
      <div>
       <Navbar color="light" light expand="md">
        <NavbarBrand href="/">reactstrap</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="mr-auto" navbar>
            <NavItem>
              <NavLink href="/components/">Components</NavLink>
            </NavItem>
            <NavItem>
              <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                Options
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem>
                  Option 1
                </DropdownItem>
                <DropdownItem>
                  Option 2
                </DropdownItem>
                <DropdownItem divider />
                <DropdownItem>
                  Reset
                </DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <NavbarText>{}</NavbarText>
        </Collapse>
      </Navbar>
          <Link to={this.state.path} id="route"></Link>
      </div>
    );
  }
}
export  default withRouter(Header) ;