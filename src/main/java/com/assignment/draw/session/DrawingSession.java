package com.assignment.draw.session;

import com.assignment.draw.exception.CommandNotFoundException;
import com.assignment.draw.exception.InvalidCommandException;

public interface DrawingSession {
    void draw(String commandString) throws InvalidCommandException, CommandNotFoundException;
}
