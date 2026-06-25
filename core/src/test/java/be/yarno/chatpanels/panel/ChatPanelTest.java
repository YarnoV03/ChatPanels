package be.yarno.chatpanels.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import be.yarno.chatpanels.model.ChatMessage;
import be.yarno.chatpanels.model.MessageLimit;

public class ChatPanelTest {

  @Test
  void messageGetsAddedWithoutFilters() {
    ChatPanel panel = new ChatPanel("General", MessageLimit.FIFTY);

    ChatMessage message = new ChatMessage("hello", "Yarno");

    panel.handleMessage(message);

    assertEquals(1, panel.getHistory().getMessages().size());
  }
}