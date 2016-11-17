package com.aidenyang.controllers;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aidenyang.Courier;
import com.aidenyang.models.Message;
import com.aidenyang.repositories.MessageRepository;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MessageRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Message>> getAllMessage() {
		return new ResponseEntity<>((Collection<Message>) repo.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Message> sendMessage(@RequestBody Message msg) {
//		Courier sender = new Courier(msg);
//		int response = sender.sendMessage();
//		if (response != 200) {
//			System.out.println("Both email services failed.");
//			return new ResponseEntity<Message> (new Message(null, null, null, null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		try {
			Message result = repo.save(msg);
			return new ResponseEntity<Message> (result, HttpStatus.CREATED);
		}
		catch (DataAccessException e) {
			return new ResponseEntity<Message> (new Message(null, null, null, null, null, null), HttpStatus.CONFLICT);
		}
	}	
}
