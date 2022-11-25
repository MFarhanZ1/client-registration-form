import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Client Registration Form");
        jFrame.setContentPane(new client_registration_form().getRootPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(500,350);
        jFrame.setVisible(true);
    }
}
