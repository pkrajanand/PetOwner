import React from 'react';
import TestRenderer from 'react-test-renderer';
import {Button, Table } from 'reactstrap';
import OwnerList from './OwnerList';
import PetList from './PetList';

function setup( editing = false ) {
  const props = {
  };
  return {
  }
}

describe('components', () => {
  describe('OwnerList', () => {

    it('initial render with owners without pets', () => {

      const root = TestRenderer.create(<OwnerList/>).root;
      const instance = root.instance;
      instance.setState({
        owners: [
          { "firstName":"Jowena", "lastName":"Chua", "city":"Manila", "petIds":[],"id":-3 },
          { "firstName":"Wally","lastName":"Crocker","city":"Sydney","petIds":[],"id":-2 },
          { "firstName":"Monisha","lastName":"Suresh","city":"Bangalore","petIds":[],"id":-1 }
        ],
        pets: [],
        isLoading: false
      });

      const ownerTableElement = root.findByProps({ className: "mt-4 owners" });
      expect(ownerTableElement.type).toBe(Table);
      const ownerItems = ownerTableElement.findByType('tbody').children;
      expect(ownerItems.length).toBe(3);
      
      const owner1Details = ownerItems[0].findByProps({ className: "mt-4" });
      expect(owner1Details.type).toBe(Table);
      const petsElementForOwner1 = owner1Details.findByType(PetList);
      expect(petsElementForOwner1.children.length).toBe(1);
      const addPetButton = petsElementForOwner1.findByType(Button);
      expect(addPetButton.props.children).toBe('Add Pet');

    

    });

    it('adding pet for an owner', () => {

      const root = TestRenderer.create(<OwnerList/>).root;
      const instance = root.instance;
      instance.setState({
        owners: [
          { "firstName":"Jowena", "lastName":"Chua", "city":"Manila", "petIds":[1],"id":3 },
          { "firstName":"Wally","lastName":"Crocker","city":"Sydney","petIds":[2],"id":2 },
          { "firstName":"Monisha","lastName":"Suresh","city":"Bangalore","petIds":[3],"id":1 }
        ],
        pets: [],
        isLoading: false
      });

      const ownerTableElement = root.findByProps({ className: "mt-4 owners" });
      const ownerItems = ownerTableElement.findByType('tbody').children;      
      const owner1Details = ownerItems[0].findByProps({ className: "mt-4" });      
      const petsElementForOwner1 = owner1Details.findByType(PetList);
      const addPetButton = petsElementForOwner1.findByType(Button);

      petsElementForOwner1.props.onAddPet({id: 1, name: "parrot", birthday: "16/05/2017", ownerId: 3});

      expect(instance.state.owners[0].petIds.length).toBe(2);

    });
  })
});
