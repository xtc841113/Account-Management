package ntut.csie.accountManagementService.useCase.user.registeruser;

import ntut.csie.accountManagementService.useCase.Output;

public interface RegisterUserOutput extends Output{
	public String getUserId();
	
	public void setUserId(String userId);
}
