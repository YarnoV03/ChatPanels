package be.yarno.chatpanels.ui;

import be.yarno.chatpanels.model.ChatFilter;
import be.yarno.chatpanels.model.FilterType;
import be.yarno.chatpanels.model.MessageLimit;
import be.yarno.chatpanels.panel.ChatPanel;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.ScrollWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;

@AutoActivity
public class ChatPanelFilterScreen extends Activity {
  private final ChatPanel panel;
  private TextFieldWidget inputField;
  private TextFieldWidget nameField;

  public ChatPanelFilterScreen(ChatPanel panel) {
    this.panel = panel;
  }

  public void init() {
    this.buildUI();
  }

  private void buildUI() {
    this.document().getChildren().clear();

    ComponentWidget title = ComponentWidget.component(Component.text("§aSettings for: " + panel.getPanelName()));
    this.document().addChild(title);

    this.nameField = new TextFieldWidget();
    this.nameField.setText(panel.getPanelName());
    this.nameField.placeholder(Component.text("Change panel name..."));
    this.document().addChild(this.nameField);

    ButtonWidget saveNameBtn = new ButtonWidget();
    saveNameBtn.updateComponent(Component.text("§eSave Name"));
    saveNameBtn.setPressable(() -> {
      String newName = this.nameField.getText();
      if (!newName.isEmpty()) {
        this.panel.rename(newName);
        this.buildUI();
      }
    });
    this.document().addChild(saveNameBtn);

    MessageLimit currentLimit = this.panel.getHistory().getLimit();
    ButtonWidget limitBtn = new ButtonWidget();
    limitBtn.updateComponent(Component.text("§bLimit: " + currentLimit.getAmount()));
    limitBtn.setPressable(() -> {
      MessageLimit[] limits = MessageLimit.values();
      int nextOrdinal = (currentLimit.ordinal() + 1) % limits.length;
      this.panel.getHistory().changeLimit(limits[nextOrdinal]);
      this.buildUI();
    });
    this.document().addChild(limitBtn);

    this.inputField = new TextFieldWidget();
    this.inputField.placeholder(Component.text("Enter filter keyword..."));
    this.document().addChild(this.inputField);

    for (FilterType type : FilterType.values()) {
      ButtonWidget addBtn = new ButtonWidget();
      addBtn.updateComponent(Component.text("§6+ " + type.name()));
      addBtn.setPressable(() -> {
        String text = this.inputField.getText();
        if (!text.isEmpty()) {
          ChatFilter filter = new ChatFilter(text, type);
          this.panel.addFilter(filter);
          this.inputField.setText("");
          this.buildUI();
        }
      });
      this.document().addChild(addBtn);
    }

    ScrollWidget scrollList = new ScrollWidget(new VerticalListWidget<>());

    for (ChatFilter filter : this.panel.getFilters()) {
      ComponentWidget filterText = ComponentWidget.component(
          Component.text("§7- " + filter.getFilter().name() + ": §f" + filter.getChatFilterText())
      );

      ButtonWidget deleteBtn = new ButtonWidget();
      deleteBtn.updateComponent(Component.text("§c[X]"));
      deleteBtn.setPressable(() -> {
        this.panel.removeFilter(filter);
        this.buildUI();
      });

      scrollList.addChild(filterText);
      scrollList.addChild(deleteBtn);
    }

    this.document().addChild(scrollList);
  }
}