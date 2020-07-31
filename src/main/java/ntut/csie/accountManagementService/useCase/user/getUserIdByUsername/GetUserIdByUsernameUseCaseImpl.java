package ntut.csie.accountManagementService.useCase.user.getUserIdByUsername;

import ntut.csie.accountManagementService.useCase.UserRepository;

public class GetUserIdByUsernameUseCaseImpl implements GetUserIdByUsernameUseCase, GetUserIdByUsernameInput {

    private UserRepository userRepository;
    private String username;

    public GetUserIdByUsernameUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(GetUserIdByUsernameInput input, GetUserIdByUsernameOutput output) {
        output.setUserId(userRepository.getUserByUsername(input.getUsername()).getId());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }


}
