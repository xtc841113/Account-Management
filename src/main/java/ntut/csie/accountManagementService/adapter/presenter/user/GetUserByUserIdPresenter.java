package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.Presenter;
import ntut.csie.accountManagementService.useCase.UserDto;
import ntut.csie.accountManagementService.useCase.user.getUserByUserId.GetUserByUserIdOutput;

public class GetUserByUserIdPresenter implements Presenter<GetUserByUserIdOutput, GetUserByUserIdViewModel>, GetUserByUserIdOutput {
    private UserDto userDto;

    @Override
    public GetUserByUserIdViewModel buildViewModel() {
        GetUserByUserIdViewModel viewModel = new GetUserByUserIdViewModel();
        viewModel.setUser(userDto);
        return viewModel;
    }

    @Override
    public UserDto getUser() {
        return userDto;
    }

    @Override
    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }
}
