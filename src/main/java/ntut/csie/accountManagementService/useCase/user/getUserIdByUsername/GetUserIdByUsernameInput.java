package ntut.csie.accountManagementService.useCase.user.getUserIdByUsername;

import ntut.csie.accountManagementService.useCase.Input;

public interface GetUserIdByUsernameInput extends Input {

    public String getUsername();

    public void setUsername(String username);
}
