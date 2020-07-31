package ntut.csie.accountManagementService.entity.model.user;

import ntut.csie.accountManagementService.entity.model.AggregateRoot;
import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.event.*;

public class User extends AggregateRoot {
	private String id;
	private String username;
	private String email;
	private String password;
	private String nickname;
	private SystemRole systemRole;
	
	public User() {}
	
	public User(String id, String username, String email, String password, String nickname, SystemRole systemRole) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.systemRole = systemRole;
		addDomainEvent(new UserRegistered(id, username, password, email, nickname, systemRole.toString()));
	}

	public void loginSuccess() {
		addDomainEvent(new UserLoggedIn(id, username));
	}

	public void loginFail() {
		addDomainEvent(new UserLoggedInFail(id, username));
	}

	public void changePassword(String newPassword) {
		String oldPassword = password;
		setPassword(newPassword);
		addDomainEvent(new PasswordChanged(id, username, oldPassword, newPassword));
	}

	public void changeEmail(String newEmail) {
		String oldEmail = email;
		setEmail(newEmail);
		addDomainEvent(new EmailChanged(id, username, oldEmail, newEmail));
	}

	public void changeNickname(String newNickname) {
		String oldNickname = nickname;
		setNickname(newNickname);
		addDomainEvent(new NicknameChanged(id, username, oldNickname, newNickname));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}
}
