package ntut.csie.accountManagementService.useCase.user.changepassword;

import ntut.csie.accountManagementService.useCase.Input;

public interface ChangePasswordInput extends Input {
    public String getUserId();

    public void setUserId(String userId);

    public String getOldPassword();

    public void setOldPassword(String oldPassword);

    public String getNewPassword();

    public void setNewPassword(String newPassword);
}
