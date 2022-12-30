package fr.uvsq.cprog.roguelike;

public enum WorldComponentsType {
    OBSTACLE(" # "), SOL(" . "), SORTIE(" 0 ");

    private String asciiChar;

    WorldComponentsType(String asciiChar) {
        this.asciiChar = asciiChar;
    }

    public String getAsciiChar() {
        return asciiChar;
    }

}
