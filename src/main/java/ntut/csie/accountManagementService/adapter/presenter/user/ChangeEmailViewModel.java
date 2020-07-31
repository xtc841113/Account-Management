package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.ViewModel;

public class ChangeEmailViewModel implements ViewModel {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
