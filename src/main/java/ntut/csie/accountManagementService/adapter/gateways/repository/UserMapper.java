package ntut.csie.accountManagementService.adapter.gateways.repository;

import ntut.csie.accountManagementService.entity.model.SystemRole;
import ntut.csie.accountManagementService.entity.model.user.User;

public class UserMapper {
	public User transformToUser(UserData data) {
		User user = new User();
		user.setId(data.getId());
		user.setUsername(data.getUsername());
		user.setEmail(data.getEmail());
		user.setPassword(data.getPassword());
		user.setNickname(data.getNickname());
		user.setSystemRole(SystemRole.valueOf(data.getSystemRole()));
		return user;
	}
	
	public UserData transformToUserData(User user) {
		UserData data = new UserData();
		data.setId(user.getId());
		data.setUsername(user.getUsername());
		data.setEmail(user.getEmail());
		data.setPassword(user.getPassword());
		data.setNickname(user.getNickname());
		data.setSystemRole(user.getSystemRole().toString());
		return data;
	}
}
