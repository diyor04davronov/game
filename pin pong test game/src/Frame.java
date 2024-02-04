import javax.swing.*;


public class Frame extends JFrame {
    Panel panel;

    Frame() {
        panel = new Panel();
        this.add(panel);
        this.setTitle("PIN PONG GAME");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
