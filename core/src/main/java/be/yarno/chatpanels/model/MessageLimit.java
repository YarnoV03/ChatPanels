package be.yarno.chatpanels.model;

public enum MessageLimit {
  FIFTY(50),
  ONE_HUNDRED(100),
  TWO_HUNDRED(200),
  THREE_HUNDRED(300),
  FOUR_HUNDRED(400),
  FIVE_HUNDRED(500),
  ONE_THOUSAND(1000);

  private final int amount;

  MessageLimit(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
