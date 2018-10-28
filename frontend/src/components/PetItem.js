import React, { Component } from 'react';
import { Button } from 'reactstrap';
import PropTypes from 'prop-types';

class PetItem extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            'newPetName': '',
            'newPetBirthday': ''
        }

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
            this.props.onPetAdded(data);   
        });
    }

    updateNewPetName(e) {
        this.setState({'newPetName': e.target.value});
    }

    updateNewPetBirthday(e) {
        this.setState({'newPetBirthday': e.target.value});
    }

    render() {
        const {pet, ownerId} = this.props;
        const {newPetName, newPetBirthday} = this.state;

        let petDetails;
        if (pet) {
            petDetails = <tr>
                            <td>{pet.id}</td>
                            <td>{pet.name}</td>
                            <td>{pet.birthday}</td>
                            <td></td>
                        </tr>
        } else {            
            petDetails = <tr>
                            <td>
                                
                            </td>
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
        }
        return {petDetails};

    }
}

PetItem.propTypes = {
    pet: PropTypes.JSON,
    pets: PropTypes.array,
    ownerId: PropTypes.number
};

export default PetItem;
