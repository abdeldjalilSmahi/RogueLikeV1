package fr.uvsq.cprog.roguelike;

import java.util.Random;
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
    this.health = 50;
    this.alive = true;
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
    int newX = this.getX() + dx;
    int newY = this.getY() + dy;
    if (newX == 0 || newX == world.getHEIGHT() - 1 || newY == 0 || newY == world.getWIDTH() - 1) {
      return false;
    }
    if (newX < 0 || newX > world.getHEIGHT() - 1 || newY < 0 || newY > world.getWIDTH() - 1) {
      return false;
    }
    WorldObject destination = world.getObject(newX, newY);
    if (destination instanceof Personnage) {

      return false;
    }
    if (destination instanceof Weapon) {

      return false;
    } else if (destination instanceof WorldComponent) {
      WorldComponent worldComponent = (WorldComponent) destination;
      if (!(worldComponent.getAsciiChar().equals(WorldComponentsType.SOL.getAsciiChar()))) {

        return false;
      }

    }
    return true;
  }

  public void move(World world) {
    int dx = (new Random().nextInt(3)) - 1;
    int dy = dx == 0 ? (Math.random() < 0.5 ? -1 : 1) : 0;
    if (canMoveTo(dx, dy, world)) {
      int newY = this.getY() + dy;
      WorldObject object = world.getObject(this.getX(), newY);
      world.swapObjects(this, object);
    }

  }
}
