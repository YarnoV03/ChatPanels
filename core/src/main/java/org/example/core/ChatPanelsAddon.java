package org.example.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import org.example.core.commands.ExamplePingCommand;
import org.example.core.listener.ChatPanelsTickListener;

@AddonMain
public class ChatPanelsAddon extends LabyAddon<ChatPanelConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    this.registerListener(new ChatPanelsTickListener(this));
    this.registerCommand(new ExamplePingCommand());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<ChatPanelConfiguration> configurationClass() {
    return ChatPanelConfiguration.class;
  }
}
