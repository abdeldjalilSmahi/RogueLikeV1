package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public class Player extends Personnage {

  private int health;
  private Weapon weapon;
  private boolean alive;
  private int score;


  /**
   * Constructeur de {@code Personnage}.
   *
   * @param x Coordonnée x du personnage dans le monde.
   * @param y Coordonnée y du personnage dans le monde.
   */
  public Player(int x, int y) {
    super(x, y, " @ ", Color.CYAN);
    this.health = 100;
    this.score = 0;
    this.weapon = null;
    this.alive = true;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  @Override
  public boolean canMoveTo(int dx, int dy, World world) {
    int newX = this.getX() + dx;
    int newY = this.getY() + dy;
    if (newX == 0 || newX == world.getHEIGHT() - 1 || newY == 0 || newY == world.getWIDTH() - 1) {
      throw new IllegalArgumentException("Interdit de toucher les bordures du World");
    }
    if (newX < 0 || newX > world.getHEIGHT() - 1 || newY < 0 || newY > world.getWIDTH() - 1) {
      throw new IndexOutOfBoundsException("tu essaie de dépasser les bordures ? Hein !! ");
    }
    WorldObject destination = world.getObject(newX, newY);
    if (destination instanceof Monster) {
      throw new IllegalArgumentException("Ouupps !! non tu peux pas, c'est un monstre !  il faut que tu l'attaque !");
    }
    if (destination instanceof Weapon) {
      throw new IllegalArgumentException("Interdit de passer par une armer, il vaut mieux la ramasser");
    }
    if (destination instanceof WorldComponent) {
      WorldComponent worldComponent = (WorldComponent) destination;
      if (!(worldComponent.getType().equals(WorldComponentsType.SOL))) {
        if (world.getMonsters().isEmpty() && (worldComponent.getType().equals(WorldComponentsType.SORTIE))) {
          return true;
        }
        throw new IllegalArgumentException("Ouupps !! non tu peux pas, c'est un obstacle ! ");
      }
    }
    return true;
  }


  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void pickUpWeapon(Weapon weapon, World world) {
    if (this.weapon == null) {
      this.weapon = weapon;
      world.removeObject(weapon);  // ! --> .
    } else { // j'ai une arme deja
      Weapon tempWeapon = weapon;
      world.removeObject(weapon); // je le rends sol ! --> . // remove from arraylist also // add . to list of comp
      this.weapon.setX(tempWeapon.getX());
      this.weapon.setY(tempWeapon.getY());
      world.addWeapon(this.weapon); // add mon ancienne arme to arraylist of weapons
      world.setObject(this.weapon);
      this.weapon = tempWeapon;
    }
  }
}
