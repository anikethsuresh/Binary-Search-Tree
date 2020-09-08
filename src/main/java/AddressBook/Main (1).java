package src.main.java.HW2;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class  Main {

    public static void main(String[] args) {
       Table addressBook=new Table();

       Scanner scanner = new Scanner(System.in);
       boolean loop = true;
        System.out.println("Do you want to open a file? (y/n)?");
        String openFile=scanner.nextLine();
        if (openFile.equals("y")) {
            System.out.println("Please enter the name of the file: ");
            String fileName = scanner.nextLine();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String name = null;
                String address = null;
                while ((name = reader.readLine()) != null && (address = reader.readLine()) != null) {
                    addressBook.insert(name, address);
                }
            } catch (IOException e ) {
                System.out.println(fileName+ " file not found or unable to be opened.");
            }

        }
       while (loop) {
           System.out.println("\nAdd a name (n)\n" +
                   "Look up a name (l)\n" +
                   "Update address (u)\n" +
                   "Delete an entry (d)\n" +
                   "Display all entries (a)\n" +
                   "Save to file (s)\n"+
                   "Quit (q)\n");
           String userInput= scanner.nextLine();
           switch(userInput){
               case "n":
                   System.out.print("Name: ");
                   String key = scanner.nextLine();
                   if(addressBook.lookUp(key)==null) {
                       System.out.print("Address: ");
                       String value = scanner.nextLine();
                       addressBook.insert(key, value);
                   } else {
                       System.out.println("Name already exists. \n");
                   }
                   break;
               case "l":
                   System.out.print("Name: ");
                   String lookup = scanner.nextLine();
                   String result = addressBook.lookUp(lookup);
                   if (result!=null) {
                       System.out.println("Address is "+result);
                   } else {
                       System.out.println(lookup+" is not in the address book.");
                   }
                   break;
               case "u":
                   System.out.print("Name: ");
                   String update = scanner.nextLine();
                   if (addressBook.lookUp(update)!=null) {
                       System.out.println("Old address is "+addressBook.lookUp(update));
                       System.out.print("New address :");
                       String newAddress = scanner.nextLine();
                       addressBook.update(update,newAddress);
                   } else {
                       System.out.println(update+" is not in the address book.");
                   }
                   break;
               case "d":
                   System.out.print("Name to delete: ");
                   String del = scanner.nextLine();
                    if (addressBook.delete(del)==false) {
                        System.out.println(del+" is not in the address book.");
                    }
                   break;
               case "a":
                   addressBook.displayAll();
                   break;
               case "s":
                   addressBook.save();
                   break;
               case "q":
                   loop=false;
                   break;
               default:
                   break;

           }


       }

    }
}