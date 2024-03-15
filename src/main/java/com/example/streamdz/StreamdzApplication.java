package com.example.streamdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


public class StreamdzApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamdzApplication.class, args);
        StreamAPIPractice streamAPIPractice = new StreamAPIPractice();
        List<StreamAPIPractice.User> userList = streamAPIPractice.getUsers();

        // Создать мап где ключ - id, значение - пользователь
        Map<Long, StreamAPIPractice.User> userMap = userList.stream()
                .collect(Collectors.toMap(StreamAPIPractice.User::getId, Function.identity()));
        userMap.forEach((id, user) -> System.out.println("ID: " + id + ", User: " + user));



        // Разбить по полу в три отдельных списка (сначала последовательно, а потом попробовать в одном проходе по оригинальному списку)
        List<StreamAPIPractice.User> maleUsers = userList.stream()
                .filter(user -> user.getGender() == StreamAPIPractice.Sex.MALE)
                .toList();
        List<StreamAPIPractice.User> femaleUsers = userList.stream()
                .filter(user -> user.getGender() == StreamAPIPractice.Sex.FEMALE)
                .toList();
        List<StreamAPIPractice.User> unknownUsers = userList.stream()
                .filter(user -> user.getGender() == StreamAPIPractice.Sex.UNKNOWN)
                .toList();

        Map<StreamAPIPractice.Sex, List<StreamAPIPractice.User>> userMap2 =  userList.stream()
                .collect(Collectors.groupingBy(StreamAPIPractice.User::getGender));
        List<StreamAPIPractice.User> maleUsers2 = userMap2.getOrDefault(StreamAPIPractice.Sex.MALE, List.of());

        // Найти всех админов и вывести в отдельный список с именами и фамилиями ЗАГЛАВНЫМИ БУКВАМИ
        List<String> adminList = userList.stream().filter(user -> user.getRoles().contains("Admin"))
                .map(user -> user.getName().toUpperCase() + " " + user.getLastname().toUpperCase())
                .collect(Collectors.toList());
        System.out.println(adminList);

        // Посчитать средний возраст reduce тебе в помощь 🙂

        BiFunction<Integer, StreamAPIPractice.User, Integer> biFunc = (a, b) -> a + b.getAge();
        BinaryOperator<Integer> biOp = Integer::sum;
        Integer average = userList.stream().reduce(0, biFunc, biOp) / userList.size();
        System.out.println(average);

        // Превратить список пользователей в список строк toString()
        List<String> userString = userList.stream()
                .map(StreamAPIPractice.User::toString)
                .collect(Collectors.toList());
        System.out.println(userString);


    }

}
