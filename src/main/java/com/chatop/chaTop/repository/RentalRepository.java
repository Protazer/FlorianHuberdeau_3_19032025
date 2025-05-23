package com.chatop.chaTop.repository;

import com.chatop.chaTop.model.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Rental entities in the database.
 */
@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
}
