package ntut.csie.accountManagementService.usecase;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangePasswordPresenter;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordInput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordOutput;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangePasswordUseCaseTest {

    private FakeApplicationContext applicationContext;
    private UserRepository userRepository;
    private Encrypt encrypt;
    private Utility utility;

    private String userId;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String nickname;
    private String email;
    private String systemRole;

    @Before
    public void setUp() throws Exception {
        applicationContext = FakeApplicationContext.getInstance();
        userRepository = applicationContext.getUserRepository();
        encrypt = applicationContext.getEncrypt();
        utility = new Utility(applicationContext);

        username = "ezKanban";
        oldPassword = "123456";
        newPassword = "123456*";
        nickname = "ezKanban";
        email = "ezKanban@gmail.com";
        systemRole = SystemRole.General_User.toString();
        userId = utility.register(username, oldPassword, email, nickname, systemRole);
    }

    @Test
    public void should_success_when_change_password() {
        ChangePasswordUseCase changePasswordUseCase = applicationContext.newChangePasswordUseCase();
        ChangePasswordInput changePasswordInput = (ChangePasswordInput) changePasswordUseCase;
        changePasswordInput.setUserId(userId);
        changePasswordInput.setOldPassword(oldPassword);
        changePasswordInput.setNewPassword(newPassword);

        ChangePasswordOutput changePasswordOutput = new ChangePasswordPresenter();

        changePasswordUseCase.execute(changePasswordInput, changePasswordOutput);

        assertEquals(userId, changePasswordOutput.getUserId());

        User user = userRepository.getUserById(userId);
        assertTrue(encrypt.checkPassword(newPassword, user.getPassword()));
    }
}
