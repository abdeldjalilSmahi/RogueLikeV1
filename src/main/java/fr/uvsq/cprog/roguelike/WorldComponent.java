package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

/**
 * Classe représentant un composant du monde dans le jeu RogueLike.
 */
public class WorldComponent extends WorldObject {

  /**
   * Type de composant.
   */
  private WorldComponentsType type;

  /**
   * Constructeur de {@code WorldComponent}.
   *
   * @param x     Coordonnée x du composant dans le monde.
   * @param y     Coordonnée y du composant dans le monde.
   * @param type  Type de composant.
   * @param color Couleur du composant dans le monde.
   */
  public WorldComponent(int x, int y, WorldComponentsType type, Color color) {
    super(x, y, type.getAsciiChar(), color);
    this.type = type;
  }

  /**
   * Retourne le type de composant.
   *
   * @return Type de composant.
   */
  public WorldComponentsType getType() {
    return type;
  }

  /**
   * Modifie le type de composant.
   *
   * @param type Type de composant.
   */
  public void setType(WorldComponentsType type) {
    this.type = type;
  }
}
