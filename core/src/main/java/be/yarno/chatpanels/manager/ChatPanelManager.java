package be.yarno.chatpanels.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import be.yarno.chatpanels.model.ChatMessage;
import be.yarno.chatpanels.panel.ChatPanel;

public class ChatPanelManager {
  private final List<ChatPanel> panels;

  public ChatPanelManager() {
    panels = new ArrayList<>();
  }

  public List<ChatPanel> getChatPanels() {
    return Collections.unmodifiableList(panels);
  }

  public void addPanel(ChatPanel panel) {
    if (panel == null) {
      throw new IllegalArgumentException();
    }

    panels.add(panel);
  }

  public void removePanel(ChatPanel panel) {
    if (panel == null) {
      throw new IllegalArgumentException();
    }

    if (!panels.remove(panel)) {
      throw new IllegalArgumentException("Panel not found");
    }
  }

  public void handleMessage(ChatMessage message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }

    for(ChatPanel panel : panels) {
      panel.handleMessage(message);
    }
  }
}
