package ntut.csie.accountManagementService.entity.model.user.event;

import ntut.csie.accountManagementService.entity.model.DateProvider;
import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.Date;

public class UserRegisteredFail implements DomainEvent {
    private Date occurredOn;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String systemRole;

    public UserRegisteredFail(String username, String password, String email, String nickname, String systemRole) {
        this.occurredOn = DateProvider.now();
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.systemRole = systemRole;
    }

    public Date occurredOn() {
        return occurredOn;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public String nickname() {
        return nickname;
    }

    public String systemRole() {
        return systemRole;
    }
}
