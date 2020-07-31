package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserOutput;

public class RegisterUserPresenter implements Presenter<RegisterUserOutput, RegisterUserViewModel>, RegisterUserOutput {
    private String userId;

    @Override
    public RegisterUserViewModel buildViewModel() {
        RegisterUserViewModel viewModel = new RegisterUserViewModel();
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
