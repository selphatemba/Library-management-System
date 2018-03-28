package entities;

import javax.persistence.*;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Book.search",query = "select b from Book b where b.bookid=:bookid")})
public class Book {
    @Id
    private int bookid;
    private String bookTitle;
    private int pageCount;
    private String isbn;
    private BookStatus bookStatus;
    @OneToOne
    private BookCategory bookCategory;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookTitle='" + bookTitle + '\'' +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                ", bookStatus=" + bookStatus +
                ", bookCategory=" + bookCategory +
                '}';
    }
}
