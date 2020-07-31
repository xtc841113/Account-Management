package ntut.csie.accountManagementService.entity.model.user.event;

import ntut.csie.accountManagementService.entity.model.DateProvider;
import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.Date;

public class NicknameChanged implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String username;
    private String oldNickname;
    private String newNickname;

    public NicknameChanged(String userId, String username, String oldNickname, String newNickname) {
        this.occurredOn = DateProvider.now();
        this.userId = userId;
        this.username = username;
        this.oldNickname = oldNickname;
        this.newNickname = newNickname;
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

    public String oldNickname() {
        return oldNickname;
    }

    public String newNickname() {
        return newNickname;
    }
}
