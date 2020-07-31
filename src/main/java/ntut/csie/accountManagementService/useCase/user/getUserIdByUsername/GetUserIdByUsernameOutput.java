package ntut.csie.accountManagementService.useCase.user.getUserIdByUsername;

import ntut.csie.accountManagementService.useCase.Output;

public interface GetUserIdByUsernameOutput extends Output {

    public String getUserId();

    public void setUserId(String userId);
}
