package ntut.csie.accountManagementService.usecase;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.adapter.presenter.user.RegisterUserPresenter;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserInput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserOutput;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterUserUseCaseTest {

    private FakeApplicationContext applicationContext;
    private UserRepository userRepository;
    private Encrypt encrypt;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String systemRole;

    @Before
    public void setUp() throws Exception {
        applicationContext = FakeApplicationContext.getInstance();
        userRepository = applicationContext.getUserRepository();
        encrypt = applicationContext.getEncrypt();

        username = "ezKanban";
        password = "123456";
        nickname = "ezKanban";
        email = "ezKanban@gmail.com";
        systemRole = SystemRole.General_User.toString();
    }

    @Test
    public void should_success_when_register_user() {
        RegisterUserUseCase registerUserUseCase =  applicationContext.newRegisterUseCase();
        RegisterUserInput input = (RegisterUserInput) registerUserUseCase;
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setSystemRole(systemRole);

        RegisterUserOutput output = new RegisterUserPresenter();

        registerUserUseCase.execute(input, output);

        assertNotNull(output.getUserId());

        User user = userRepository.getUserById(output.getUserId());

        assertEquals(username, user.getUsername());
        assertTrue(encrypt.checkPassword(password, user.getPassword()));
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(systemRole, user.getSystemRole().toString());
    }
}
