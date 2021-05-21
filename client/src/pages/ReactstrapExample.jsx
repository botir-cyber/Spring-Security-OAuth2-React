import React,{Component} from "react";

import { TabContent, TabPane, Nav, NavItem, NavLink, Card, CardTitle, CardText, Row, Col, Button } from 'reactstrap';

class ReactstrapExample extends Component {
  constructor(){
      super();
      this.state={
        activeTab:"1"
      }
  }
  render() {
 
    const toggle = tab => {
      if(this.state.activeTab !== tab) this.setState({activeTab:tab});
    }
    return (
      <div>
   <Button color="primary">primary</Button> <br/>
      <Button color="secondary">secondary</Button> <br/>
      <Button color="success">success</Button> <br/>
      <Button color="info">info</Button> <br/>
      <Button color="warning">warning</Button> <br/>
      <Button color="danger">danger</Button> <br/>
      <Button color="link">link</Button> <br/>
      <Nav tabs>
        <NavItem>
          <NavLink
            onClick={() => { toggle('1'); }}
          >
            Tab1
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={this.state.activeTab=="2"?"active1":""}
            onClick={() => { toggle('2'); }}
          >
            More Tabs
          </NavLink>
        </NavItem>
      </Nav>
      <TabContent activeTab={this.state.activeTab}>
        <TabPane tabId="1">
          <Row>
            <Col sm="12">
              <h4>Tab 1 Contents</h4>
            </Col>
          </Row>
        </TabPane>
        <TabPane tabId="2">
          <Row>
            <Col sm="6">
              <Card body>
                <CardTitle>Special Title Treatment</CardTitle>
                <CardText>With supporting text below as a natural lead-in to additional content.</CardText>
                <Button>Go somewhere</Button>
              </Card>
            </Col>
            <Col sm="6">
              <Card body>
                <CardTitle>Special Title Treatment</CardTitle>
                <CardText>With supporting text below as a natural lead-in to additional content.</CardText>
                <Button>Go somewhere</Button>
              </Card>
            </Col>
          </Row>
        </TabPane>
      </TabContent>
      </div>
    );
  }
}

export default ReactstrapExample;