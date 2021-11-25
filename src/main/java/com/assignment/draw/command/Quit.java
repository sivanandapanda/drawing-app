package com.assignment.draw.command;

import com.assignment.draw.exception.ExitException;

public class Quit implements Command {

    @Override
    public void execute(String[] commandTokens) {
        throw new ExitException("Quit command received, stopping drawing app. Bye!");
    }
}
