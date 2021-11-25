package com.assignment.draw.service;

import com.assignment.draw.model.Canvas;

public class ConsoleDrawingService implements DrawingService {

    private static final String EMPTY_STRING = " ";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public void draw(Canvas canvas) {
        StringBuilder shapeBuilder = new StringBuilder();
        for (char[] chars : canvas.getCurrentShape()) {
            for (char aChar : chars) {
                shapeBuilder.append(aChar == 0 ? EMPTY_STRING : aChar);
            }
            shapeBuilder.append(LINE_SEPARATOR);
        }
        System.out.println(shapeBuilder);
    }
}
