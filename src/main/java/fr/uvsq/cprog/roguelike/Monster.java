package fr.uvsq.cprog.roguelike;

import java.util.Random;
import org.fusesource.jansi.Ansi.Color;

/**
 * Classe representant un monstre dans le monde du jeu.
 *
 * @author jalil
 */

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

  /**
   * Classe representant un monstre dans le monde du jeu.
   *
   * @author Charles-Etienne TABUSSE
   */
  public int getHealth() {
    return health;
  }

  /**
   * Modifie la vie du monstre.
   *
   * @param health La nouvelle vie du monstre.
   */

  public void setHealth(int health) {
    this.health = health;
    if (health <= 0) {
      setAlive(false);
    }
  }

  /**
   * Retourne l'état de vie du monstre.
   *
   * @return {@code true} si le monstre est en vie, {@code false} sinon.
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Modifie l'état de vie du monstre.
   *
   * @param alive Le nouvel état de vie du monstre.
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  /**
   * Retourne les dégâts infligés par le monstre.
   *
   * @return Les dégâts infligés par le monstre.
   */

  public int getDAMAGE() {
    return DAMAGE;
  }

  /**
   * Vérifie si le monstre peut se déplacer vers les coordonnées spécifiées.
   *
   * @param dx    Déplacement en x.
   * @param dy    Déplacement en y.
   * @param world Le monde dans lequel se déplace le monstre.
   * @return Vrai si le monstre peut se déplacer, faux sinon.
   */
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

  /**
   * Déplace le monstre de manière aléatoire dans le monde.
   *
   * @param world Le monde dans lequel se déplace le monstre.
   */

  public void move(World world) {
    int dx = (new Random().nextInt(3)) - 1;
    int dy = dx == 0 ? (Math.random() < 0.5 ? -1 : 1) : 0;
    if (canMoveTo(dx, dy, world)) {
      int newY = this.getY() + dy;
      WorldObject object = world.getObject(this.getX(), newY);
      world.swapObjects(this, object);
    }
  }

  /**
   * Attaque le joueur.
   *
   * @param player Le joueur à attaquer.
   */
  public void attack(Player player) {
    player.setHealth(player.getHealth() - this.DAMAGE);
    if (player.getHealth() <= 0) {
      player.setHealth(0);
      player.setAlive(false);
    }
  }
}
