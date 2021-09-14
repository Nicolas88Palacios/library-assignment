import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.*;

public class BookList {
  static Scanner sc = new Scanner(System.in);
  static boolean loop = true;
  private static final String url = "jdbc:mysql://localhost:3306/library";
  private static final String user = "root";
  private static final String password = "Chico88.root.03";
  static Statement sqlStatement = null;
  static ResultSet rs = null;


    public static void main(String[] args) throws SQLException {
      
        try ( Connection connection = DriverManager.getConnection(url, user, password)){ // Gör man så här så stänger anslutningen sig själv!
            System.out.println("Connected");

            sqlStatement = connection.createStatement();

            while(loop) {
              switchList(menu());
            }
            
        }catch (SQLException e){
        System.err.println(e);
        }
    }

  public static void switchList(int choise) {
    switch (choise) {
      case 1:
        Library.addbok();
        break;
      case 2:
        Library.removeBookById();
        break;
      case 3:
        Library.removeBookByName();
        break;
      case 4:
        Library.showBookById();
        break;
      case 5:
        Library.showAllBooks();
        break;
      case 6:
        Library.updateBookName();
        break;
      case 7:
        Library.numberOfBooks();
        break;
      case 0: 
        loop = false;
        break;
      default:
      System.out.println("Du måste välja en siffra mellan 0-7!");
    }
  }

  public static boolean isValid(int choice) {
    if (choice >= 0 && choice <= 7) {
      return true;
    } else {
      System.out.println("Du måste välja en siffra mellan 0-7.");
      return false;
    }
  }

  public static int menu() {
    int choice = 0;

    do {
      System.out.println("\n\n1. Lägg till en bok");
      System.out.println("2. Ta bort bok genom id");
      System.out.println("3. Ta bort bok genom namn");
      System.out.println("4. Visa bok genom id");
      System.out.println("5. Visa alla böcker");
      System.out.println("6. Uppdatera bok genom namn");
      System.out.println("7. Antal böcker");
      System.out.println("0. Avsluta");
      System.out.println("8. visa författare");
      System.out.println("\nGör ett val!");

      try {
        choice = sc.nextInt();
      } catch (Exception e) {
        System.out.println("Måste skriva en siffra!");
        choice = -1;
      }

      sc.nextLine();
    } while (!isValid(choice));
      
    return choice;
  }
}
