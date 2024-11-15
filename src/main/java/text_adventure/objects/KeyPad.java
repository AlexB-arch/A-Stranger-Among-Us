package text_adventure.objects;

import java.util.ArrayList;

public class KeyPad{
  private int code = 0;
  private String direction = "";
  private Room DoorMap;
  public static MessageBus globalEventBus;

    public KeyPad(int code1, String direction1, Room DoorMap1) {
        code = code1;
        direction = direction1;
        DoorMap = DoorMap1;
        globalEventBus.startMessageProcessing();
    }

		public void enterCode(String code1) {
		        if (code1.equals(code)) {
		            MessageBus.getInstance().sendMessage(new TextMessage("open", roomId));
		        } else {
		            System.out.println("Incorrect code entered.");
		        }
}
