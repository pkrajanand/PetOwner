import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button, Container, Table } from 'reactstrap';

class PetList extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            'newPetName': '',
            'newPetBirthday': ''
        }

        this.addPet = this.addPet.bind(this)
    }

    addPet(newPetOwnerId, newPetName, newPetBirthday) {

        fetch(`/pets`,
            {
                method: 'POST',
                headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
                body: JSON.stringify({"name": newPetName, "birthday": newPetBirthday, "ownerId": {'id': newPetOwnerId}})
            }
        )
        .then(response => response.json())
        .then(data => {
            this.props.onAddPet(data);   
        });
    }

    updateNewPetName(e) {
        this.setState({'newPetName': e.target.value});
    }

    updateNewPetBirthday(e) {
        this.setState({'newPetBirthday': e.target.value});
    }

    render() {
        const { pets, petIds } = this.props;

        const petList = pets
            .filter(pet => petIds.find(pId => pId === pet.id) )
            .map ((pet, i) => {

                return <tr key={i}>
                            <td>{pet.id}</td>
                            <td>{pet.name}</td>
                            <td>{pet.birthday}</td>
                            <td></td>
                        </tr>
            });

        const {ownerId} = this.props;
        const {newPetName, newPetBirthday} = this.state;
        const petDetails = <tr key="-1">
                                <td></td>
                                <td>
                                    Pet Name: 
                                    <input type="text" onChange={(e) => this.updateNewPetName(e)}/>
                                </td>
                                <td>
                                    Birthday:
                                    <input type="text" onChange={(e) => this.updateNewPetBirthday(e)}/>
                                </td>
                                <td>
                                    <Button size="sm" color="primary" onClick={() => this.addPet(ownerId, newPetName, newPetBirthday)}>Add Pet & Refresh Page</Button>
                                </td>
                            </tr>

        petList.push(petDetails);

        return (
                        <tbody>
                            {petList}
                        </tbody>
        );
    }
}

PetList.propTypes = {
    petIds: PropTypes.array.isRequired,
    pets: PropTypes.array,
    ownerId: PropTypes.number
};
  
export default PetList;
