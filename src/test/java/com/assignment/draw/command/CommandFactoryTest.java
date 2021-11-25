package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandFactoryTest {

    @Test
    void should_register_new_command_string_and_get_same_command() throws InvalidCommandException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("ABC", Quit::new);

        assertTrue(commandFactory.getCommand("ABC").isPresent());
        assertTrue(commandFactory.getCommand("ABC").get() instanceof Quit);
    }

    @Test
    void should_not_register_with_null_command_string() {
        var commandFactory = new CommandFactory();
        assertThrows(InvalidCommandException.class, () -> commandFactory.registerCommand(null, Quit::new));
    }

    @Test
    void should_not_register_with_empty_command_string() {
        var commandFactory = new CommandFactory();
        assertThrows(InvalidCommandException.class, () -> commandFactory.registerCommand("", Quit::new));
    }

    @Test
    void should_not_register_with_null_command_supplier() {
        var commandFactory = new CommandFactory();
        assertThrows(InvalidCommandException.class, () -> commandFactory.registerCommand("A", null));
    }

    @Test
    void should_not_get_with_empty_command_string() {
        var commandFactory = new CommandFactory();
        assertThrows(InvalidCommandException.class, () -> commandFactory.getCommand(""));
    }

    @Test
    void should_not_get_with_null_command_string() {
        var commandFactory = new CommandFactory();
        assertThrows(InvalidCommandException.class, () -> commandFactory.getCommand(null));
    }

}