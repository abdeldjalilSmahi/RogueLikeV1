package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public class WorldComponent extends WorldObject{

  private WorldComponentsType type ;


  /**
   * Constructeur de {@code WorldObject}.
   *
   * @param x         Coordonnée x de l'objet dans le monde.
   * @param y         Coordonnée y de l'objet dans le monde.
   * @param asciiChar Caractère ASCII représentant l'objet dans le monde.
   * @param color     Couleur de l'objet dans le monde.
   */
  public WorldComponent(int x, int y, WorldComponentsType type, Color color) {
    super(x, y, type.getAsciiChar(), color);
    this.type = type;
  }

  public WorldComponentsType getType() {
    return type;
  }

  public void setType(WorldComponentsType type) {
    this.type = type;
  }
}
