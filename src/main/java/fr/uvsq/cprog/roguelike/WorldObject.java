package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

/**
 * Classe représentant un objet du monde dans le jeu RogueLike.
 */
public class WorldObject {

  /**
   * Coordonnée x de l'objet dans le monde.
   */
  protected int x;
  /**
   * Coordonnée y de l'objet dans le monde.
   */
  protected int y;
  /**
   * Caractère ASCII représentant l'objet dans le monde.
   */
  private String asciiChar;
  /**
   * Couleur de l'objet dans le monde.
   */
  private Color color;

  /**
   * Constructeur de {@code WorldObject}.
   *
   * @param x         Coordonnée x de l'objet dans le monde.
   * @param y         Coordonnée y de l'objet dans le monde.
   * @param asciiChar Caractère ASCII représentant l'objet dans le monde.
   * @param color     Couleur de l'objet dans le monde.
   */
  public WorldObject(int x, int y, String asciiChar, Color color) {
    this.color = color;
    this.x = x;
    this.y = y;
    this.asciiChar = asciiChar;
  }

  /**
   * Retourne la coordonnée x de l'objet dans le monde.
   *
   * @return Coordonnée x de l'objet dans le monde.
   */
  public int getX() {
    return x;
  }

  /**
   * Modifie la coordonnée x de l'objet dans le monde.
   *
   * @param x Coordonnée x de l'objet dans le monde.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Retourne la coordonnée y de l'objet dans le monde.
   *
   * @return Coordonnée y de l'objet dans le monde.
   */
  public int getY() {
    return y;
  }

  /**
   * Modifie la coordonnée y de l'objet dans le monde.
   *
   * @param y Coordonnée y de l'objet dans le monde.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Retourne le caractère ASCII représentant l'objet dans le monde.
   *
   * @return Caractère ASCII représentant l'objet dans le monde.
   */
  public String getAsciiChar() {
    return asciiChar;
  }

  /**
   * Modifie le caractère ASCII représentant /** Modifie le caractère ASCII représentant l'objet dans le monde.
   *
   * @param asciiChar Caractère ASCII représentant l'objet dans le monde.
   */
  public void setAsciiChar(String asciiChar) {
    this.asciiChar = asciiChar;
  }

  /**
   * Retourne la couleur de l'objet dans le monde.
   *
   * @return Couleur de l'objet dans le monde.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Modifie la couleur de l'objet dans le monde.
   *
   * @param color Couleur de l'objet dans le monde.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Retourne le caractère ASCII représentant l'objet dans le monde.
   *
   * @return Caractère ASCII représentant l'objet dans le monde.
   */
  @Override
  public String toString() {
    return getAsciiChar();
  }
}