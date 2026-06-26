package be.yarno.chatpanels.listener;

import be.yarno.chatpanels.manager.ChatPanelManager;
import be.yarno.chatpanels.model.ChatMessage;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.TranslatableComponent;

public class ChatMessageListener {

  private final ChatPanelManager panelManager;

  public ChatMessageListener(ChatPanelManager panelManager) {
    this.panelManager = panelManager;
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    TranslatableComponent component =
        (TranslatableComponent) event.message();

    String sender = "unknown";
    String messageText = "";

    if (component.getKey().equals("chat.type.text")) {

      var arguments = component.getArguments();

      TextComponent senderComponent =
          (TextComponent) arguments.get(0);

      TextComponent messageComponent =
          (TextComponent) arguments.get(1);

      sender = senderComponent.getText();
      messageText = messageComponent.getText();
    }

    ChatMessage message =
        new ChatMessage(
            messageText,
            sender
        );

    panelManager.handleMessage(message);
  }
}