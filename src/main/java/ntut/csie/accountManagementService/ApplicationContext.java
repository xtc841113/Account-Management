package ntut.csie.accountManagementService;

import ntut.csie.accountManagementService.adapter.encryption.BCryptImpl;
import ntut.csie.accountManagementService.adapter.gateways.repository.MySqlUserRepositoryImpl;
import ntut.csie.accountManagementService.adapter.gateways.repository.event.MySqlEventStoreImpl;
import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.EventStore;
import ntut.csie.accountManagementService.useCase.UserRepository;
import ntut.csie.accountManagementService.useCase.handler.EventStoreHandler;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCase;
import ntut.csie.accountManagementService.useCase.user.changeemail.ChangeEmailUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCase;
import ntut.csie.accountManagementService.useCase.user.changepassword.ChangePasswordUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameUseCase;
import ntut.csie.accountManagementService.useCase.user.chnagenickname.ChangeNicknameUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.getUserByUserId.GetUserByUserIdUseCase;
import ntut.csie.accountManagementService.useCase.user.getUserByUserId.GetUserByUserIdUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.getUserIdByUsername.GetUserIdByUsernameUseCase;
import ntut.csie.accountManagementService.useCase.user.getUserIdByUsername.GetUserIdByUsernameUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserUseCase;
import ntut.csie.accountManagementService.useCase.user.loginUser.LoginUserUseCaseImpl;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCase;
import ntut.csie.accountManagementService.useCase.user.registeruser.RegisterUserUseCaseImpl;

public class ApplicationContext {
	private static ApplicationContext instance = null;
	
	private RegisterUserUseCase registerUserUseCase;
	private LoginUserUseCase loginUserUseCase;
	private GetUserByUserIdUseCase getUserByUserIdUseCase;
	private GetUserIdByUsernameUseCase getUserIdByUsernameUseCase;
	private ChangePasswordUseCase changePasswordUseCase;
	private ChangeEmailUseCase changeEmailUseCase;
	private ChangeNicknameUseCase changeNicknameUseCase;

	private UserRepository userRepository;
	private EventStore eventStore;
	private DomainEventBus domainEventBus;
	private EventStoreHandler eventStoreHandler;
	private Encrypt encrypt;

	private ApplicationContext() {
		this(
			new MySqlUserRepositoryImpl(),
			new MySqlEventStoreImpl()
		);
	}

	public ApplicationContext(UserRepository userRepository, EventStore eventStore) {
		this.userRepository = userRepository;
		this.eventStore = eventStore;
		this.domainEventBus = new DomainEventBus();
		encrypt = new BCryptImpl();

		eventStoreHandler = new EventStoreHandler(eventStore);

		domainEventBus.register(eventStoreHandler);
	}
	
	public static synchronized ApplicationContext getInstance() {
		if(instance == null){
			return new ApplicationContext();
		}
		return instance;
	}
	
	public UserRepository newUserRepository() {
		userRepository = new MySqlUserRepositoryImpl();
		return userRepository;
	}

	public EventStore newEventStore() {
		eventStore = new MySqlEventStoreImpl();
		return eventStore;
	}
	
	public RegisterUserUseCase newRegisterUseCase() {
		registerUserUseCase = new RegisterUserUseCaseImpl(newUserRepository(), domainEventBus, encrypt);
		return registerUserUseCase;
	}

	public LoginUserUseCase newLoginUserUseCase() {
		loginUserUseCase = new LoginUserUseCaseImpl(newUserRepository(), domainEventBus, encrypt);
		return loginUserUseCase;
	}
	
	public GetUserByUserIdUseCase newGetUserByUserIdUseCase() {
		getUserByUserIdUseCase = new GetUserByUserIdUseCaseImpl(newUserRepository());
		return getUserByUserIdUseCase;
	}

	public GetUserIdByUsernameUseCase newGetUserIdByUserInformationUseCase() {
		getUserIdByUsernameUseCase = new GetUserIdByUsernameUseCaseImpl(newUserRepository());
		return getUserIdByUsernameUseCase;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public EventStore getEventStore() {
		return eventStore;
	}

	public Encrypt getEncrypt() { return encrypt;}

	public ChangePasswordUseCase newChangePasswordUseCase() {
		changePasswordUseCase = new ChangePasswordUseCaseImpl(newUserRepository(), domainEventBus, encrypt);
		return changePasswordUseCase;
	}

	public ChangeEmailUseCase newChangeEmailUseCase() {
		changeEmailUseCase = new ChangeEmailUseCaseImpl(newUserRepository(), domainEventBus, encrypt);
		return changeEmailUseCase;
	}

	public ChangeNicknameUseCase newChangeNicknameUseCase() {
		changeNicknameUseCase = new ChangeNicknameUseCaseImpl(newUserRepository(), domainEventBus, encrypt);
		return changeNicknameUseCase;
	}
}
