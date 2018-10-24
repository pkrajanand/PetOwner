package com.genbook.petowner.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.genbook.petowner.dto.OwnerDto;
import com.genbook.petowner.entity.Owner;

public class OwnerMapper {

  public static List<OwnerDto> map(List<Owner> owners) {
    return owners.stream().map(owner -> {
      return new OwnerDto(owner);
    }).collect(Collectors.toList());
  }
}
