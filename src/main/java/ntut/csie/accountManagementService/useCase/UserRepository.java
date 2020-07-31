package ntut.csie.accountManagementService.useCase;

import ntut.csie.accountManagementService.entity.model.user.User;

public interface UserRepository {
	public void save(User user);
	
	public User getUserById(String id);
	
	public User getUserByUsername(String username);
}
