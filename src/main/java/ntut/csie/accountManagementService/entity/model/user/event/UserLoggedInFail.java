package ntut.csie.accountManagementService.entity.model.user.event;

import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.Date;

public class UserLoggedInFail implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String username;

    public UserLoggedInFail(String userId, String username) {
        this.occurredOn = occurredOn();
        this.userId = userId;
        this.username = username;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    public String userId() {
        return userId;
    }

    public String username() {
        return username;
    }
}
