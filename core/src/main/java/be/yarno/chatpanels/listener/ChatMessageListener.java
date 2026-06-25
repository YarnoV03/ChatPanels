package be.yarno.chatpanels.listener;

import be.yarno.chatpanels.manager.ChatPanelManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class ChatMessageListener {

  private final ChatPanelManager panelManager;

  public ChatMessageListener(ChatPanelManager panelManager) {
    this.panelManager = panelManager;
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    System.out.println("Received chat: " + event.message());
  }
}