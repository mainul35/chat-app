package com.mainul35.chatapp.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mainul35.chatapp.model.ChatMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class ChatController {

	final SimpMessagingTemplate simpMessagingTemplate;

	public ChatController(SimpMessagingTemplate messagingTemplate) {
		this.simpMessagingTemplate = messagingTemplate;
	}

	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("roomId", UUID.randomUUID().toString());
		return "index";
	}

/*	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}*/

	@MessageMapping("/chat.register/{roomId}")
	public void register(@DestinationVariable String roomId, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		simpMessagingTemplate.convertAndSend("/topic/"+roomId, chatMessage);
	}

	/*@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}*/

	@MessageMapping("/chat.send/{roomId}")
	public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
		simpMessagingTemplate.convertAndSend("/topic/"+roomId, chatMessage);
	}

}
