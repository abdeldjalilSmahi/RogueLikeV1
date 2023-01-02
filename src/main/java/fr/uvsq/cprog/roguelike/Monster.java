package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public class Monster extends Personnage {

  private int health;

  private boolean alive;

  private final int DAMAGE = 5;

  /**
   * Constructeur de {@code Personnage}.
   *
   * @param x Coordonnée x du personnage dans le monde.
   * @param y Coordonnée y du personnage dans le monde.
   */
  public Monster(int x, int y) {
    super(x, y, " $ ", Color.MAGENTA);
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public int getDAMAGE() {
    return DAMAGE;
  }

  @Override
  public boolean canMoveTo(int dx, int dy, World world) {
    return true ;
  }
}
