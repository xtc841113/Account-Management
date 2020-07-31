package ntut.csie.accountManagementService.usecase;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.adapter.presenter.user.ChangeNicknamePresenter;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameInput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameOutput;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeNicknameUseCaseTest {

    private FakeApplicationContext applicationContext;
    private UserRepository userRepository;
    private Utility utility;

    private String userId;
    private String username;
    private String password;
    private String oldNickname;
    private String newNickname;
    private String email;
    private String systemRole;

    @Before
    public void setUp() throws Exception {
        applicationContext = FakeApplicationContext.getInstance();
        userRepository = applicationContext.getUserRepository();
        utility = new Utility(applicationContext);


        username = "ezKanban";
        password = "123456";
        oldNickname = "ezKanban";
        newNickname = "ezKanban123";
        email = "ezKanban@gmail.com";
        systemRole = SystemRole.General_User.toString();
        userId = utility.register(username, password, email, oldNickname, systemRole);
    }

    @Test
    public void should_success_when_change_nickname() {
        ChangeNicknameUseCase changeNicknameUseCase = applicationContext.newChangeNicknameUseCase();
        ChangeNicknameInput changeNicknameInput = (ChangeNicknameInput) changeNicknameUseCase;
        changeNicknameInput.setUserId(userId);
        changeNicknameInput.setPassword(password);
        changeNicknameInput.setNewNickname(newNickname);

        ChangeNicknameOutput changeNicknameOutput = new ChangeNicknamePresenter();

        changeNicknameUseCase.execute(changeNicknameInput, changeNicknameOutput);

        assertEquals(userId, changeNicknameOutput.getUserId());

        User user = userRepository.getUserById(userId);
        assertEquals(newNickname, user.getNickname());
    }
}
