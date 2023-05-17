package shop.mtcoding.metablog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.metablog.model.board.Board;
import shop.mtcoding.metablog.model.board.BoardRepository;
import shop.mtcoding.metablog.model.user.User;
import shop.mtcoding.metablog.model.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MetablogApplication extends DummyEntity{

    @Bean
    CommandLineRunner init(UserRepository userRepository, BoardRepository boardRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            User archie = User.builder()
                    .username("archie")
                    .password(passwordEncoder.encode("1234"))
                    .email("archie@nate.com")
                    .role("USER")
                    .status(true)
                    .profile("person.png")
                    .build();

            userRepository.save(archie);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MetablogApplication.class, args);
    }

}
