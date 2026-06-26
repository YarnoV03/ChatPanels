package be.yarno.chatpanels.panel;

import be.yarno.chatpanels.manager.ChatPanelManager;
import be.yarno.chatpanels.ui.ChatPanelFilterScreen;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gfx.pipeline.renderer.text.TextRenderingOptions;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.ScreenContext;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.key.InputType;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.state.ScreenCanvas;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.util.Color;
import net.labymod.api.util.bounds.ModifyReason;
import net.labymod.api.util.bounds.Rectangle;

@AutoActivity
public class ChatPanelRenderer extends Activity {

  private static final ModifyReason CHAT_PANEL = ModifyReason.of(ChatPanelRenderer.class, "chat panel input bounds");
  private final ChatPanelManager panelManager;
  private ChatPanel activePanel;
  private boolean dragging;
  private boolean resizing;
  private int dragStartX;
  private int dragStartY;
  private int startWidth;
  private int startHeight;
  private TextFieldWidget chatInputField;

  public ChatPanelRenderer(ChatPanelManager panelManager) {
    this.panelManager = panelManager;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    this.chatInputField = new TextFieldWidget();this.chatInputField.placeholder(Component.text("Type a message..."));
    this.chatInputField.setFocused(true);
    this.document().addChild(this.chatInputField);
  }

  @Override
  public void render(ScreenContext context) {
    ScreenCanvas canvas = context.canvas();

    int width = Laby.references()
        .labyAPI()
        .minecraft()
        .minecraftWindow()
        .getScaledWidth();

    int height = Laby.references()
        .labyAPI()
        .minecraft()
        .minecraftWindow()
        .getScaledHeight();

    this.chatInputField.bounds().set(Rectangle.absolute(2, height - 18, width - 4, 14), CHAT_PANEL);

    for (ChatPanel panel : this.panelManager.getChatPanels()) {
      PanelBounds bounds = panel.getBounds();

      int x = bounds.getX();
      int y = bounds.getY();
      int w = bounds.getWidth();
      int h = bounds.getHeight();

      int bg = panel == this.activePanel ? Color.ofRGB(35,35,35).get() : Color.ofRGB(20,20,20).get();

      canvas.submitAbsoluteRect(x, y, x+w, y+h, bg);

      canvas.submitComponent(Component.text(panel.getPanelName()), x+5, y+5, -1, TextRenderingOptions.NONE);

      canvas.submitComponent(Component.text("§e[⚙]"), x+w-25, y+5, -1, TextRenderingOptions.NONE);

      canvas.submitAbsoluteRect(x+w-8, y+h-8, x+w, y+h, Color.ofRGB(120,120,120).get());

      int textY = y+20;

      for(var message : panel.getHistory().getMessages()) {
        if(textY > y+h-10)
          break;

        canvas.submitComponent(Component.text(message.getMessage()), x+5, textY, -1, TextRenderingOptions.NONE);

        textY += 12;
      }
    }
  }

  @Override
  public boolean keyPressed(Key key, InputType type) {
    if(key.getId()==257 || key.getId()==335) {
      String msg = this.chatInputField.getText();

      if(msg != null && !msg.isBlank()) {
        Laby.references()
            .labyAPI()
            .minecraft()
            .chatExecutor()
            .chat(msg,false);

        this.chatInputField.setText("");
        return true;
      }
    }

    return super.keyPressed(key,type);
  }

  @Override
  public boolean mouseClicked(MutableMouse mouse, MouseButton button){
    if(button != MouseButton.LEFT)
      return super.mouseClicked(mouse,button);

    double mx = mouse.getX();
    double my = mouse.getY();

    for(ChatPanel panel : panelManager.getChatPanels()){
      PanelBounds b = panel.getBounds();

      int x=b.getX();
      int y=b.getY();
      int w=b.getWidth();
      int h=b.getHeight();

      if(mx >= x && mx <= x+w && my >= y && my <= y+h){
        activePanel = panel;

        if(mx >= x+w-30 && my <= y+20){
          ChatPanelFilterScreen screen = new ChatPanelFilterScreen(panel);

          Laby.references()
              .labyAPI()
              .minecraft()
              .minecraftWindow()
              .displayScreen(screen);

          screen.init();

          return true;
        }

        if(mx >= x+w-10 && my >= y+h-10){
          resizing=true;

          dragStartX=(int)mx;
          dragStartY=(int)my;

          startWidth=w;
          startHeight=h;

          return true;
        }

        if(my <= y+18){
          dragging=true;

          dragStartX=(int)mx-x;
          dragStartY=(int)my-y;

          return true;
        }
      }
    }

    return true;
  }

  @Override
  public boolean mouseDragged(MutableMouse mouse, MouseButton button, double dx, double dy){
    if(activePanel==null)
      return false;

    PanelBounds b = activePanel.getBounds();

    int mx = mouse.getX();
    int my = mouse.getY();

    if(dragging){
      b.setX(mx-dragStartX);
      b.setY(my-dragStartY);

      return true;
    }

    if(resizing){
      b.setWidth(Math.max(100, startWidth+(mx-dragStartX)));
      b.setHeight(Math.max(60, startHeight+(my-dragStartY)));

      return true;
    }

    return false;
  }

  @Override
  public boolean mouseReleased(MutableMouse mouse, MouseButton button){
    dragging=false;
    resizing=false;

    return true;
  }

  @Override
  public boolean isPauseScreen(){
    return false;
  }
}