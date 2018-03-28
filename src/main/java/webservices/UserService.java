package webservices;

import beans.BeanI.UserBeanI;
import entities.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Path("/user")
public class UserService extends Base {
    @EJB
    private UserBeanI userBeanI;
    User user = new User();

    @Path("/registration")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(@FormParam("idNumber") int idNumber, @FormParam("pass") String password) {
        user.setIdNumber(idNumber);
        user.setPassword(password);
        if (userBeanI.create(user)) {
            return Response.status(200).entity(user.getIdNumber() + "  you are successfully registered").build();
        } else {
            return Response.status(400).entity("Registration unsuccessful").build();
        }
    }
    @Path("/edit")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response editUser(@FormParam("idNumber")int idNumber,@FormParam("pass")String password) {
    user.setPassword(password);
    if (userBeanI.edit(user)) {
        return Response.status(200).entity("password successfully updated").build();
    } else {
        return Response.status(400).entity("update unsuccessfull").build();
    }
}
    @Path("/find/{idNumber}")
    @GET
    @Consumes
    @Produces
    public Response findUserById(@PathParam("idNumber")int id){
        if(userBeanI.find(user)!=null){
            return Response.status(200).entity(user.getIdNumber()+"  found").build();
        }else{
            return Response.status(400).entity("User not found").build();
        }

    }
    @Path("/delete/{idNumber}")
    @PUT
    @Consumes
    @Produces
    public Response removeUser(@PathParam("idNumber")int id){
        if(userBeanI.remove(user)){
            return Response.status(200).entity("User   "+user.getIdNumber()+"  successfully removed").build();
        }else{
            return Response.status(400).entity("removal failed").build();
        }

    }
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(@FormParam("idNumber")int id,@FormParam("pass")String password){
      user.setIdNumber(id);
      user.setPassword(password);
      if(userBeanI.login(user)){
          return Response.status(200).entity(user.getIdNumber()+"  successfully logged in").build();
      }else{
          return Response.status(400).entity("Login unsuccessful").build();
      }
    }


}
