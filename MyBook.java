import java.util.*;
import java.sql.*;

public class MyBook {
  static Scanner sc = new Scanner(System.in);
  static ArrayList<GettersAndSetters> books = new ArrayList<>();
  static int minLength = 0;
  static int maxLength = 0;
  static Statement sqlStatement = null;

  public static void addbok() {
    String bookTitle;
    String authorName;
    String bookISBN;
    String published;
    int rating = 10;
    int bookId = 2;
    int authorId = 2;
    String userEmail = "nicolas88palacios@gmail.com";
    try {
      do {
        System.out.println("Ange boktitel: \n");
        System.out.println("Skriv in minst ett tecken");
        bookTitle = sc.nextLine();
      } while (minimumLengthCheck(bookTitle, minLength) != true);

      do {
        System.out.println("Ange författarens för- och efternamn: \n");
        System.out.println("Skriv in minst ett tecken");
        authorName = sc.nextLine();
      } while (minimumLengthCheck(authorName, minLength) != true);
      // sqlStatement.executeUpdate("INSERT INTO book " + bookName.toLowerCase() + "
      // VALUES (title)");
      // sqlStatement.executeUpdate("INSERT INTO book " + authorName.toLowerCase() + "
      // VALUES (Author)");
      do {
        System.out.println("Skriv in bokens ISBN");
        bookISBN = sc.nextLine();

        if (minimumLengthCheck(bookISBN, minLength = 12) != true) {
          System.out.println("Du måste skriva 13 siffror");
        }
        if (maxLengthCheck(bookISBN, maxLength = 14) != true) {
          System.out.println("Du har skrivit mer än 13 siffror");
        }
      } while (minimumLengthCheck(bookISBN, minLength) != true || maxLengthCheck(bookISBN, maxLength) != true);

      do {
        System.out.println("När skrevs boken?");
        System.out.println("Skriv in minst ett tecken");
        published = sc.nextLine();
      } while (published == null);

      // GettersAndSetters book = new GettersAndSetters(bookISBN,bookId,authorId,
      // published,bookTitle.toLowerCase(), rating, userEmail);//
      // authorName.toLowerCase()
      // books.add(book);
      try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
          "Chico88.root.03")) {
        Statement st = connection.createStatement();

        // String sql = "INSERT INTO book(isbn, book_id, auhtor_id, published, title,
        // rating,user_email)"
        // +"VALUES ('bookISBN', bookId, authorId, published, bookTitle, rating,
        // userEmail)";
        st.executeUpdate("INSERT INTO `book`(isbn, book_id, auhtor_id, published, title, rating,user_email) VALUE ('"
            + bookISBN + "','" + bookId + "','" + authorId + "'," + published + ",'" + bookTitle + "'," + rating + ",'"
            + userEmail + "')");
        System.out.println("Boken är skapad!");
      } catch (SQLException e) {
        System.err.println(e);
      }

    } catch (Exception e) {
      System.out.println("Något blev fel");
      sc.nextLine();
    }
  }

  private static boolean minimumLengthCheck(String userInput, int inputMinLength) {
    return inputMinLength < userInput.length();
  }

  private static boolean maxLengthCheck(String userInput, int userInputMaxLength) {
    return userInputMaxLength > userInput.length();
  }

  public static String capitalize(String text) {
    char[] charArray = text.toCharArray();
    boolean isSpace = true;

    for (int i = 0; i < charArray.length; i++) {
      if (Character.isLetter(charArray[i])) {
        if (isSpace) {
          charArray[i] = Character.toUpperCase(charArray[i]);
          isSpace = false;
        }
      } else {
        isSpace = true;
      }
    }

    return String.valueOf(charArray);
  }

  public static void showAllBooks() {

    for (GettersAndSetters book : books) {

      System.out.println("********************");
      System.out.println("Boktitel: " + capitalize(book.getBookTitle()));
      System.out.println("Författare: " + capitalize(book.getAuthorName()));
      System.out.println("ISBN: " + book.getBookISBN());
      System.out.println("********************");
    }
  }

  public static GettersAndSetters getBookById(int bookId) {

    for (GettersAndSetters book : books) {
      if (book.getId() == bookId) {
        return book;
      }
    }
    return null;
  }

  public static void showBookById() {
    int bookId = 0;

    do {
      System.out.println("Hitta bok via id");
      try {
        bookId = sc.nextInt();
        sc.nextLine();
        GettersAndSetters book = getBookById(bookId);

        if (book == null) {
          System.out.println("Boken kunde inte hittas!");
        } else {
          System.out.println("********************");
          System.out.println("Boktitel: " + capitalize(book.getBookTitle()));
          System.out.println("Författare: " + capitalize(book.getAuthorName()));
          System.out.println("********************");
        }

      } catch (Exception e) {
        System.out.println("Du måste skriva in siffror!");
        sc.nextLine();
      }
    } while (getBookById(bookId) == null);
  }

  public static void updateBookName() {

    try {
      System.out.println("Ange bokID för att ändra boktitel:");
      int id = sc.nextInt();
      sc.nextLine();

      for (GettersAndSetters book : books) {
        if (book.getId() == id) {
          System.out.println("Ange ny boktitel");
          String newName = sc.nextLine();

          book.setBookTitle(newName);
          System.out.println("Boktitel uppdaterad");
          System.out.println("********************");
          System.out.println("Boktitel: " + capitalize(book.getBookTitle()));
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
      sqlStatement.executeUpdate("DELET FROM book WHERE Book_id = " + bookId + " VALUES (Author)");// -------------------------->
                                                                                                   // HÄR ÄR DET ÄNDRAT
      for (int i = 0; i < books.size(); i++) {
        if (books.get(i).getId() == bookId) {
          books.remove(books.get(i));
          System.out.println("Boken är nu borta");
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

  public static void removeBookByName() {
    System.out.println("Ange boktitel för att radera bok");
    String bookName = sc.nextLine();
    // int result = sqlStatement.executeUpdate("DELETE FROM course WHERE Book_title
    // =" + bookName + ";"); --------------------------------- fixa!!! du är här!!!!
    bookName.toLowerCase();

    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getBookTitle().equals(bookName)) {
        books.remove(books.get(i));
        System.out.println("Boken är nu borta");
      }
    }
  }

  public static void published() {
    String date;
    System.out.println("Skriv in när boken skrevs: ");
    date = sc.nextLine();
    System.out.println(date);
  }
}
