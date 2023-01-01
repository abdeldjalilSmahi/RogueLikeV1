package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public abstract class Personnage extends WorldObject {

  /**
   * Constructeur de {@code WorldObject}.
   *
   * @param x         Coordonnée x de l'objet dans le monde.
   * @param y         Coordonnée y de l'objet dans le monde.
   * @param asciiChar Caractère ASCII représentant l'objet dans le monde.
   * @param color     Couleur de l'objet dans le monde.
   */
  public Personnage(int x, int y, String asciiChar, Color color) {
    super(x, y, asciiChar, color);
  }

  public abstract void canMoveTo(int dx, int dy);
}
