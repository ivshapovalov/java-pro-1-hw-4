package edu.t1.javapro1.hw4.service;

import edu.t1.javapro1.hw4.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceTestRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public ServiceTestRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {

        UserService userService = applicationContext.getBean("userService", UserService.class);

        System.out.println("=== ПРОВЕРКА МЕТОДОВ UserService ===");

        // 1. Создание пользователя 1
        User user1 = new User("user1");
        User savedUser1 = userService.save(user1);
        System.out.println("save(): " + savedUser1);

        // 1. Создание пользователя 2
        User user2 = new User("user2");
        User savedUser2 = userService.save(user2);
        System.out.println("save(): " + savedUser2);

        // 2. Поиск по ID 1
        User foundUser1 = userService.findById(savedUser1.getId());
        System.out.printf("findById(%s): %s%n", savedUser1.getId(), foundUser1);

        // 2. Поиск по ID 2
        User foundUser2 = userService.findById(savedUser2.getId());
        System.out.printf("findById(%s): %s%n", savedUser2.getId(), foundUser2);

        // 3. Получение всех пользователей
        List<User> allUsers = userService.findAll();
        System.out.println("findAll(): " + allUsers);

        // 5. Удаление по id 1
        boolean deleted1 = userService.deleteById(savedUser1.getId());
        System.out.printf("deleteById(%s): %s%n", savedUser1.getId(), deleted1);

        // 3. Получение всех пользователей после удаления 1
        List<User> allUsersAfterFirstDelete = userService.findAll();
        System.out.printf("findAll() after delete %s: %s%n", savedUser1.getId(), allUsersAfterFirstDelete);

        // 5. Удаление по id 2
        boolean deleted2 = userService.deleteById(savedUser2.getId());
        System.out.printf("deleteById(%s): %s%n", savedUser2.getId(), deleted2);

        // 3. Получение всех пользователей после удаления 2
        List<User> allUsersAfterSecondDelete = userService.findAll();
        System.out.printf("findAll() after delete %s: %s%n", savedUser2.getId(), allUsersAfterSecondDelete);

        System.out.println("=== ВСЕ МЕТОДЫ UserService УСПЕШНО ПРОВЕРЕНЫ ===");
    }
}
