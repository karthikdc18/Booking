package com.shows.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;

    @ElementCollection
    private Map<String, Integer> slots = new HashMap<>();

    @ElementCollection
    private Map<String, List<String>> waitlist = new HashMap<>();
}