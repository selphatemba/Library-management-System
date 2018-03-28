package entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Entity
@NamedQuery(name="BookBorrow.findOverdueBooks",query = "select bb from BookBorrow bb where bb.returnDate>bb.dueDate")
public class BookBorrow {
    @Id
    private int borrowID;
    @OneToOne
    private Librarian librarian;
    @OneToOne
    private Reader reader;
    @OneToOne
    private Book book;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private  double amountDue;



    public int getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
