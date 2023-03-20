package com.example.lztest.dao;

import com.example.lztest.entity.Person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Person object with first name, last name, and person type")
public class PersonDAO implements Person {
    @ApiModelProperty(value = "First name", example = "John")
    private String name;
    @ApiModelProperty(value = "Surname", example = "Doe")
    private String surname;
    @ApiModelProperty(value = "Person type", example = "Mr")
    private String personType;
}

