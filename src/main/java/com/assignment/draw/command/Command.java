package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;

public interface Command {
    void execute(String[] commandTokens) throws InvalidCommandException;
}
