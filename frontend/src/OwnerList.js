import React, { Component } from 'react';
import {Container, Table } from 'reactstrap';
import PetList from './PetList';

class OwnerList extends Component {
    constructor(props) {
        super(props);
        this.state = { owners: [], isLoading: true };
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('/owners')
            .then(response => response.json())
            .then(data => this.setState({ owners: data, isLoading: false }));
    }

    render() {
        const { owners, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading Owners...</p>;
        }

        const ownerList = owners.map(owner => {
            const name = `${owner.firstName} ${owner.lastName}`;

            return <tr key={owner.id}>
                <td>{owner.id}</td>
                <td>{name}</td>
                <td>{owner.city}</td>
                <td>
                    <div>
                        <Container fluid>
                            <Table className="mt-4">
                                <tbody>
                                    <PetList petIds={owner.petIds} ownerId={owner.id}></PetList>
                                </tbody>
                            </Table>
                        </Container>
                    </div>                                
                </td>
            </tr>
        });

        return (
            <div>
                <Container fluid>
                    <h3>Pet Owners</h3>
                    <Table className="mt-4">
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
