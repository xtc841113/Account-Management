package ntut.csie.accountManagementService.useCase.user.loginUser;

import ntut.csie.accountManagementService.useCase.Input;

public interface LoginUserInput extends Input {

	public String getUsername();

	public void setUsername(String username);
	
	public String getPassword();

	public void setPassword(String password);
}
