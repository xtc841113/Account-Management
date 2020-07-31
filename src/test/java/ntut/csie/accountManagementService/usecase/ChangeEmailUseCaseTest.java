package ntut.csie.accountManagementService.usecase;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangeEmailPresenter;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailInput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailOutput;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeEmailUseCaseTest {

    private FakeApplicationContext applicationContext;
    private UserRepository userRepository;
    private Utility utility;

    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String oldEmail;
    private String newEmail;
    private String systemRole;

    @Before
    public void setUp() throws Exception {
        applicationContext = FakeApplicationContext.getInstance();
        userRepository = applicationContext.getUserRepository();
        utility = new Utility(applicationContext);


        username = "ezKanban";
        password = "123456";
        nickname = "ezKanban";
        oldEmail = "ezKanban@gmail.com";
        newEmail = "ezKanbanTeam@gmail.com";
        systemRole = SystemRole.General_User.toString();
        userId = utility.register(username, password, oldEmail, nickname, systemRole);
    }

    @Test
    public void should_success_when_change_email() {
        ChangeEmailUseCase changeEmailUseCase = applicationContext.newChangeEmailUseCase();
        ChangeEmailInput changeEmailInput = (ChangeEmailInput) changeEmailUseCase;
        changeEmailInput.setUserId(userId);
        changeEmailInput.setPassword(password);
        changeEmailInput.setNewEmail(newEmail);

        ChangeEmailOutput changeEmailOutput = new ChangeEmailPresenter();

        changeEmailUseCase.execute(changeEmailInput, changeEmailOutput);

        assertEquals(userId, changeEmailOutput.getUserId());

        User user = userRepository.getUserById(userId);
        assertEquals(newEmail, user.getEmail());
    }
}
