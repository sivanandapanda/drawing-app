package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class CommandFactory {

    private final Map<String, Supplier<Command>> commandMap = new HashMap<>();

    public void registerCommand(String commandChar, Supplier<Command> commandSupplier) throws InvalidCommandException {
        if(Objects.isNull(commandChar) || commandChar.isEmpty()) {
            throw new InvalidCommandException("No commandChar is provided to register.");
        }

        if(Objects.isNull(commandSupplier)) {
            throw new InvalidCommandException("No command is provided to register.");
        }

        commandMap.put(commandChar.toUpperCase(), commandSupplier);
    }

    public Optional<Command> getCommand(String commandChar) throws InvalidCommandException {
        if(Objects.isNull(commandChar) || commandChar.isEmpty()) {
            throw new InvalidCommandException("No commandChar is provided.");
        }

        if(commandMap.containsKey(commandChar.toUpperCase())) {
            return Optional.of(commandMap.get(commandChar.toUpperCase()).get());
        } else {
            return Optional.empty();
        }
    }
}
