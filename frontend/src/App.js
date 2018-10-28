import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OwnerList from './components/OwnerList';

class App extends Component {

  render() {

    return (
      <Router>
        <Switch>
          <OwnerList></OwnerList>
        </Switch>
      </Router>
    );
  }
}

export default App;
