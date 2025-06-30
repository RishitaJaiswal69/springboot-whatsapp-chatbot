package com.example.demo.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ChatMessage;

	@Repository
	public class MessageRepository {

	    private final DatabaseReference databaseReference;

	    public MessageRepository() {
	        databaseReference = FirebaseDatabase.getInstance().getReference("messages");
	    }

	    public void saveMessage(ChatMessage message) {
	        databaseReference.push().setValueAsync(message);
	    }
	}
	
