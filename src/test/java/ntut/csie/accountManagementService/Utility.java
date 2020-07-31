package ntut.csie.accountManagementService;

import ntut.csie.accountManagementService.adapter.presenter.user.*;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailInput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordInput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordOutput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameInput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameOutput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameUseCase;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserInput;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserOutput;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserUseCase;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserInput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserOutput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Utility {
    private ApplicationContext applicationContext;

    public Utility(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String register(String username, String password, String nickname, String email, String systemRole) {
        RegisterUserUseCase registerUserUseCase =  applicationContext.newRegisterUseCase();
        RegisterUserInput input = (RegisterUserInput) registerUserUseCase;
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setSystemRole(systemRole);

        RegisterUserOutput output = new RegisterUserPresenter();

        registerUserUseCase.execute(input, output);

        return output.getUserId();
    }

    public String login(String username, String password) {
        LoginUserUseCase loginUserUseCase = applicationContext.newLoginUserUseCase();
        LoginUserInput input = (LoginUserInput) loginUserUseCase;
        input.setUsername(username);
        input.setPassword(password);

        LoginUserOutput output = new LoginUserPresenter();

        loginUserUseCase.execute(input, output);
        return output.getUserId();
    }

    public String changePassword(String userId, String oldPassword, String newPassword) {
        ChangePasswordUseCase changePasswordUseCase = applicationContext.newChangePasswordUseCase();
        ChangePasswordInput changePasswordInput = (ChangePasswordInput) changePasswordUseCase;
        changePasswordInput.setUserId(userId);
        changePasswordInput.setOldPassword(oldPassword);
        changePasswordInput.setNewPassword(newPassword);

        ChangePasswordOutput changePasswordOutput = new ChangePasswordPresenter();

        changePasswordUseCase.execute(changePasswordInput, changePasswordOutput);
        return changePasswordOutput.getUserId();
    }

    public String changeNickname(String userId, String password, String newNickname) {
        ChangeNicknameUseCase changeNicknameUseCase = applicationContext.newChangeNicknameUseCase();
        ChangeNicknameInput changeNicknameInput = (ChangeNicknameInput) changeNicknameUseCase;
        changeNicknameInput.setUserId(userId);
        changeNicknameInput.setPassword(password);
        changeNicknameInput.setNewNickname(newNickname);

        ChangeNicknameOutput changeNicknameOutput = new ChangeNicknamePresenter();

        changeNicknameUseCase.execute(changeNicknameInput, changeNicknameOutput);

        return changeNicknameOutput.getUserId();
    }

    public String changeEmail(String userId, String password, String newEmail) {
        ChangeEmailUseCase changeEmailUseCase = applicationContext.newChangeEmailUseCase();
        ChangeEmailInput changeEmailInput = (ChangeEmailInput) changeEmailUseCase;
        changeEmailInput.setUserId(userId);
        changeEmailInput.setPassword(password);
        changeEmailInput.setNewEmail(newEmail);

        ChangeEmailOutput changeEmailOutput = new ChangeEmailPresenter();

        changeEmailUseCase.execute(changeEmailInput, changeEmailOutput);

        return changeEmailOutput.getUserId();
    }
}
