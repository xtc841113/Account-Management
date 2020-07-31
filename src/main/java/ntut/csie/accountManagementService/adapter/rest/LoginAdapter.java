package ntut.csie.accountManagementService.adapter.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.accountManagementService.adapter.presenter.user.LoginUserPresenter;
import org.json.JSONException;
import org.json.JSONObject;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserInput;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserUseCase;

@Path("/users")
public class LoginAdapter {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private LoginUserUseCase loginUserUseCase = applicationContext.newLoginUserUseCase();

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String loginInfo) {
		String username = "";
		String password = "";

		try {
			JSONObject userJSON = new JSONObject(loginInfo);
			username = userJSON.getString("username");
			password = userJSON.getString("password");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		LoginUserInput input = (LoginUserInput) loginUserUseCase;
		input.setUsername(username);
		input.setPassword(password);
		
		
		LoginUserPresenter presenter = new LoginUserPresenter();
		
		loginUserUseCase.execute(input, presenter);
		if (!presenter.getUserId().isEmpty()) {
			return Response.status(Response.Status.CREATED).entity(presenter.buildViewModel()).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodeHandler.POST).build();
	}

}
