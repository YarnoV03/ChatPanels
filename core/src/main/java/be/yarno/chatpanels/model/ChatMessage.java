package be.yarno.chatpanels.model;

import java.time.LocalTime;

public class ChatMessage {

  private final String message;
  private final LocalTime timestamp;
  private final String sender;

  public ChatMessage(String message, String sender) {
    this.message = message;
    this.timestamp = LocalTime.now();
    this.sender = sender;
  }

  public String getMessage() {
    return message;
  }

  public LocalTime getTimestamp() {
    return timestamp;
  }

  public String getSender() {
    return sender;
  }
}