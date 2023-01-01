package fr.uvsq.cprog.roguelike;

/**
 * Énumération des différents types d'armes dans le jeu RogueLike.
 */
public enum TypeWeapon {

    /** Hache, inflige 10 points de dégâts. */
    Axe(" ? ", 10),
    /** Batte, inflige 5 points de dégâts. */
    Bat(" ! ", 5),
    /** Pistolet, inflige 15 points de dégâts. */
    Gun(" > ", 15);

    /** Caractère ASCII représentant l'arme dans le monde. */
    private String asciiChar;
    /** Dégâts infligés par l'arme. */
    private int damage;

    /**
     * Constructeur de {@code TypeWeapon}.
     *
     * @param asciiChar Caractère ASCII représentant l'arme dans le monde.
     * @param damage    Dégâts infligés par l'arme.
     */
    TypeWeapon(String asciiChar, int damage) {
        this.asciiChar = asciiChar;
        this.damage = damage;
    }

    /**
     * Retourne le caractère ASCII représentant l'arme dans le monde.
     *
     * @return Caractère ASCII représentant l'arme dans le monde.
     */
    public String getAsciiChar() {
        return asciiChar;
    }

    /**
     * Retourne les dégâts infligés par l'arme.
     *
     * @return Dégâts infligés par l'arme.
     */
    public int getDamage() {
        return damage;
    }
}
