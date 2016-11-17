package com.aidenyang;

import com.sendgrid.*;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.aidenyang.models.Message;


public class Courier {
	private Message msg;
	private String subjectLine;
	public Courier(Message msg) {
		this.msg = msg;
		this.subjectLine = "Happy Holidays from " + msg.getSenderName();
	}
	
	public int sendMessage() {
		try {
			sendWithSendGrid();
		} catch (IOException e) {
			System.out.println("SendGrid failed. Falling back to Mailgun");
		}
		Response mgRes = sendWithMailgun();
		if (mgRes.getStatus() != 200) {
			return 400;
		}
		return 200;
	}
	
	public Response sendWithMailgun() {
	    Client client = ClientBuilder.newClient();
	    client.register(HttpAuthenticationFeature.basic("api",
	                System.getenv("MAILGUN_API_KEY")));
	    WebTarget target = client.target("https://api.mailgun.net/v3/sandbox2391591ab9f34f4fbe32ad95de02adfc.mailgun.org/messages"); 
	    MultivaluedMap<String, String> formData  = new MultivaluedHashMap<String, String>();
	    formData.add("from", "Happy Holidays App <postmaster@sandbox2391591ab9f34f4fbe32ad95de02adfc.mailgun.org>");
	    String toStr = msg.getRecipientName() + " <" + msg.getRecipientEmail() + ">";
	    formData.add("to", toStr);
	    formData.add("subject", subjectLine);
	    formData.add("text", msg.getContent());
	    return target.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(formData));
	}
	
	public com.sendgrid.Response sendWithSendGrid() throws IOException {
		Email from = new Email("alyang250@gmail.com");
		Email to = new Email(msg.getRecipientEmail());
		Content content = new Content("text/plain", msg.getContent());
		Mail mail = new Mail(from, subjectLine, to, content);
		Map<String, String> env = System.getenv();
		String API_KEY = System.getenv("SENDGRID_API_KEY");
		SendGrid sg = new SendGrid(API_KEY);
		Request request = new Request();
		
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			com.sendgrid.Response response = sg.api(request);
			return response;
		}
		catch (IOException e) {
			throw e;
		}
	}

	public Message getMsg() {
		return msg;
	}
	    
}
