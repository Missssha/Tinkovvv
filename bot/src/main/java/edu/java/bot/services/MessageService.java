package edu.java.bot.services;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.models.SessionState;
import edu.java.bot.processors.CommandHandler;
import edu.java.bot.repository.UserService;
import edu.java.bot.users.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public static final String DO_REGISTRATION_MESSAGE = "Необходимо зарегистрироваться.";
    public static final String INVALID_COMMAND_MESSAGE = "Команда введена некорректно.";

    private final CommandHandler commandHandler;
    private final UserService userRepository;

    public MessageService(
        CommandHandler commandHandler,
        UserService userRepository
    ) {
        this.commandHandler = commandHandler;
        this.userRepository = userRepository;
    }

    public String prepareResponseMessage(Update update) {
        var chatId = update.message().chat().id();
        var textMessage = update.message().text();
        var botCommand = commandHandler.getCommand(textMessage);
        return botCommand.map(command -> command.handle(update))
            .orElseGet(() -> processNonCommandMessage(chatId, textMessage));
    }

    private String processNonCommandMessage(Long chatId, String text) {
        var userOptional = userRepository.findUserById(chatId);
        if (userOptional.isEmpty()) {
            return DO_REGISTRATION_MESSAGE;
        }

        return INVALID_COMMAND_MESSAGE;
    }

}
