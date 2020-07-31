package ntut.csie.accountManagementService;

import ntut.csie.accountManagementService.repository.FakeEventStore;
import ntut.csie.accountManagementService.repository.FakeUserRepository;
import ntut.csie.accountManagementService.useCase.UserRepository;

public class FakeApplicationContext extends ApplicationContext{

    public static FakeApplicationContext getInstance() {

        return new FakeApplicationContext();
    }

    public FakeApplicationContext() {
        super(
                new FakeUserRepository(),
                new FakeEventStore()
        );
    }

    public UserRepository newUserRepository() {
        return getUserRepository();
    }
}
