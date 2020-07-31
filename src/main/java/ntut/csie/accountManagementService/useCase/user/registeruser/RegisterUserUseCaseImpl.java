package ntut.csie.accountManagementService.useCase.user.registeruser;

import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.entity.model.user.event.UserRegisteredFail;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;

import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.entity.model.user.UserBuilder;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase, RegisterUserInput {
	private UserRepository userRepository;
	private DomainEventBus domainEventBus;
	private Encrypt encrypt;

	private String username;
	private String email;
	private String password;
	private String nickname;
	private String systemRole;

	public RegisterUserUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
		this.userRepository = userRepository;
		this.domainEventBus = domainEventBus;
		this.encrypt = encrypt;
	}

	@Override
	public void execute(RegisterUserInput input, RegisterUserOutput output) {
		User user = userRepository.getUserByUsername(input.getUsername());
		if(user == null) {
			user = UserBuilder.newInstance()
					.username(input.getUsername())
					.email(input.getEmail())
					.password(encrypt.encrypt(input.getPassword()))
					.nickname(input.getNickname())
					.systemRole(input.getSystemRole())
					.build();
			userRepository.save(user);
			domainEventBus.postAll(user);
			output.setUserId(user.getId());
		}else {
			domainEventBus.post(new UserRegisteredFail(
					input.getUsername(),
					encrypt.encrypt(input.getPassword()),
					input.getEmail(),
					input.getNickname(),
					input.getSystemRole()
			));
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getNickname() {
		return nickname;
	}

	@Override
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String getSystemRole() {
		return systemRole;
	}

	@Override
	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

}
