import java.util.Scanner;
import java.util.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class Library {
  static Scanner sc = new Scanner(System.in);
  static ArrayList<Book> books = new ArrayList<>();
  static int minLength = 0;
  static Statement sqlStatement = null;

  public static void addbok() {
    String bookName;
    String authorName;

    try {
      do {
          System.out.println("Ange boktitel: \n");
          System.out.println("Skriv in minst ett tecken");
          bookName = sc.nextLine();
      } while (lengthCheck(bookName, minLength) != true);

      do {
        System.out.println("Ange författarens för- och efternamn: \n");
        System.out.println("Skriv in minst ett tecken");
        authorName = sc.nextLine();
      } while (lengthCheck(authorName, minLength) != true);
          //Book book = new Book(bookName.toLowerCase(), authorName.toLowerCase());
          //books.add(book);
          sqlStatement.executeUpdate("INSERT INTO book " + bookName.toLowerCase() + " VALUES (Book_title)");
          sqlStatement.executeUpdate("INSERT INTO book " + authorName.toLowerCase() + " VALUES (Author)");

          System.out.println("Boken är skapad!");

      } catch (Exception e) {
        System.out.println("Något blev fel");
        sc.nextLine();
        }
  }


  private static boolean lengthCheck(String userIputBookname, int userInputBookNameMinLength) {
    return userInputBookNameMinLength < userIputBookname.length();
  }

  public static String capitalize(String text) {//-------------Läs på om character och is letter!
    char[] charArray = text.toCharArray();
    boolean isSpace = true;

    for(int i = 0; i < charArray.length; i++) {
      if(Character.isLetter(charArray[i])) {
        if(isSpace) {
          charArray[i] = Character.toUpperCase(charArray[i]);
          isSpace = false;
        }
      }
      else {
        isSpace = true;
      }
    }

    return String.valueOf(charArray);
  }


  public static void showAllBooks() {

    for (Book book : books) {

      System.out.println("********************");
      System.out.println("Boktitel: " + capitalize(book.getName()));
      System.out.println("Författare: " + capitalize(book.getAuthorName()));
      System.out.println("********************");
    }
  }

  public static Book getBookById(int bookId) {

    for (Book book : books) {
      if(book.getId() == bookId) {
        return book;
      }
    }
    return null;
  }

  public static void showBookById(){
    int bookId = 0;

    do {
      System.out.println("Hitta bok via id");
      try {
        bookId = sc.nextInt();
        sc.nextLine();
        Book book = getBookById(bookId);

        if(book == null) {
          System.out.println("Boken kunde inte hittas!");
        } else {
          System.out.println("********************");
          System.out.println("Boktitel: " + capitalize(book.getName()));
          System.out.println("Författare: " + capitalize(book.getAuthorName()));
          System.out.println("********************");
        }

      } catch (Exception e) {
        System.out.println("Du måste skriva in siffror!");
        sc.nextLine();
      }
    } while(getBookById(bookId) == null);
  }

  public static void updateBookName() {

    try {
      System.out.println("Ange bokID för att ändra boktitel:" );
      int id = sc.nextInt();
      sc.nextLine();

      for (Book book : books) {
        if(book.getId() == id) {
          System.out.println("Ange ny boktitel");
          String newName =  sc.nextLine();

          book.setName(newName);
          System.out.println("Boktitel uppdaterad");
          System.out.println("********************");
          System.out.println("Boktitel: " + capitalize(book.getName()));
          System.out.println("Författare: " + capitalize(book.getAuthorName()));
          System.out.println("********************");
        }
      }
    } catch (Exception e) {
            System.out.println("Du måste skriva in siffror!");
      sc.nextLine();
    } finally {
      System.out.println("Tryck enter för att gå vidare");
      sc.nextLine();
      System.out.println("Vad vill du göra nu?");
    }
  }

  public static void numberOfBooks() {
    System.out.println("Antal Böcker:" + books.size());
  }

  public static void removeBookById() {
    try {
      System.out.println("Ange bokID för att radera bok");
      int bookId = sc.nextInt();
      sqlStatement.executeUpdate("DELET FROM book WHERE Book_id = " + bookId + " VALUES (Author)");// --------------------------> HÄR ÄR DET ÄNDRAT
      for (int i =  0; i < books.size(); i++) {
        if (books.get(i).getId() == bookId) {
          books.remove(books.get(i));
          System.out.println("Boken är nu borta");
        }
      }
    } catch (Exception e) {
      System.out.println("Du måste skriva in siffror!");
      sc.nextLine();
    }finally {
      System.out.println("Tryck enter för att gå vidare");
      sc.nextLine();
      System.out.println("Vad vill du göra nu?");
    }
  }

  public static void removeBookByName() {
    System.out.println("Ange boktitel för att radera bok");
    String bookName = sc.nextLine();
    //int result = sqlStatement.executeUpdate("DELETE FROM course WHERE Book_title =" + bookName + ";"); --------------------------------- fixa!!! du är här!!!!
    bookName.toLowerCase();

    for (int i =  0; i < books.size(); i++) {
      if (books.get(i).getName().equals(bookName)) {
        books.remove(books.get(i));
        System.out.println("Boken är nu borta");
      }
    }
  }
}
