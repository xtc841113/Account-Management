package ntut.csie.accountManagementService.adapter.rest;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangePasswordPresenter;
import ntut.csie.accountManagementService.adapter.presenter.user.RegisterUserPresenter;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordInput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordOutput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserInput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class ChangePasswordAdapter {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();

	@PUT
	@Path("/{userId}/password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@PathParam("userId") String userId, String userInfo) {
		String oldPassword = "";
		String newPassword = "";
		
		try {
			JSONObject userJSON = new JSONObject(userInfo);
			oldPassword = userJSON.getString("oldPassword");
			newPassword = userJSON.getString("newPassword");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ChangePasswordUseCase useCase = applicationContext.newChangePasswordUseCase();
		ChangePasswordInput input = (ChangePasswordInput) useCase;
		input.setUserId(userId);
		input.setOldPassword(oldPassword);
		input.setNewPassword(newPassword);

		ChangePasswordPresenter presenter = new ChangePasswordPresenter();

		useCase.execute(input, presenter);

		if (!presenter.getUserId().isEmpty()) {
			return Response.status(Response.Status.OK).entity(presenter.buildViewModel()).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodeHandler.POST).build();
	}
	

}
