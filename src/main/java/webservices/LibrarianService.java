package webservices;

import beans.BeanI.LibrarianBeanI;
import com.google.gson.Gson;
import entities.BookBorrow;
import entities.Librarian;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Path("/librarian")
public class LibrarianService extends Base {
    @EJB
    private LibrarianBeanI librarianBeanI;
    Gson gson=new Gson();
    Librarian librarian=new Librarian();
    @Path("/issue")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response issueBook(String jsonObject){
        if(librarianBeanI.issueBook(gson.fromJson(jsonObject, BookBorrow.class))){
            return Response.status(200).entity(" book successfully issued").build();
        }else{
            return Response.status(400).entity("issue was not successful").build();
        }
    }
    @Path("/return")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response returnBook(String jsonObject){
        if(librarianBeanI.returnBook(gson.fromJson(jsonObject,BookBorrow.class))){
            return Response.status(200).entity(" book successfully returned").build();
        }else{
            return Response.status(400).entity(" book return not successful").build();
        }
    }
    @Path("/report/{idNumber}")
    @GET
    @Consumes
    @Produces
    public Response viewReaderHistory(@PathParam("idNumber")int id){
        BookBorrow bookBorrow=new BookBorrow();
        if(librarianBeanI.viewReaderHistory(bookBorrow)!=null){
            return Response.status(200).entity("report found").build();
        }else{
            return Response.status(400).entity(" report not found").build();
        }
    }
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public  Response createLibrarian(String jsonObject){
        if(librarianBeanI.create(gson.fromJson(jsonObject,Librarian.class))){
            return Response.status(200).entity(" librarian successfully added").build();
        }else{
            return Response.status(400).entity("librarian not added").build();
        }
    }
    @Path("/overduebooks")
    @GET
    @Produces
    @Consumes
    public Response viewOverdueBooks(){
        if(librarianBeanI.overdueReport()!=null){
            return Response.status(200).entity(" success!!").build();
        }else{
            return Response.status(400).entity("unsuccessfull").build();
        }

    }
}
