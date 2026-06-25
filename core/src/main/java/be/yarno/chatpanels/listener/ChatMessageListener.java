package be.yarno.chatpanels.listener;

import be.yarno.chatpanels.manager.ChatPanelManager;
import be.yarno.chatpanels.model.ChatMessage;

public class ChatMessageListener {

  private final ChatPanelManager panelManager;

  public ChatMessageListener(ChatPanelManager panelManager) {
    this.panelManager = panelManager;
  }

  public void handleMessage(ChatMessage message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }

    panelManager.handleMessage(message);
  }
}