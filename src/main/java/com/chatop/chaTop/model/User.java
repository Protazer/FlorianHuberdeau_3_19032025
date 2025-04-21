package com.chatop.chaTop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


/**
 * Entity representing a User.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Password of the user.
     */
    private String password;
    /**
     * User updated Timestamp.
     */
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * User updated Timestamp.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
