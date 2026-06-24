package be.yarno.chatpanels;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class ChatPanelsAddon extends LabyAddon<ChatPanelConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<ChatPanelConfiguration> configurationClass() {
    return ChatPanelConfiguration.class;
  }
}
