package webservices;

import beans.BeanI.ReaderBeanI;
import com.google.gson.Gson;
import entities.Book;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Path("/reader")
public class ReaderService extends Base {
    @EJB
    private ReaderBeanI readerBeanI;
    Gson gson=new Gson();
    Book book=new Book();
    @Path("/search/{bookid}")
    @GET
    @Consumes
    @Produces
    public Response searchBook(@PathParam("bookid")int bookid) {
        book.setBookid(bookid);
        if (readerBeanI.searchBook(book) != null) {
            return Response.status(200).entity("book found").build();
        }else {
            return Response.status(400).entity("Error!!").build();
        }
    }
    @Path("/reserve")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public Response reserveBook(String jsonObject){
        if(readerBeanI.reserveBook(gson.fromJson(jsonObject,Book.class))){
            return Response.status(200).entity("reserved").build();
        }else {
            return Response.status(400).entity("not reserved").build();
        }
    }

}
