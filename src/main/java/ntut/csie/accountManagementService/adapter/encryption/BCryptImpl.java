package ntut.csie.accountManagementService.adapter.encryption;

import ntut.csie.accountManagementService.useCase.Encrypt;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptImpl implements Encrypt {
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptImpl() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Boolean checkPassword(String password1, String password2) {

        return bCryptPasswordEncoder.matches(password1, password2);
    }
}
