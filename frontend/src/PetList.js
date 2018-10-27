import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Container, Table } from 'reactstrap';
import PetItem from './PetItem';

class PetList extends Component {
    constructor(props, context) {
        super(props, context);
    }

    handleAddPetItem(pet){
        this.props.onAddPet(pet);   
    }

    render() {
        const { pets, petIds, ownerId } = this.props;

        const petList = pets
            .filter(pet => petIds.find(pId => pId === pet.id) )
            .map (pet => {
                return <PetItem pet={pet} ownerId={ownerId} pets={pets}></PetItem>
            });

        petList.push(<PetItem ownerId={ownerId} onPetAdded={pet => this.handleAddPetItem(pet)}></PetItem>);

        return (
                <Container fluid>
                    <Table>
                        <tbody>
                            {petList}
                        </tbody>
                    </Table>
                </Container>
        );
    }
}

PetList.propTypes = {
    petIds: PropTypes.array.isRequired,
    pets: PropTypes.array.isRequired,
    ownerId: PropTypes.number
};
  
export default PetList;
