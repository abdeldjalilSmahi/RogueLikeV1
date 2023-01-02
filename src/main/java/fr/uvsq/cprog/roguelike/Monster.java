package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public class Monster extends Personnage{

  /**
   * Constructeur de {@code Personnage}.
   *
   * @param x         Coordonnée x du personnage dans le monde.
   * @param y         Coordonnée y du personnage dans le monde.
   * @param asciiChar Caractère ASCII représentant le personnage dans le monde.
   * @param color     Couleur du personnage dans le monde.
   */
  public Monster(int x, int y, String asciiChar, Color color) {
    super(x, y, asciiChar, color);
  }

  @Override
  public void canMoveTo(int dx, int dy) {

  }
}