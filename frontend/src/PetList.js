import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Container, Table } from 'reactstrap';
import PetItem from './PetItem';

class PetList extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = { pets: [], isLoading: true };
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('/pets')
            .then(response => response.json())
            .then(data => this.setState({ pets: data, isLoading: false }));
    }

    handleAddPetItem(pet, pets){
        var newArray = pets.slice();    
        newArray.push(pet);   
        this.setState({pets:newArray});
    }

    render() {
        const { pets, isLoading } = this.state;
        const { petIds, ownerId } = this.props;

        if (isLoading) {
            return <p>Loading Pets...</p>;
        }

        const petList = pets
            .filter(pet => petIds.find(pId => pId === pet.id) )
            .map (pet => {
                return <PetItem pet={pet} ownerId={ownerId} pets={pets}></PetItem>
        });

        petList.push(<PetItem ownerId={ownerId} onPetAdded={pet => this.handleAddPetItem(pet, pets)}></PetItem>);

        return (
            <div>
                <Container fluid>
                    <Table>
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
    ownerId: PropTypes.number
};
  
export default PetList;
