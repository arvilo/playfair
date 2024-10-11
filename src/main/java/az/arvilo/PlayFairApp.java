package az.arvilo;

import az.arvilo.exception.EdgeCaseException;

import java.util.Scanner;

public class PlayFairApp {

    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter empty line for exit");
        PlayFair playFair = new PlayFair();
        while (true) {
            System.out.print("key: ");
            String key = scan.nextLine();
            if (key.equals("")) {
                break;
            }
            System.out.print("text: ");
            playFair.setKey(key);

            String text = scan.nextLine();
            if (text.equals(""))
                break;

            try {
                String code = playFair.encode(text);
                playFair.showMatrix();
                System.out.println();
                System.out.println("**********");
                System.out.println("Result: " + code);
            } catch (EdgeCaseException e) {
                System.out.println("Edge case. Please try again.");
            }
        }
    }
}
