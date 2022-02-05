import javax.swing.*;
import java.util.Scanner;

public class switchName {
    public static void main(String[] args)
    {
        String year;
        year = JOptionPane.showInputDialog(null,"birth year");
        int birth = Integer.parseInt(year);

        switch (birth)
        {
            case 1:
                JOptionPane.showMessageDialog(null, "You are a Freshman" );
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You are a Sophomore" );
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "You are a Senior" );
                break;
            default:
                JOptionPane.showMessageDialog(null, "You are too old!!" );

        }
    }

    public static class scannerclass {
        public static void main(String[] args)
        {
            String name;
            int age;
            String address;

            Scanner inputDevice = new Scanner(System.in);
            System.out.print("please enter your name>> ");
            name = inputDevice.nextLine();
            System.out.print("Please enter your address ");
            address = inputDevice.nextLine();
            System.out.print("Please enter your age ");
            age = inputDevice.nextInt();


            System.out.println("Your name is "+ name+" your age is "+ age + " your address is "+ address);


        }
    }
}
