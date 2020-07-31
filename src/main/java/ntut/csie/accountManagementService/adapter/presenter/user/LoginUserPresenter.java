package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserOutput;

public class LoginUserPresenter implements Presenter<LoginUserOutput, LoginUserViewModel>, LoginUserOutput {

    private String userId;

    @Override
    public LoginUserViewModel buildViewModel() {
        LoginUserViewModel viewModel = new LoginUserViewModel();

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
