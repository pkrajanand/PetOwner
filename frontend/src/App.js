import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OwnerList from './OwnerList';

class App extends Component {

  render() {

    return (
      <Router>
        <Switch>
          {/* <Route path='/owners' exact={true} component={OwnerList}/> */}
          <OwnerList></OwnerList>
        </Switch>
      </Router>
    );
  }
}

export default App;
