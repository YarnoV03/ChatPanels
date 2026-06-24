package be.yarno.chatpanels.model;

import java.time.LocalTime;

public class ChatMessage {
  private final String sender;
  private final String message;
  private final LocalTime timestamp;
  private final MessageType type;

  public ChatMessage(String sender, String message, MessageType type) {
    this.sender = sender;
    this.message = message;
    this.timestamp = LocalTime.now();
    this.type = type;
  }

  public String getSender() {
    return sender;
  }

  public String getMessage() {
    return message;
  }

  public LocalTime getTimestamp() {
    return timestamp;
  }

  public MessageType getType() {
    return type;
  }
}
