
public class GettersAndSetters {
  private int id;
  static int idGenerator = 1;
  private String bookTitle;
  private String authorName; 
  // private int authorId; ---Flyttas till Author class?
  // static int authorIdGenerator = 1;
  private String bookISBN;
  private String published;
  private int rating;
  

  public GettersAndSetters(String title, String authorName, String bookISBN, String published, int rating, int b) {
    this.id = idGenerator;
    idGenerator++;
    // this.authorId = authorIdGenerator;
    // authorId++;
    this.bookTitle = title;
    this.authorName = authorName;
    this.bookISBN = bookISBN;
    this.published = published;
    this.rating = rating;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
    //Flyttas till author class??
//   public int getAuthorId() {
//     return authorId;
// }

// public void setAuthorId(int authorId) {
//     this.authorId = authorId;
// }
  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String title) {
    this.bookTitle = title;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  
  public String getBookISBN() {
    return bookISBN;
  }

  public void setBookISBN(String bookISBN) {
    this.bookISBN = bookISBN;
  }
  
  public String getPublished() {
    return published;
}

public void setPublished(String published) {
    this.published = published;
}

    public int getRating() {
      return rating;
  }

  public void setRating(int rating) {
      this.rating = rating;
  }


  @Override
  public String toString() {
    return "Book{" + "id=" + id + ", name=" + bookTitle + ", authorName=" + authorName + "bookISBN=" + bookISBN + "Skriven: " + published + "Betyg:" + rating + '}';
  }
}