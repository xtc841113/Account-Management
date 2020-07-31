package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.user.getUserIdByUsername.GetUserIdByUsernameOutput;

public class GetUserIdByUsernamePresenter implements Presenter<GetUserIdByUsernameOutput, GetUserIdByUserInformationViewModel>, GetUserIdByUsernameOutput {

    private String userId;

    @Override
    public GetUserIdByUserInformationViewModel buildViewModel() {
        GetUserIdByUserInformationViewModel viewModel = new GetUserIdByUserInformationViewModel();
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
