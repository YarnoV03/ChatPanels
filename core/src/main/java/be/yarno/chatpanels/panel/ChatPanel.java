package be.yarno.chatpanels.panel;

import java.util.ArrayList;
import java.util.List;
import be.yarno.chatpanels.model.ChatFilter;
import be.yarno.chatpanels.model.ChatMessage;
import be.yarno.chatpanels.model.MessageLimit;

public class ChatPanel {
  private String panelName;
  private final ChatHistory history;
  private final List<ChatFilter> filters;

  public ChatPanel(String panelName, MessageLimit limit) {
    this.panelName = panelName;
    this.history = new ChatHistory(limit);
    this.filters = new ArrayList<>();
  }

  public String getPanelName() {
    return panelName;
  }

  public void rename(String newPanelName) {
    if (newPanelName == null || newPanelName.isBlank()) {
      throw new IllegalArgumentException();
    }

    panelName = newPanelName;
  }

  public void handleMessage(ChatMessage message) {
    if (filters.isEmpty()) {
      history.addMessage(message);

      return;
    }

    for (ChatFilter filter : filters) {
      if (filter.matches(message)) {
        history.addMessage(message);
        break;
      }
    }
  }

  public void addFilter(ChatFilter filter) {
    if (filter == null) {
      throw new IllegalArgumentException();
    }

    filters.add(filter);
  }

  public void removeFilter(ChatFilter filter) {
    if (filter == null) {
      throw new IllegalArgumentException();
    }

    if (!filters.remove(filter)) {
      throw new IllegalArgumentException("Filter not found");
    }
  }
}
