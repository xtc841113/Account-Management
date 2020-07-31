package ntut.csie.accountManagementService.repository;

import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.UserRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FakeUserRepository implements UserRepository {
    private Map<String, User> users;

    public FakeUserRepository() {
        this.users = new LinkedHashMap<>();
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getUserById(String userId) {
        for(User user : users.values()) {
            if(user.getId().equals(userId))
                return user;
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        for(User user : users.values()) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
