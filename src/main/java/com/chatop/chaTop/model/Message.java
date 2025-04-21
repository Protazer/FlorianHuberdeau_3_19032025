package com.chatop.chaTop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Entity representing a message.
 */
@Data
@Entity
@Table(name = "messages")
public class Message {
    /**
     * Unique identifier for the message.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Unique identifier for the associated rental.
     */
    @Column(name = "rental_id")
    private Integer rentalId;
    /**
     * Unique identifier for the associated user.
     */
    @Column(name = "user_id")
    private Integer userId;
    /**
     * Message content.
     */
    private String message;
    /**
     * Message created Timestamp.
     */
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * Message updated Timestamp.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
