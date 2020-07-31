package ntut.csie.accountManagementService.useCase.user.chnagenickname;

import ntut.csie.accountManagementService.useCase.Input;

public interface ChangeNicknameInput extends Input {
    public String getUserId();

    public void setUserId(String userId);

    public String getPassword();

    public void setPassword(String password);

    public String getNewNickname();

    public void setNewNickname(String newNickname);
}
