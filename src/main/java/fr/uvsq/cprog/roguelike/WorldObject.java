package fr.uvsq.cprog.roguelike;

public class WorldObject {
    protected int x;
    protected int y;
    private String asciiChar;

    public WorldObject(int x, int y, String asciiChar) {
        this.x = x;
        this.y = y;
        this.asciiChar = asciiChar;
    }
}
