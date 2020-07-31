package ntut.csie.accountManagementService.useCase.user.changepassword;

import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.entity.model.DomainEvent;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase, ChangePasswordInput{
    private UserRepository userRepository;
    private DomainEventBus domainEventBus;
    private Encrypt encrypt;

    private String userId;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
        this.userRepository = userRepository;
        this.domainEventBus = domainEventBus;
        this.encrypt = encrypt;
    }

    @Override
    public void execute(ChangePasswordInput input, ChangePasswordOutput output) {
        User user = userRepository.getUserById(input.getUserId());
        if(encrypt.checkPassword(input.getOldPassword(), user.getPassword())){
            user.changePassword(encrypt.encrypt(input.getNewPassword()));
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
    public String getOldPassword() {
        return oldPassword;
    }

    @Override
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
