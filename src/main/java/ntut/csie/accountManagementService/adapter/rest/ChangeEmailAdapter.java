package ntut.csie.accountManagementService.adapter.rest;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangeEmailPresenter;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangePasswordPresenter;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailInput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordInput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class ChangeEmailAdapter {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();

	@PUT
	@Path("/{userId}/email")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeEmail(@PathParam("userId") String userId, String userInfo) {
		String password = "";
		String newEmail = "";
		
		try {
			JSONObject userJSON = new JSONObject(userInfo);
			password = userJSON.getString("password");
			newEmail = userJSON.getString("newEmail");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ChangeEmailUseCase userCase = applicationContext.newChangeEmailUseCase();
		ChangeEmailInput input = (ChangeEmailInput) userCase;
		input.setUserId(userId);
		input.setPassword(password);
		input.setNewEmail(newEmail);

		ChangeEmailPresenter presenter = new ChangeEmailPresenter();

		userCase.execute(input, presenter);

		if (!presenter.getUserId().isEmpty()) {
			return Response.status(Response.Status.OK).entity(presenter.buildViewModel()).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodeHandler.POST).build();
	}
	

}
