package ntut.csie.accountManagementService.useCase.user.changeemail;

import ntut.csie.accountManagementService.useCase.Input;

public interface ChangeEmailInput extends Input {
    public String getUserId();

    public void setUserId(String userId);

    public String getPassword();

    public void setPassword(String password);

    public String getNewEmail();

    public void setNewEmail(String newEmail);
}
