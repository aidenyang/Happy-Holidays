package com.aidenyang.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aidenyang.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAll();
	List<Message> findBySenderName(String senderName);
}
