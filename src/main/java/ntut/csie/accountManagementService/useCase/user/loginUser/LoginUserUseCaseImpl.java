package ntut.csie.accountManagementService.useCase.user.loginUser;

import ntut.csie.accountManagementService.entity.DomainEventBus;
import ntut.csie.accountManagementService.entity.model.DomainEvent;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.Encrypt;
import ntut.csie.accountManagementService.useCase.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginUserUseCaseImpl implements LoginUserUseCase, LoginUserInput {
	private UserRepository userRepository;
	private DomainEventBus domainEventBus;
	private Encrypt encrypt;

	private String username;
	private String password;

	public LoginUserUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
		this.userRepository = userRepository;
		this.domainEventBus = domainEventBus;
		this.encrypt = encrypt;
	}

	@Override
	public void execute(LoginUserInput input, LoginUserOutput output) {
		User user = userRepository.getUserByUsername(input.getUsername());
		if(encrypt.checkPassword(input.getPassword(), user.getPassword())) {
			user.loginSuccess();
			output.setUserId(user.getId());
		} else {
			user.loginFail();
		}
		domainEventBus.postAll(user);

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
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
