package com.thebrgtest.jsondb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long typeId;
    private String label;

    public Property() {

    }
}
