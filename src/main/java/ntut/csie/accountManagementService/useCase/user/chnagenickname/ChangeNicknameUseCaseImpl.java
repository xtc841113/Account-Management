package ntut.csie.accountManagementService.useCase.user.chnagenickname;

import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailInput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;

public class ChangeNicknameUseCaseImpl implements ChangeNicknameUseCase, ChangeNicknameInput {
    private UserRepository userRepository;
    private DomainEventBus domainEventBus;
    private Encrypt encrypt;

    private String userId;
    private String password;
    private String newNickname;

    public ChangeNicknameUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
        this.userRepository = userRepository;
        this.domainEventBus = domainEventBus;
        this.encrypt = encrypt;
    }

    @Override
    public void execute(ChangeNicknameInput input, ChangeNicknameOutput output) {
        User user = userRepository.getUserById(input.getUserId());
        if(encrypt.checkPassword(input.getPassword(), user.getPassword())){
            user.changeNickname(input.getNewNickname());
        }else {
            throw new RuntimeException("Password is incorrect.");
        }

        userRepository.save(user);
        domainEventBus.postAll(user);
        output.setUserId(user.getId());
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getNewNickname() {
        return newNickname;
    }

    @Override
    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }
}
