package vbasic.prjpetdatabase;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PrjPetDatabase {

  static Pet[] pets = new Pet[5]; //Database limit

  static Scanner input = new Scanner(System.in);
  static int petCount = 0;
  public static void main(String[] args) {

    int choice = 0;
    // TODO Auto-generated method stub
    System.out.println("Pet Database.");
    loadPet();
    while (choice != 7) {
      System.out.println("What would you like to do?");
      System.out.println("1) View all pets\n2) Add more pets\n3) Search pets by name\n4) Search pets by age\n5) Exit program");
      System.out.print("Your choice: ");
      choice = input.nextInt();
      switch (choice) {
      case 5 -> {
            }
      case 1 -> PrjPetDatabase.showAllPets();
      case 2 -> PrjPetDatabase.addPets();
      case 3 -> PrjPetDatabase.searchPetsByName();
      case 4 -> PrjPetDatabase.searchPetsByAge();
      }

    }
    savePet();
    System.out.println("Goodbye!");
  }

  private static void addPets() {
    int localPetCount = 0;
    System.out.println("...");
    String a = "";
    String b = "";

    String[] temp = new String[2];
    while (!"done".equals(a)) {
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
      System.out.println("Added: " + CurrPet + ". PetCount at: " + petCount);
    }
    System.out.println(localPetCount + " pets added. Total: " + petCount);
  }

  private static void loadPet(){
      try {
      File loadFile = new File("pet.txt");
          try (Scanner myReader = new Scanner(loadFile)) {
              while (myReader.hasNextLine()) {
                  String[] data = myReader.nextLine().split(",");
                 // System.out.println(data[0]+" who is "+data[1]); 
                  pets[petCount] = new Pet(data[0], data[1], petCount);
                  petCount++;
              }   }
    } catch (FileNotFoundException e) {
      System.out.println("Whoops. Let's try again.");
    }
  }
  
  private static void savePet(){
      try {
          try (FileWriter fw = new FileWriter("pet.txt")) {
              for(Pet entry : pets){
                  if(entry != null){
                      fw.write(entry.getName()+","+entry.getAge()+"\n");
                  }
              }}

    } catch (IOException ex) {
        System.out.println("Whoops. Let's try again.");
      }
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

  private static void searchPetsByName() {
    System.out.print("Insert a name to Search: ");
    String query = input.next();
    printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {

        if (i.getName().equalsIgnoreCase(query)) {
          printTableRow(i.getID(), i.getName(), i.getAge());
          count++;
        }
      }
    }

    printTableFooter(count);

  }

  private static void searchPetsByAge() {
    System.out.print("Insert an age to search: ");
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
  /*
LEGACY  
  
         ID DUPLICATION BUG
      case 3 -> {
          PrjPetDatabase.showAllPets();
          System.out.println();
          PrjPetDatabase.updatePet();
            }
      case 4 -> {
          PrjPetDatabase.showAllPets();
          System.out.println();
          PrjPetDatabase.removePet();
            }
  
  private static void updatePet() {
    System.out.print("Enter the pet ID to update: ");
    int thisID = input.nextInt();
    String oldPet = pets[thisID].getName() + " " + pets[thisID].getAge();
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
      System.out.println(oldPet + " Changed to " + pets[thisID].getName() + " " + pets[thisID].getAge() + ".");
      break;
    }
  }
  private static void removePet() {
      //reset PetCount for new list
      petCount = 0;
    Pet[] pets2 = new Pet[5];
    System.out.print("Enter the pet ID to remove: ");
    int thisID = input.nextInt();
    String oldPet = pets[thisID].getName() + " " + pets[thisID].getAge();
    Pet delPet = pets[thisID];
    System.out.print(oldPet + " was removed.");
    
    for(int i=0, k=0;i<5;i++){
            if(pets[i]!=delPet & pets[i]!=null){
                pets2[k]=pets[i];
                System.out.println("Meow");
                       
                k++;
                petCount++;
            }
        }


    pets = pets2;

  }
*/
}