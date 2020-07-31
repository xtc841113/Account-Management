package ntut.csie.accountManagementService.usecase;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.adapter.presenter.user.LoginUserPresenter;
import ntut.csie.accountManagementService.adapter.presenter.user.RegisterUserPresenter;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserInput;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserOutput;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserUseCase;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserInput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserOutput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginUserUseCaseTest {

    private FakeApplicationContext applicationContext;
    private Utility utility;

    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String systemRole;

    @Before
    public void setUp() throws Exception {
        applicationContext = FakeApplicationContext.getInstance();
        utility = new Utility(applicationContext);

        username = "ezKanban";
        password = "123456";
        nickname = "ezKanban";
        email = "ezKanban@gmail.com";
        systemRole = SystemRole.General_User.toString();
        userId = utility.register(username, password, nickname, email, systemRole);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_success_when_login_user() {
        LoginUserUseCase useCase = applicationContext.newLoginUserUseCase();
        LoginUserInput input = (LoginUserInput) useCase;
        input.setUsername(username);
        input.setPassword(password);

        LoginUserOutput output = new LoginUserPresenter();

        useCase.execute(input, output);

        assertEquals(userId, output.getUserId());
    }
}
