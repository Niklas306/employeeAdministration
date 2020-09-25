import control.MainController;
import view.MainFrame;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Main.setup();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private static void setup() throws IOException {
        MainController mainController = new MainController();
        MainFrame mainFrame = new MainFrame(mainController, "Mitarbeiter verwalten", 50, 50, 800, 600);
    }

}
