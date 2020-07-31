package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordOutput;

public class ChangePasswordPresenter implements Presenter<ChangePasswordOutput, ChangePasswordViewModel>, ChangePasswordOutput {
    private String userId;

    @Override
    public ChangePasswordViewModel buildViewModel() {
        ChangePasswordViewModel viewModel = new ChangePasswordViewModel();
        viewModel.setUserId(userId);
        return viewModel;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
