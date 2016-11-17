package com.aidenyang.models;

import java.util.Date;

import javax.persistence.*;


@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String content;
	private String recipientName;
	private String recipientEmail;
	private String senderName;
	private Date sentDate;
	
	public Message(String content, String recipientName, String recipientEmail, String senderName, String senderEmail, Date sentDate) {
		this.content = content;
		this.recipientName = recipientName;
		this.recipientEmail = recipientEmail;
		this.senderName = senderName;
		this.sentDate = sentDate; // I am keeping the date in a JS format since I am displaying it on the frontend only
	}
	
	// Constructor for Jackson
	public Message() {
		this.content = getContent();
		this.recipientName = getRecipientName();
		this.recipientEmail = getRecipientEmail();
		this.senderName = getSenderName();
		this.sentDate = getSentDate();
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

}
