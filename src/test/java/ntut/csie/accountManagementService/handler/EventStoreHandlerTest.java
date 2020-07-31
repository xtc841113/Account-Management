package ntut.csie.accountManagementService.handler;

import ntut.csie.accountManagementService.FakeApplicationContext;
import ntut.csie.accountManagementService.Utility;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.event.*;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.EventStore;
import ntut.csie.accountManagementService.useCase.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventStoreHandlerTest {

    private FakeApplicationContext applicationContext;
    private UserRepository userRepository;
    private Encrypt encrypt;
    private EventStore eventStore;
    private Utility utility;

    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String systemRole;

    @Before
    public void setUp() {
        applicationContext = FakeApplicationContext.getInstance();
        userRepository = applicationContext.getUserRepository();
        eventStore = applicationContext.getEventStore();
        encrypt = applicationContext.getEncrypt();
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
    public void should_publish_user_registered_domain_event_in_when_user_register() {
        assertEquals(1, eventStore.getAllEvent().size());
        assertEquals(UserRegistered.class.getSimpleName(), eventStore.getAllEvent().get(0).getClass().getSimpleName());
        UserRegistered userRegistered = (UserRegistered) eventStore.getAllEvent().get(0);
        assertEquals(userId, userRegistered.userId());
        assertEquals(username, userRegistered.username());
        assertTrue(encrypt.checkPassword(password, userRegistered.password()));
        assertEquals(nickname, userRegistered.nickname());
        assertEquals(email, userRegistered.email());
        assertEquals(systemRole, userRegistered.systemRole());
    }

    @Test
    public void should_publish_user_registered_fail_domain_event_in_when_user_register_fail() {
        utility.register(username, password, nickname, email, systemRole);
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(UserRegisteredFail.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        UserRegisteredFail userRegisteredFail = (UserRegisteredFail) eventStore.getAllEvent().get(1);
        assertEquals(username, userRegisteredFail.username());
        assertTrue(encrypt.checkPassword(password, userRegisteredFail.password()));
        assertEquals(nickname, userRegisteredFail.nickname());
        assertEquals(email, userRegisteredFail.email());
        assertEquals(systemRole, userRegisteredFail.systemRole());
    }

    @Test
    public void should_publish_user_logged_in_domain_event_when_user_login() {
        utility.login(username, password);
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(UserLoggedIn.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        UserLoggedIn userLoggedIn = (UserLoggedIn) eventStore.getAllEvent().get(1);
        assertEquals(userId, userLoggedIn.userId());
        assertEquals(username, userLoggedIn.username());
    }

    @Test
    public void should_publish_user_logged_in_fail_domain_event_when_user_login_fail() {
        utility.login(username, "incorrect Password");
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(UserLoggedInFail.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        UserLoggedInFail userLoggedInFail = (UserLoggedInFail) eventStore.getAllEvent().get(1);
        assertEquals(userId, userLoggedInFail.userId());
        assertEquals(username, userLoggedInFail.username());
    }

    @Test
    public void should_publish_password_changed_domain_event_when_change_password() {
        String newPassword = "123456*";
        utility.changePassword(userId, password, newPassword);
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(PasswordChanged.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        PasswordChanged passwordChanged = (PasswordChanged) eventStore.getAllEvent().get(1);
        assertEquals(userId, passwordChanged.userId());
        assertEquals(username, passwordChanged.username());
        assertTrue(encrypt.checkPassword(password, passwordChanged.oldPassword()));
        assertTrue(encrypt.checkPassword(newPassword, passwordChanged.newPassword()));
    }

    @Test
    public void should_publish_nickname_changed_domain_event_when_change_nickname() {
        String newNickname = "ez Kanban";
        utility.changeNickname(userId, password, newNickname);
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(NicknameChanged.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        NicknameChanged nicknameChanged = (NicknameChanged) eventStore.getAllEvent().get(1);
        assertEquals(userId, nicknameChanged.userId());
        assertEquals(username, nicknameChanged.username());
        assertEquals(nickname, nicknameChanged.oldNickname());
        assertEquals(newNickname, nicknameChanged.newNickname());
    }

    @Test
    public void should_publish_email_changed_domain_event_when_change_email() {
        String newEmail = "Kanban@gmail.com";
        utility.changeEmail(userId, password, newEmail);
        assertEquals(2, eventStore.getAllEvent().size());
        assertEquals(EmailChanged.class.getSimpleName(), eventStore.getAllEvent().get(1).getClass().getSimpleName());
        EmailChanged emailChanged = (EmailChanged) eventStore.getAllEvent().get(1);
        assertEquals(userId, emailChanged.userId());
        assertEquals(username, emailChanged.username());
        assertEquals(email, emailChanged.oldEmail());
        assertEquals(newEmail, emailChanged.newEmail());
    }
}
