package ntut.csie.accountManagementService.entity.model.user.event;

import ntut.csie.accountManagementService.entity.model.DateProvider;
import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.Date;

public class EmailChanged implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String username;
    private String oldEmail;
    private String newEmail;

    public EmailChanged(String userId, String username, String oldEmail, String newEmail) {
        this.occurredOn = DateProvider.now();
        this.userId = userId;
        this.username = username;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
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

    public String oldEmail() {
        return oldEmail;
    }

    public String newEmail() {
        return newEmail;
    }
}
