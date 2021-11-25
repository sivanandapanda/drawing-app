package com.assignment.draw.util;

import java.util.Objects;

public class DrawingUtils {

    private DrawingUtils() {
    }

    public static boolean isInteger(String token) {
        if (Objects.isNull(token)) {
            return Boolean.FALSE;
        }

        try {
            Integer.parseInt(token);

            return Boolean.TRUE;
        } catch (NumberFormatException e) {
            return Boolean.FALSE;
        }
    }

    public static String getHelpText() {
        return "Command \t\tDescription\n" +
                "C w h           Creates a new canvas of width w and height h.\n" +
                "L x1 y1 x2 y2   creates a new line from (x1,y1) to (x2,y2). Currently only\n" +
                "                horizontal or vertical lines are supported. Horizontal and vertical lines\n" +
                "                will be drawn using the 'x' character.\n" +
                "R x1 y1 x2 y2   Creates a new rectangle, whose upper left corner is (x1,y1) and\n" +
                "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
                "                using the 'x' character.\n" +
                "B x y c         Fills the entire area connected to (x,y) with \"colour\" c. The\n" +
                "                behavior of this is the same as that of the \"bucket fill\" tool in paint\n" +
                "                programs.\n" +
                "Q               Quits the program.\n";
    }
}
