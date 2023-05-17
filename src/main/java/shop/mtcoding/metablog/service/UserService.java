package shop.mtcoding.metablog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.metablog.dto.user.UserRequest;
import shop.mtcoding.metablog.model.user.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void joinUser(UserRequest.JoinInDTO joinInDTO) {

        // password 암호화
        joinInDTO.setPassword(passwordEncoder.encode(joinInDTO.getPassword()));

        // DB 저장
        try {
            userRepository.save(joinInDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("회원가입 오류 : " + e.getMessage());
        }

    }
}