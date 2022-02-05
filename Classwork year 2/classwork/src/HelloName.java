import javax.swing.*;

public class HelloName {
    public static void main(String[] args) {
        String name;
        name = JOptionPane.showInputDialog(null, "What is your name? ");
        JOptionPane.showMessageDialog(null,"Your name is "+ name);
    }
}