package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.user.UserService;
import org.example.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllUsersProcessors implements OperationCommandProcessor {
    private final UserService userService;

    public ShowAllUsersProcessors(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        List<User> users = userService.getAllUsers();
        System.out.println("List of all users:");
        users.forEach(System.out::println);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }

}
