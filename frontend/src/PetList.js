import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class PetList extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = { pets: [], isLoading: true };
        // this.add = this.add.bind(this);
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('/pets')
            .then(response => response.json())
            .then(data => this.setState({ pets: data, isLoading: false }));
    }

    render() {
        const { pets, isLoading } = this.state;
        const { petIds } = this.props;

        if (isLoading) {
            return <p>Loading Pets...</p>;
        }

        const petList = pets
            .filter(pet => petIds.find(pId => pId === pet.id) )
            .map (pet => {
                return <tr>
                        <td>{pet.id}</td>
                        <td>{pet.name}</td>
                        <td>{pet.birthday}</td>
                    </tr>
        });

        return (
            <div>
                <Container fluid>
                    <Table className="mt-4">
                    <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Birthday</th>
                            </tr>
                        </thead>
                        <tbody>
                            {petList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

PetList.propTypes = {
    petIds: PropTypes.array.isRequired,
};
  
export default PetList;
