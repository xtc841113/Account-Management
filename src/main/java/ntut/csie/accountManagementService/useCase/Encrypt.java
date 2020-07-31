package ntut.csie.accountManagementService.useCase;

public interface Encrypt {
    public String encrypt(String password);

    public Boolean checkPassword(String password1, String password2);
}
