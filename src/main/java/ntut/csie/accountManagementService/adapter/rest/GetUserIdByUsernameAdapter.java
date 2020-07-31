package ntut.csie.accountManagementService.adapter.rest;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.adapter.presenter.user.GetUserIdByUsernamePresenter;
import ntut.csie.accountManagementService.useCase.user.getUserIdByUsername.GetUserIdByUsernameInput;
import ntut.csie.accountManagementService.useCase.user.getUserIdByUsername.GetUserIdByUsernameUseCase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class GetUserIdByUsernameAdapter {

    private ApplicationContext applicationContext = ApplicationContext.getInstance();
    private GetUserIdByUsernameUseCase getUserIdByUsernameUseCase = applicationContext.newGetUserIdByUserInformationUseCase();

    @GET
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response getUserIdByUsername(@PathParam("username") String username) {


        GetUserIdByUsernameInput input = (GetUserIdByUsernameInput) getUserIdByUsernameUseCase;
        input.setUsername(username);
        GetUserIdByUsernamePresenter presenter = new GetUserIdByUsernamePresenter();

        getUserIdByUsernameUseCase.execute(input, presenter);

        if (!presenter.getUserId().isEmpty()) {
            return Response.status(Response.Status.OK).entity(presenter.buildViewModel()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(ErrorCodeHandler.GET).build();

    }


}
