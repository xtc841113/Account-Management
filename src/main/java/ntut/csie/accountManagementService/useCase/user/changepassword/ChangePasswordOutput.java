package ntut.csie.accountManagementService.useCase.user.changepassword;

import ntut.csie.accountManagementService.useCase.Output;

public interface ChangePasswordOutput extends Output {
    public String getUserId();

    public void setUserId(String userId);
}
