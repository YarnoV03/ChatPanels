package be.yarno.chatpanels.listener;

import be.yarno.chatpanels.manager.ChatPanelManager;
import be.yarno.chatpanels.model.MessageLimit;
import be.yarno.chatpanels.panel.ChatPanel;
import be.yarno.chatpanels.panel.ChatPanelRenderer;
import be.yarno.chatpanels.panel.PanelBounds;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.event.client.input.KeyEvent.State;

public class ChatKeyBindListener {
  private final ChatPanelManager panelManager;
  private final ChatPanelRenderer renderer;
  private final int triggerKeyId = 75;

  public ChatKeyBindListener(ChatPanelManager panelManager, ChatPanelRenderer renderer) {
    this.panelManager = panelManager;
    this.renderer = renderer;
  }

  @Subscribe
  public void onKey(KeyEvent event) {
    if (event.state() == State.PRESS && event.key().getId() == triggerKeyId) {

      if (Laby.references().labyAPI().minecraft().minecraftWindow().currentScreen() != null) {
        return;
      }

      int panelCount = this.panelManager.getChatPanels().size() + 1;

      String defaultName = "Chat " + panelCount;
      PanelBounds defaultBounds = new PanelBounds(50 + (panelCount * 15), 50 + (panelCount * 15), 200, 100);
      ChatPanel newPanel = new ChatPanel(defaultName, MessageLimit.ONE_HUNDRED, defaultBounds);
      this.panelManager.addPanel(newPanel);

      Laby.references().labyAPI().minecraft().minecraftWindow().displayScreen(this.renderer);
    }
  }
}