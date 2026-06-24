package be.yarno.chatpanels.model;

public class ChatFilter {
  private final String chatFilterText;
  private final FilterType filter;

  public ChatFilter(String chatFilterText, FilterType filter) {
    this.chatFilterText = chatFilterText;
    this.filter = filter;
  }

  public String getChatFilterText() {
    return chatFilterText;
  }

  public FilterType getFilter() {
    return filter;
  }
}
