package view;

import control.MainController;
import sun.applet.Main;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private MainController mainController;
    private PanelHandler panelHandler;

    public MainFrame(MainController mainController, String name, int x, int y, int width, int height) throws IOException {
        this.mainController = mainController;
        this.panelHandler = new PanelHandler(mainController);

        this.setLocation(x, y);
        this.setSize(width, height);
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setVisible(true);

        this.switchToPanel(panelHandler.getPanel());
    }

    public void switchToPanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.validate();
        this.repaint();
    }

}
