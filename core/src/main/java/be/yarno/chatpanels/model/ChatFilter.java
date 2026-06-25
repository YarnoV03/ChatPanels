package be.yarno.chatpanels.model;

public class ChatFilter {
  private final String chatFilterText;
  private final FilterType filter;

  public ChatFilter(String chatFilterText, FilterType filter) {
    if (chatFilterText == null || filter == null) {
      throw new IllegalArgumentException();
    }

    this.chatFilterText = chatFilterText;
    this.filter = filter;
  }

  public String getChatFilterText() {
    return chatFilterText;
  }

  public FilterType getFilter() {
    return filter;
  }

  private String cleanFormatting(String text) {
    text = text.replaceAll("§.", "");

    return text;
  }

  public boolean matches(ChatMessage message) {
    String messageText = message.getMessage();
    String cleanedFilterText = chatFilterText;

    messageText = cleanFormatting(messageText);
    cleanedFilterText = cleanFormatting(cleanedFilterText);

    return switch (filter) {
      case CONTAINS -> messageText.contains(cleanedFilterText);
      case STARTS_WITH -> messageText.startsWith(cleanedFilterText);
      case ENDS_WITH -> messageText.endsWith(cleanedFilterText);
    };
  }
}
