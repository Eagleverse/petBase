package vbasic.prjpetdatabase;
import java.util.Scanner;
/**
 *
 * @author kaifa
 */

public class PrjPetDatabase {

  static Pet[] pets = new Pet[100];

  static Scanner input = new Scanner(System.in);
  static int petCount = 0;
  public static void main(String[] args) {

    int choice = 0;
    // TODO Auto-generated method stub
    System.out.println("Pet Database.");
    while (choice != 7) {
      System.out.println("What would you like to do?");
      System.out.println("1) View all pets\n2) Add more pets\n7) Exit program");
      //\n3) Update an existing pet\n4) Remove an existing pet\n5) Search pets by name\n6) Search pets by age
      System.out.print("Your choice: ");
      choice = input.nextInt();
      switch (choice) {
      case 7:
        break;
      case 1:
        PrjPetDatabase.showAllPets();
        break;
      case 2:
        PrjPetDatabase.addPets();
        break;
      case 3:
        //      PrjPetDatabase.updatePet();
        break;
      case 4:
        //    PrjPetDatabase.removePet();
        break;
      case 5:
        //  PrjPetDatabase.searchPetsByName();
        break;
      case 6:
        // PrjPetDatabase.searchPetsByAge();
        break;
      }

    }
    System.out.println("Goodbye!");
  }

  private static void addPets() {
    int localPetCount = 0;
    System.out.println("...");
    String a = "";
    String b = "";

    String[] temp = new String[2];
    while (a != "done") {
      System.out.print("Add pet (name,age): ");
      a = input.next();
      if (a.equalsIgnoreCase("done")) {
        break;
      }
      b = input.next();
      String CurrPet = a + " " + b;
      temp = CurrPet.split(" ");
      String age = temp[1];
      pets[petCount] = new Pet(temp[0], age, petCount);
      petCount++;
      localPetCount++;
      //System.out.println("Added: " + CurrPet + ". PetCount at: " + petCount);
    }
    System.out.println(localPetCount + " pets added. Total: " + petCount);
  }

  private static void showAllPets() {
    printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {
        printTableRow(pets[count].getID(), pets[count].getName(), pets[count].getAge());
        count++;
      }
    }
    printTableFooter(count);
  }
  /*
    CODE TO UPDATE FOR M2
    
    private static void updatePet() {
        System.out.print("Enter the pet ID you want to update: ");
        int thisID = input.nextInt();
        String oldPet =pets[thisID].getName()+" "+pets[thisID].getAge();
        System.out.println("...");
      String a = "";
      String b = "";

      String[] temp = new String[2];
      while (a != "cancel") {
        System.out.println("Enter new name and new age:");
        a = input.next();
        if (a.equalsIgnoreCase("cancel")) {
          break;
        }
        b = input.next();
        String CurrPet = a + " " + b;
        temp = CurrPet.split(" ");
        String age = temp[1];
        //Override with new pet object
        pets[thisID] = new Pet(temp[0], age, thisID);
        System.out.println(oldPet+" Changed to "+pets[thisID].getName()+" "+pets[thisID].getAge()+".");
        break;
      }
    }
    private static void removePet(){
        Pet[] pets2 = new Pet[100];
        System.out.print("Enter the pet ID you want to remove: ");
        int thisID = input.nextInt();
        String oldPet =pets[thisID].getName()+" "+pets[thisID].getAge();
        pets[thisID]=null;
        System.out.print(oldPet+" was removed.");
        printTableHeader();
      int count = 0;
      for (Pet i: pets) {
        if (i != null) {
          pets2[count]=i;
          i.setID(count);
          printTableRow(pets2[count].getID(), pets2[count].getName(), pets2[count].getAge());
          count++;
        }
      }
      printTableFooter(count);
      //Replace with new array
      pets = pets2;
        
    }
   
    private static void searchPetsByName() {
      System.out.print("Insert name to Search: ");
      String query = input.next();
      printTableHeader();
      int count = 0;
      for (Pet i: pets) {
        if (i != null) {

          if (i.getName().equals(query)) {
            printTableRow(i.getID(), i.getName(), i.getAge());
            count++;
          }
        }
      }

      printTableFooter(count);

    }

    private static void searchPetsByAge() {
            System.out.print("Enter age to search: ");
      String query = input.next();
      printTableHeader();
      int count = 0;
      for (Pet i: pets) {
        if (i != null) {

          if (i.getAge().equals(query)) {
            printTableRow(i.getID(), i.getName(), i.getAge());
            count++;
          }
        }
      }

      printTableFooter(count);

    }
  */
  private static void printTableHeader() {
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");
    System.out.printf("|%-3s|%-10s|%-4s|\n", "ID", "NAME", "AGE");
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");
  }

  private static void printTableRow(int id, String name, String age) {
    System.out.printf("|%-3s|%-10s|%-4s|\n", id, name, age);

  }

  private static void printTableFooter(int rowCount) {
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");

    System.out.println(rowCount + " rows in set.");
  }
}