package com.chatop.chaTop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Entity representing a rental.
 */
@Data
@Entity
@Table(name = "rentals")
public class Rental {
    /**
     * Unique identifier for the rental.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Name of the rental.
     */
    private String name;
    /**
     * Surface of the rental.
     */
    private int surface;

    /**
     * Price of the rental.
     */
    private int price;

    /**
     * Picture url of the rental.
     */
    private String picture;

    /**
     * Description of the rental.
     */
    private String description;

    /**
     * Unique identifier for the associated user.
     */
    @Column(name = "owner_id")
    private int ownerId;
    /**
     * Rental created Timestamp.
     */
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * Rental updated Timestamp.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
