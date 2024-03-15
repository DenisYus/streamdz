package com.example.streamdz;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StreamAPIPractice {

    enum Sex {
        UNKNOWN,
        MALE,
        FEMALE,
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class User {
        private Long id;
        private String name;
        private String lastname;
        private Sex gender;
        private int age;
        private List<String> roles;

        public String toString() {
            return "%s_%s".formatted(name, lastname);
        }
    }

    List<User> getUsers() {
        return List.of(
                new User(1L, "Anna", "Annovna", Sex.FEMALE, 25, List.of("User")),
                new User(2L, "Oleg", "Olegov", Sex.UNKNOWN, 30, List.of("User", "Provider")),
                new User(3L, "Kirill", "Kirillov", Sex.MALE, 68, List.of("Admin", "Director")),
                new User(4L, "Tatiana", "Tatianina", Sex.FEMALE, 43, List.of("Admin", "Director")),
                new User(5L, "Maria", "Maria", Sex.UNKNOWN, 43, List.of("Admin", "User")),
                new User(6L, "Stepan", "Stepanych", Sex.MALE, 29, List.of("Provider", "User"))
        );
    }
}