package fr.uvsq.cprog.roguelike;

/**
 * Énumération des différents types de composants du monde dans le jeu RogueLike.
 */
public enum WorldComponentsType {

    /** Obstacle, représenté par un caractère #. */
    OBSTACLE(" # "),
    /** Sol, représenté par un caractère . */
    SOL(" . "),
    /** Sortie, représentée par un caractère 0. */
    SORTIE(" 0 ");

    /** Caractère ASCII représentant le composant dans le monde. */
    private String asciiChar;

    /**
     * Constructeur de {@code WorldComponentsType}.
     *
     * @param asciiChar Caractère ASCII représentant le composant dans le monde.
     */
    WorldComponentsType(String asciiChar) {
        this.asciiChar = asciiChar;
    }

    /**
     * Retourne le caractère ASCII représentant le composant dans le monde.
     *
     * @return Caractère ASCII représentant le composant dans le monde.
     */
    public String getAsciiChar() {
        return asciiChar;
    }
}
