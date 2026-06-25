package be.yarno.chatpanels;

import be.yarno.chatpanels.listener.ChatMessageListener;
import be.yarno.chatpanels.manager.ChatPanelManager;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class ChatPanelsAddon extends LabyAddon<ChatPanelConfiguration> {

  private ChatPanelManager panelManager;
  private ChatMessageListener messageListener;

  @Override
  protected void enable() {
    this.registerSettingCategory();

    panelManager = new ChatPanelManager();
    messageListener = new ChatMessageListener(panelManager);

    System.out.println("ChatPanels manager loaded!");
  }

  public ChatPanelManager getPanelManager() {
    return panelManager;
  }

  public ChatMessageListener getMessageListener() {
    return messageListener;
  }

  @Override
  protected Class<ChatPanelConfiguration> configurationClass() {
    return ChatPanelConfiguration.class;
  }
}