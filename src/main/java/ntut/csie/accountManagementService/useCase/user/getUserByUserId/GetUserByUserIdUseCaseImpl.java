package ntut.csie.accountManagementService.useCase.user.getUserByUserId;

import ntut.csie.accountManagementService.useCase.ConvertUserToDto;
import ntut.csie.accountManagementService.useCase.UserRepository;

public class GetUserByUserIdUseCaseImpl implements GetUserByUserIdUseCase, GetUserByUserIdInput{
	private UserRepository userRepository;
	
	private String userId;
	
	public GetUserByUserIdUseCaseImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void execute(GetUserByUserIdInput input, GetUserByUserIdOutput output) {
		output.setUser(ConvertUserToDto.transform(userRepository.getUserById(input.getUserId())));
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
