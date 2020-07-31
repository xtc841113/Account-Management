package ntut.csie.accountManagementService.entity.model.user;

import ntut.csie.accountManagementService.entity.model.SystemRole;

import java.util.UUID;

public class UserBuilder {
	private String id;
	private String username;
	private String email;
	private String password;
	private String nickname;
	private SystemRole systemRole;
	
	public static UserBuilder newInstance() {
		return new UserBuilder();
	}
	
	public UserBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	public UserBuilder systemRole(String systemRole) {
		this.systemRole = SystemRole.valueOf(systemRole);
		return this;
	}
	
	public User build() {
		id = UUID.randomUUID().toString();
		User user = new User(id, username, email, password, nickname, systemRole);
		return user;
	}
}
