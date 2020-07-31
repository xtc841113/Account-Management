package ntut.csie.accountManagementService.useCase.user.changeemail;

import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordInput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordOutput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;

public class ChangeEmailUseCaseImpl implements ChangeEmailUseCase, ChangeEmailInput {
    private UserRepository userRepository;
    private DomainEventBus domainEventBus;
    private Encrypt encrypt;

    private String userId;
    private String password;
    private String newEmail;

    public ChangeEmailUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
        this.userRepository = userRepository;
        this.domainEventBus = domainEventBus;
        this.encrypt = encrypt;
    }

    @Override
    public void execute(ChangeEmailInput input, ChangeEmailOutput output) {
        User user = userRepository.getUserById(input.getUserId());
        if(encrypt.checkPassword(input.getPassword(), user.getPassword())){
            user.changeEmail(input.getNewEmail());
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
    public String getNewEmail() {
        return newEmail;
    }

    @Override
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
