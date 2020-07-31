package ntut.csie.accountManagementService.useCase.user.chnagenickname;

import ntut.csie.accountManagementService.useCase.Output;

public interface ChangeNicknameOutput extends Output {
    public String getUserId();

    public void setUserId(String userId);
}
