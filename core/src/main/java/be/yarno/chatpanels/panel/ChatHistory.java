package be.yarno.chatpanels.panel;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import be.yarno.chatpanels.model.ChatMessage;
import be.yarno.chatpanels.model.MessageLimit;

public class ChatHistory {
  private final List<ChatMessage> messages;
  private MessageLimit limit;

  public ChatHistory(MessageLimit limit) {
    this.messages = new ArrayList<>();
    this.limit = limit;
  }

  public MessageLimit getLimit() {
    return limit;
  }

  public List<ChatMessage> getMessages() {
    return Collections.unmodifiableList(messages);
  }

  private void trimHistory() {
    while(messages.size() > limit.getAmount()) {
      messages.remove(0);
    }
  }

  public void addMessage(ChatMessage message) {
    messages.add(message);

    trimHistory();
  }

  public void changeLimit(MessageLimit newLimit) {
    if(newLimit == null) {
      throw new IllegalArgumentException();
    }

    limit = newLimit;

    trimHistory();
  }
}
