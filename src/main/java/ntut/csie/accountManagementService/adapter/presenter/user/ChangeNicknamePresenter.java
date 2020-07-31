package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameOutput;

public class ChangeNicknamePresenter implements Presenter<ChangeNicknameOutput, ChangeNicknameViewModel>, ChangeNicknameOutput {
    private String userId;

    @Override
    public ChangeNicknameViewModel buildViewModel() {
        ChangeNicknameViewModel viewModel = new ChangeNicknameViewModel();
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
