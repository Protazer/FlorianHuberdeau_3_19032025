package com.chatop.chaTop.repository;

import com.chatop.chaTop.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Message entities in the database.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
