package org.example.core.listener;

import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import org.example.core.ChatPanelsAddon;

public class ChatPanelsTickListener {

  private final ChatPanelsAddon addon;

  public ChatPanelsTickListener(ChatPanelsAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }

    this.addon.logger().info(
        this.addon.configuration().enabled().get() ? "enabled" : "disabled"
    );
  }
}