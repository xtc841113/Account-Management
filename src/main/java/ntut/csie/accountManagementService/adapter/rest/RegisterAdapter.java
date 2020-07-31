package ntut.csie.accountManagementService.adapter.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.accountManagementService.adapter.presenter.user.RegisterUserPresenter;
import org.json.JSONException;
import org.json.JSONObject;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserInput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Path("/users")
public class RegisterAdapter {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(String userInfo) {
		String username = "";
		String email = "";
		String password = "";
		String systemRole = "";
		String nickname = "";
		
		try {
			JSONObject userJSON = new JSONObject(userInfo);
			username = userJSON.getString("username");
			email = userJSON.getString("email");
			password = userJSON.getString("password");
			systemRole = userJSON.getString("systemRole");
			nickname = userJSON.getString("nickname");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		RegisterUserUseCase registerUserUseCase = applicationContext.newRegisterUseCase();
		RegisterUserInput input = (RegisterUserInput) registerUserUseCase;
		input.setUsername(username);
		input.setEmail(email);
		input.setPassword(password);
		input.setSystemRole(systemRole);
		input.setNickname(nickname);
		
		RegisterUserPresenter presenter = new RegisterUserPresenter();
		
		registerUserUseCase.execute(input, presenter);

		if (!presenter.getUserId().isEmpty()) {
			return Response.status(Response.Status.CREATED).entity(presenter.buildViewModel()).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodeHandler.POST).build();
	}
	

}
