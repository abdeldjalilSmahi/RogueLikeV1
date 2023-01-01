package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

/**
 * Classe représentant une arme dans le jeu RogueLike.
 */
public class Weapon extends WorldObject {

    /** Type d'arme. */
    private TypeWeapon type;
    /** Dégâts infligés par l'arme. */
    private int damage;

    /**
     * Constructeur de {@code Weapon}.
     *
     * @param x     Coordonnée x de l'arme dans le monde.
     * @param y     Coordonnée y de l'arme dans le monde.
     * @param type  Type d'arme.
     */
    public Weapon(int x, int y, TypeWeapon type) {
        super(x, y, type.getAsciiChar(), Color.WHITE);
        this.type = type;
        this.damage = type.getDamage();
    }

    /**
     * Retourne le type d'arme.
     *
     * @return Type d'arme.
     */
    public TypeWeapon getType() {
        return type;
    }

    /**
     * Modifie le type d'arme.
     *
     * @param type Type d'arme.
     */
    public void setType(TypeWeapon type) {
        this.type = type;
        setAsciiChar(type.getAsciiChar());
    }

    /**
     * Retourne les dégâts infligés par l'arme.
     *
     * @return Dégâts infligés par l'arme.
     */
    public int getDamage() {
        return this.damage;
    }
}
