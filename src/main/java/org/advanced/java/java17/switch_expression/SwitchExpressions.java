package org.advanced.java.java17.switch_expression;

enum Direction {NORTH, SOUTH, EAST, WEST}

public class SwitchExpressions {
    public static void main(String[] args) {
        Direction d = Direction.NORTH;

        // Regular switch statement (fall through => requires 'break')
        int numLetters = 0;
        switch (d) {
            case NORTH:
            case SOUTH:
                numLetters = 5;
                break;
            case EAST:
            case WEST:
                numLetters = 4;
                break;
        }
        System.out.println("Old switch, numLetters: " + numLetters);

        /**
         * Switch expressions came in Java 14. They result in a single value
         * They make use of the '->' token.
         * there is no fall-trough logic.
         * Case labels must cover all values, thus 'default' is mandatory.
         * Labels with ":" requires a 'break' or 'yield'.
         * If using the '->' token, then there is no fall-through.
         */
        System.out.println("Switch Expression: " +
                switch (d) {
                    case NORTH, SOUTH -> 5;
                    default -> 4;
//                    case NORTH, SOUTH: yield 5;
//                    default: yield 4;
        });

        System.out.println("Sunday is: " + getDayType("Sunday"));
        System.out.println("Tuesday is: " + getDayType("Tuesday"));
        System.out.println("Xxx is: " + getDayType("Xxx"));
    }

    public static String getDayType(String day) {
        var result = switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" :
                yield "Weekday";
            case "Saturday", "Sunday":
                yield "Weekend";
            default:
                yield "Invalid day";
        };

        return result;
    }
}
