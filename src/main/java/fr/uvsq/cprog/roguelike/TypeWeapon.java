package fr.uvsq.cprog.roguelike;

public enum TypeWeapon {
    Axe(" ? ", 10), Bat(" ! ", 5), Gun(" > ", 15);

    private String asciiChar;
    private int damage;

    TypeWeapon(String asciiChar, int damage) {
        this.asciiChar = asciiChar;
        this.damage = damage;
    }

    public String getAsciiChar() {
        return asciiChar;
    }

    public int getDamage() {
        return damage;
    }

}
