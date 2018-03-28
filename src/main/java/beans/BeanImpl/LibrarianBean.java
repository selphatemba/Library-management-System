package beans.BeanImpl;

import beans.BeanI.LibrarianBeanI;
import dao.daoI.Crud;
import dao.daoI.CrudA;
import dao.daoImpl.BookBorrowDao;
import dao.daoImpl.LibrarianDao;
import dao.daoImpl.ReaderDao;
import entities.Book;
import entities.BookBorrow;
import entities.Librarian;
import entities.Reader;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static dao.daoI.CrudA.Entity.BOOK;
import static dao.daoI.CrudA.Entity.BOOKBORROW;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Stateless
public class LibrarianBean implements LibrarianBeanI {
    @Inject
    @CrudA(choice = BOOKBORROW)
    private Crud crud;
    @Inject
    @CrudA(choice = BOOK)
    private Crud crud1;
    Book book=null;
    Reader reader=null;


    public LibrarianBean() {
        book=new Book();
        reader=new Reader();
    }

    public boolean issueBook(BookBorrow bookBorrow) {
        if (crud1.read(book) != null) {//check if book isINDB
            if (book.getBookStatus().equals("Available")) {//check if book is available
                if (reader.getIdNumber() != 0) {//check if the reader isINDB
                    try {
                        crud.create(bookBorrow);//borrow book
                        crud1.update(book);//update book status to borrowed
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }return true;
    }

    public boolean returnBook(BookBorrow bookBorrow) {
        if(crud.read(book)!=null){//check if book is borrowed
            try {
                crud.update(bookBorrow);//return book
                crud1.update(book);//update book status to available
            }catch (Exception p){
                p.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public List<BookBorrow> viewReaderHistory(BookBorrow bb) {
        ReaderDao readerDao = new ReaderDao();
        List<BookBorrow> listA = new ArrayList<BookBorrow>();
        if (bb.getReader() != null) {//check that the reader is captured
            try {
                listA = readerDao.readersReport(bb);
            } catch (Exception p) {
                p.printStackTrace();
            }
        }
        return listA;
    }

    public List<Book> overdueReport() {
        List<Book>overdueBooks=new ArrayList<Book>();
        try {
            BookBorrowDao bookBorrowDao=new BookBorrowDao();
            overdueBooks=bookBorrowDao.OverdueReport();
        }catch (Exception p){
            p.printStackTrace();
        }
        return overdueBooks;
    }


    public boolean create(Librarian librarian) {
        if (crud.read(librarian)==null){
            return crud.create(librarian);
        }
        return true;
    }

    public Librarian find(Librarian librarian) {
        Librarian lib=null;
        lib= (Librarian) crud.read(librarian);
        return  lib;
    }

    public boolean edit(Librarian librarian) {
        return crud.update(librarian);
    }

    public boolean remove(Librarian librarian) {
        return crud.delete(librarian);
    }




}
