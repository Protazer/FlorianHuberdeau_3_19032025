package com.chatop.chaTop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float surface;

    private Float price;

    private String picture;

    private String description;

    @Column(name="owner_id")
    private Long ownerId;

    @Column(name="created_at")
    private Date  createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
}
