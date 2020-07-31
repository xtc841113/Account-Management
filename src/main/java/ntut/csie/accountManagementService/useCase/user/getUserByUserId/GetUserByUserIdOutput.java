package ntut.csie.accountManagementService.useCase.user.getUserByUserId;

import ntut.csie.accountManagementService.useCase.Output;
import ntut.csie.accountManagementService.useCase.UserDto;

public interface GetUserByUserIdOutput extends Output {
	
	public UserDto getUser();
	
	public void setUser(UserDto userDto);
}
