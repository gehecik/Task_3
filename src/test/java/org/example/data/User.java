package org.example.data;

import lombok.*;

import static org.example.utils.RandomValue.randomEmail;
import static org.example.utils.RandomValue.randomNumberAsString;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public static User userWithRandomField() {
        return User.builder()
                .name("name")
                .email(randomEmail())
                .password(randomNumberAsString())
                .build();
    }

    public static User userWithWrongPassword() {
        return User.builder()
                .name("name")
                .email(randomEmail())
                .password("123")
                .build();
    }

}
