package ntut.csie.accountManagementService.useCase;

import ntut.csie.accountManagementService.entity.model.user.User;

public class ConvertUserToDto {
	public static UserDto transform(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setNickname(user.getNickname());
		dto.setSystemRole(user.getSystemRole().toString());
		return dto;
	}
}
