package com.shows.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Booking {
    @Id
    private String id = UUID.randomUUID().toString();

    @JsonBackReference
    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Show show;

    private String slot;

    private int persons;

}