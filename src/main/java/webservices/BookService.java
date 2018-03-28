package webservices;

import beans.BeanI.BookBeanI;
import com.google.gson.Gson;
import entities.Book;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Path("/book")
public class BookService extends Base{
    @EJB
    private BookBeanI bookBeanI;
    Gson gson=new Gson();
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBook(String jsonObject){
        if(bookBeanI.create(gson.fromJson(jsonObject,Book.class))){
            return Response.status(200).entity("book created").build();
        }else{
            return Response.status(400).entity("book not created").build();
        }

    }
    @Path("/remove/{bookid}")
    @PUT
    @Consumes
    @Produces
    public  Response removeBook(@PathParam("bookid")int bookid){
        Book book=new Book();
        book.setBookid(bookid);
        if(bookBeanI.remove(book)){
            return Response.status(200).entity("book removed").build();
        }else {
            return Response.status(400).entity("book not removed").build();
        }

    }
}
