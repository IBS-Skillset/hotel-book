package com.happystays.book.cmd.infrastructure;

import com.happystays.cqrs.core.commands.BaseCommand;
import com.happystays.cqrs.core.commands.CommandHandlerMethod;
import com.happystays.cqrs.core.infrastucture.CommandDispatcher;
import com.happystays.cqrs.core.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;


@Service
public class BookCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);

    }

    @Override
    public BaseResponse send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (Objects.isNull(handlers) || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler");
        }

        return handlers.get(0).handle(command);


    }
}
