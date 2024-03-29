package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.models.SessionState;
import edu.java.bot.repository.UserService;
import edu.java.bot.users.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component

public class CommandStart implements Command {
    public static final String SUCCESS_REGISTRATION_MESSAGE = "Регистрация прошла успешно!";
    private static final String ALREADY_REGISTRATE_MESSAGE = "Вы уже зарегистрированы";

    private final UserService userService;

    public CommandStart(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Позволяет зарегистрироваться в системе";
    }

    @Override
    public String handle(Update update) {
        long chatId = update.message().chat().id();

        return registerUser(chatId);
    }

    private String registerUser(long chatId) {
        Optional userOptional = userService.findUserById(chatId);

        if (userOptional.isEmpty()) {
            User user = new User(chatId, List.of(), SessionState.BASE_STATE);
            userService.saveUser(user);
            return SUCCESS_REGISTRATION_MESSAGE;
        }
        return ALREADY_REGISTRATE_MESSAGE;
    }
}

