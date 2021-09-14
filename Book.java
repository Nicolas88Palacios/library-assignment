public class Book {
  private int id;
  private String bookTitle;
  private String authorName; 
  static int idGenerator=1;

  public Book(String bookTitle, String authorName) {
    this.id=idGenerator;
    idGenerator++;
    this.bookTitle = bookTitle;
    this.authorName = authorName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return bookTitle;
  }

  public void setName(String name) {
    this.bookTitle = name;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  @Override
  public String toString() {
    return "Book{" + "id=" + id + ", name=" + bookTitle + ", authorName=" + authorName + '}';
  }
}