package ntut.csie.accountManagementService.useCase.user.loginUser;

import ntut.csie.accountManagementService.useCase.Output;

public interface LoginUserOutput extends Output {
	
	public String getUserId();
	
	public void setUserId(String userId);
	
}
