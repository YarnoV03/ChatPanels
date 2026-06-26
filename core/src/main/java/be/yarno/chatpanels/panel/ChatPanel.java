package be.yarno.chatpanels.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import be.yarno.chatpanels.model.ChatFilter;
import be.yarno.chatpanels.model.ChatMessage;
import be.yarno.chatpanels.model.MessageLimit;
import be.yarno.chatpanels.model.PanelBounds;

public class ChatPanel {

  private String panelName;
  private final ChatHistory history;
  private final List<ChatFilter> filters;
  private final PanelBounds bounds;

  public ChatPanel(
      String panelName,
      MessageLimit limit,
      PanelBounds bounds
  ) {
    if (bounds == null) {
      throw new IllegalArgumentException();
    }

    this.panelName = panelName;
    this.history = new ChatHistory(limit);
    this.filters = new ArrayList<>();
    this.bounds = bounds;
  }

  public String getPanelName() {
    return panelName;
  }

  public List<ChatFilter> getFilters() {
    return Collections.unmodifiableList(filters);
  }

  public ChatHistory getHistory() {
    return history;
  }

  public PanelBounds getBounds() {
    return bounds;
  }

  public void rename(String newPanelName) {
    if (newPanelName == null || newPanelName.isBlank()) {
      throw new IllegalArgumentException();
    }

    panelName = newPanelName;
  }

  public void handleMessage(ChatMessage message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }

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