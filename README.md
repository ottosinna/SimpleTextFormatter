This project is a simple text formatter in Java that allows users to format text according to specified width and alignment options. The program reads user input for text, desired line width, and alignment type (left, right, justify) via the console. It processes the input text to fit within the specified width by breaking lines appropriately and aligning the text according to the selected alignment. The formatted text is then displayed back to the user. The program continues to prompt for input until the user chooses to exit by entering a quit command. This project demonstrates handling user input, string manipulation, and text formatting using Java's built-in utilities and custom logic.

createLine(String symbol, int count): Creates a line of repeated symbols.
formatText(String text, int width, Alignment alignment): Formats the input text according to the specified width and alignment. It splits the text into words, constructs lines, and aligns them.
alignLine(String line, int width, Alignment alignment): Aligns a given line based on the specified alignment.
justifyLine(String line, int width): Justifies a line by evenly distributing spaces between words.
getWidth(Scanner scanner): Prompts the user for the line width and validates input.
getText(Scanner scanner): Prompts the user for text input, ensuring it's not empty.
getAlignment(Scanner scanner): Prompts the user to select an alignment option.
