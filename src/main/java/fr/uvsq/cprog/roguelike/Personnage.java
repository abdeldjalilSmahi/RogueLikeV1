package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

/**
 * Classe abstraite représentant un personnage dans le jeu RogueLike.
 */
public abstract class Personnage extends WorldObject {

  /**
   * Constructeur de {@code Personnage}.
   *
   * @param x         Coordonnée x du personnage dans le monde.
   * @param y         Coordonnée y du personnage dans le monde.
   * @param asciiChar Caractère ASCII représentant le personnage dans le monde.
   * @param color     Couleur du personnage dans le monde.
   */
  public Personnage(int x, int y, String asciiChar, Color color) {
    super(x, y, asciiChar, color);
  }

  /**
   * Méthode permettant de déterminer si le personnage peut se déplacer vers une nouvelle position.
   *
   * @param dx Déplacement sur l'axe x.
   * @param dy Déplacement sur l'axe y.
   */
  public abstract void canMoveTo(int dx, int dy);
}
