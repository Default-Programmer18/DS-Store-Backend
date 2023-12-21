package com.commerce.newbies.ecommerceproject.entities;

import org.springframework.stereotype.Component;

@Component
	public class EmailDetails {
	 
	
	    private String recipient;
	    private String msgBody;
	    private String subject;
		public EmailDetails() {
			super();
			// TODO Auto-generated constructor stub
		}
		public EmailDetails(String recipient, String msgBody, String subject) {
			super();
			this.recipient = recipient;
			this.msgBody = msgBody;
			this.subject = subject;
		}
		public String getRecipient() {
			return recipient;
		}
		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
		public String getMsgBody() {
			return msgBody;
		}
		public void setMsgBody(String msgBody) {
			this.msgBody = msgBody;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		@Override
		public String toString() {
			return "EmailDetails [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + "]";
		}

	    
	}

