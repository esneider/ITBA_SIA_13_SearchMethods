package tp1.problem;

public enum Cell {

    OPENED('.'),
    CLOSED('#'),
    FLAG  ('F');

    private final char character;

    private Cell(char character) {

        this.character = character;
    }

    public String toString() {

        return "" + character;
    };
}
