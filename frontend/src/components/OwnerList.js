import React, { Component } from 'react';
import {Container, Table } from 'reactstrap';
import PetList from './PetList';

class OwnerList extends Component {
    constructor(props) {
        super(props);
        this.state = { owners: [], pets: [], isLoading: true };

        this.handleAddPetItem = this.handleAddPetItem.bind(this)
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        this.fetchOwners();

        this.fetchPets();            
    }

    fetchPets() {
        fetch('/pets')
            .then(response => response.json())
            .then(data => this.setState({ pets: data, isLoading: false }));
    }

    fetchOwners() {
        fetch('/owners')
            .then(response => response.json())
            .then(data => this.setState({ owners: data, isLoading: false }));
    }

    handleAddPetItem(pet){

        const owner = this.state.owners.find(owner => {
            return (owner.id === pet.ownerId);
        });
        owner.petIds.push(pet.id);

        const newLocal = this.state.pets.concat(pet);
        this.setState({
            pets: newLocal,
            isLoading: false
        });
    }

    render() {
        const { owners, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading Owners...</p>;
        }

        const ownerList = owners.map(owner => {
            const name = `${owner.firstName} ${owner.lastName}`;

            return  <tr key={owner.id}>
                        <td>{owner.id}</td>
                        <td>{name}</td>
                        <td>{owner.city}</td>
                        <td>
                                <Container fluid>
                                    <Table className="mt-4">
                                            <PetList pets={this.state.pets} petIds={owner.petIds} ownerId={owner.id}
                                                onAddPet={pet => this.handleAddPetItem(pet)}>
                                            </PetList>
                                    </Table>
                                </Container>
                        </td>
                    </tr>
        });

        return (
            <div>
                <Container fluid>
                    <h3>Pet Owners</h3>
                    <Table className="mt-4 owners">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>City</th>
                                <th>Pets</th>
                            </tr>
                        </thead>
                        <tbody>
                            {ownerList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default OwnerList;
