package com.thebrgtest.jsondb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Input {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long productId;
    private String eventId;
    private Date start;
    private Date end;

    private Integer seatsAvailable;

    private Integer seatsTotal;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Property> properties;

    public Input() {
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
