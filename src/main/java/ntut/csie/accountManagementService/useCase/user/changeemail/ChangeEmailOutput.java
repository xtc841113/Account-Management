package ntut.csie.accountManagementService.useCase.user.changeemail;

import ntut.csie.accountManagementService.useCase.Output;

public interface ChangeEmailOutput extends Output {
    public String getUserId();

    public void setUserId(String userId);
}
