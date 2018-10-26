import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  state = {
    isLoading: true,
    owners: []
  };
  async componentDidMount() {

    const response = await fetch('/owners');
    const body = await response.json();
    this.setState({ owners: body, isLoading: false });
  }

  render() {

    const { owners, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">PetOwner 1.0</h1>
          <div className="App-intro">
            <h2>Owners</h2>

            {owners.map(owner =>
              <div>
                <div key={owner.id}>
                  {owner.id}
                  {owner.firstName} {owner.lastName}
                  {owner.city}
                  {owner.petIds}
                </div>
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
