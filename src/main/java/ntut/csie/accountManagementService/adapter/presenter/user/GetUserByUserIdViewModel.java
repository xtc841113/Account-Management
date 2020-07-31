package ntut.csie.accountManagementService.adapter.presenter.user;

import ntut.csie.accountManagementService.adapter.presenter.ViewModel;
import ntut.csie.accountManagementService.useCase.UserDto;

public class GetUserByUserIdViewModel implements ViewModel {
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
