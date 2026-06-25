package be.yarno.chatpanels.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChatFilterTest {

  @Test
  void containsFilterMatchesMessage() {
    ChatMessage message = new ChatMessage("hello world", "Yarno");
    ChatFilter filter = new ChatFilter("hello", FilterType.CONTAINS);

    assertTrue(filter.matches(message));
  }
}