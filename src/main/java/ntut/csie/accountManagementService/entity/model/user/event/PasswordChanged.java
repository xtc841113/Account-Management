package ntut.csie.accountManagementService.entity.model.user.event;

import ntut.csie.accountManagementService.entity.model.DateProvider;
import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.Date;

public class PasswordChanged implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String username;
    private String oldPassword;
    private String newPassword;

    public PasswordChanged(String userId, String username, String oldPassword, String newPassword) {
        this.occurredOn = DateProvider.now();
        this.userId = userId;
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Date occurredOn() {
        return occurredOn;
    }

    public String userId() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String oldPassword() {
        return oldPassword;
    }

    public String newPassword() {
        return newPassword;
    }
}
