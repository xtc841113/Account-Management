package ntut.csie.accountManagementService.adapter.rest;

import ntut.csie.accountManagementService.ApplicationContext;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangeEmailPresenter;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangeNicknamePresenter;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailInput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameInput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameOutput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameUseCase;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@Path("/users")
public class ChangeNicknameAdapter {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();

	@PUT
	@Path("/{userId}/nickname")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeNickname(@PathParam("userId") String userId, String userInfo) {
		String password = "";
		String newNickname = "";
		
		try {
			JSONObject userJSON = new JSONObject(userInfo);
			password = userJSON.getString("password");
			newNickname = userJSON.getString("newNickname");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ChangeNicknameUseCase useCase = applicationContext.newChangeNicknameUseCase();
		ChangeNicknameInput input = (ChangeNicknameInput) useCase;
		input.setUserId(userId);
		input.setPassword(password);
		input.setNewNickname(newNickname);

		ChangeNicknamePresenter presenter = new ChangeNicknamePresenter();

		useCase.execute(input, presenter);

		assertEquals(userId, presenter.getUserId());

		if (!presenter.getUserId().isEmpty()) {
			return Response.status(Response.Status.OK).entity(presenter.buildViewModel()).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodeHandler.POST).build();
	}
	

}
