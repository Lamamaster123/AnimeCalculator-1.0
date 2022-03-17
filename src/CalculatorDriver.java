import java.io.*;
import java.util.*;

public class CalculatorDriver {
   private static Calculator calculator;
   private static Scanner input;
   private static String currentFileName;
   
   public static void main(String[] args) throws FileNotFoundException {
      input = new Scanner(System.in);
      calculator = initialize(input);
      userInput();
   }
   
   public static void userInput() {
      boolean quit = false;
      System.out.println("Welcome anime lover! ⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾");
      while (!quit) {
         System.out.print("Command: ");
         String command = input.nextLine();
         
         if (command.equalsIgnoreCase("add")) {
            calculator.add(input);
         } else if (command.equalsIgnoreCase("delete")) {
            calculator.delete(input);
         } else if (command.equalsIgnoreCase("print")) {
            calculator.print();
         } else if (command.equalsIgnoreCase("analyze")) {
            calculator.analyze();
         } else if (command.equalsIgnoreCase("edit")) {
            calculator.edit(input);
         } else if (command.equalsIgnoreCase("save")) {
            System.out.print("Name of file to save to: ");
            String filename = input.nextLine();
            try {
               calculator.save(filename);
            } catch(IOException e) {
               System.out.println("Invalid file");
            }
         } else if (command.equalsIgnoreCase("quit")) {
            quit = true;
         }
      }
      
      System.out.println("Backing up...");
      try {
         calculator.save("backup.txt");
         System.out.println("Thank you! ( ˶ˆ꒳ˆ˵ )");
      } catch(Exception e) {
         System.out.println("Backup failed");
      }

   
   }
   
   public static Calculator initialize(Scanner input) throws FileNotFoundException {
      
      
      System.out.print("Name of file: ");
      currentFileName = input.nextLine();
      
      File file = new File(currentFileName);
      return new Calculator(file);
   }
}