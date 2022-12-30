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

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getAsciiChar() {
        return asciiChar;
    }

    public void setAsciiChar(String asciiChar) {
        this.asciiChar = asciiChar;
    }

    @Override
    public String toString() {
        return getAsciiChar() ;
    }

}
