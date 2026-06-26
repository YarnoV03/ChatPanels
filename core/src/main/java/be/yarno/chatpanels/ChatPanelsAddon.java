package be.yarno.chatpanels;

import be.yarno.chatpanels.listener.ChatKeyBindListener;
import be.yarno.chatpanels.listener.ChatMessageListener;
import be.yarno.chatpanels.manager.ChatPanelManager;
import be.yarno.chatpanels.panel.ChatPanelRenderer;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class ChatPanelsAddon extends LabyAddon<ChatPanelConfiguration> {

  private ChatPanelManager panelManager;
  private ChatPanelRenderer panelRenderer;
  private ChatMessageListener messageListener;
  private ChatKeyBindListener keyBindListener;

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.panelManager = new ChatPanelManager();
    this.panelRenderer = new ChatPanelRenderer(this.panelManager);

    this.messageListener = new ChatMessageListener(this.panelManager);
    this.keyBindListener = new ChatKeyBindListener(this.panelManager, this.panelRenderer);

    this.registerListener(this.messageListener);
    this.registerListener(this.keyBindListener);

    System.out.println("ChatPanels manager and HUD Renderer loaded successfully!");
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