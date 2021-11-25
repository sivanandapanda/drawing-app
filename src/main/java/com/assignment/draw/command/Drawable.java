package com.assignment.draw.command;

import com.assignment.draw.model.Canvas;

interface Drawable {
    void setCanvas(Canvas canvas);
    Canvas getCanvas();
    void drawShape(String[] commandTokens);
}
