import java.util.Scanner;

class format {
    private static final String QUIT_COMMAND = "Q";

    enum Alignment {
        LEFT, RIGHT, JUSTIFY
    }

    static String createLine(String symbol, int count) {
        return symbol.repeat(count);
    }

    static String formatText(String text, int width, Alignment alignment) {
        width -= 1;
        StringBuilder result = new StringBuilder();
        String[] words = text.replaceAll("\\s+", " ").trim().split(" ");

        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (line.length() + word.length() + 1 > width) {
                result.append(alignLine(line.toString().trim(), width, alignment)).append("\n");
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }

        if (line.length() > 0) {
            result.append(alignLine(line.toString().trim(), width, alignment));
        }

        return result.toString();
    }

    private static String alignLine(String line, int width, Alignment alignment) {
        switch (alignment) {
            case RIGHT:
                return " ".repeat(Math.max(0, width - line.length())) + line;
            case JUSTIFY:
                return justifyLine(line, width);
            default:
                return line;
        }
    }

    private static String justifyLine(String line, int width) {
        String[] words = line.split(" ");
        if (words.length == 1) return line;

        int totalSpaces = width - line.replace(" ", "").length();
        int gaps = words.length - 1;
        int spacesPerGap = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            result.append(words[i]);
            result.append(" ".repeat(spacesPerGap + (i < extraSpaces ? 1 : 0)));
        }
        result.append(words[words.length - 1]);
        return result.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nWelcome to the simple text formatter.\n");
                
                int width = getWidth(scanner);
                if (width == -1) break;  // User wants to quit

                String text = getText(scanner);
                Alignment alignment = getAlignment(scanner);

                String formattedText = formatText(text, width, alignment);
                System.out.println("\nFormatted text:\n" + formattedText);
            }
        }
        System.out.println("Thank you for using the text formatter. Goodbye!");
        System.out.println("");
    }

    private static int getWidth(Scanner scanner) {
        while (true) {
            System.out.println("Enter a line width or input 'Q' to exit: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(QUIT_COMMAND)) {
                return -1;
            }
            try {
                int width = Integer.parseInt(input);
                if (width <= 0) throw new IllegalArgumentException("Width must be positive");
                return width;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getText(Scanner scanner) {
        while (true) {
            System.out.println("\nEnter the text to be formatted: ");
            System.out.println("");
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Text cannot be empty. Please try again.");
        }
    }

    private static Alignment getAlignment(Scanner scanner) {
        while (true) {
            System.out.println("\nSelect a format: (L)eft, (R)ight, (J)ustify");
            String format = scanner.nextLine().trim().toUpperCase();
            switch (format) {
                case "L": return Alignment.LEFT;
                case "R": return Alignment.RIGHT;
                case "J": return Alignment.JUSTIFY;
                default:
                    System.out.println("Invalid format. Please try again.");
            }
        }
    }
}