package com.example.demo.model;

	public class ChatMessage {
	    private String id;
	    private String sender;
	    private String text;
	    private long timestamp;

	    public ChatMessage() {
	    }

	    public ChatMessage(String id, String sender, String text, long timestamp) {
	        this.id = id;
	        this.sender = sender;
	        this.text = text;
	        this.timestamp = timestamp;
	    }
	    
	    public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		

	    // Getters and Setters
	} 

