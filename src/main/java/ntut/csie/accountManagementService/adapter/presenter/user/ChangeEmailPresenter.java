package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;

public class ChangeEmailPresenter implements Presenter<ChangeEmailOutput, ChangeEmailViewModel>, ChangeEmailOutput {
    private String userId;

    @Override
    public ChangeEmailViewModel buildViewModel() {
        ChangeEmailViewModel viewModel = new ChangeEmailViewModel();
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
