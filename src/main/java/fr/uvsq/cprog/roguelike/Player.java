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

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
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
    if (destination instanceof WorldComponent worldComponent) {
      if (!(worldComponent.getType().equals(WorldComponentsType.SOL))) {
        if (world.getMonsters().isEmpty() && (worldComponent.getType().equals(WorldComponentsType.SORTIE))) {
          return true;
        }
        throw new IllegalArgumentException("Ouupps !! non tu peux pas, c'est un obstacle ! ");
      }
    }
    return true;
  }

  public void move(int dx, int dy, World world) {
    if (canMoveTo(dx, dy, world)) {
      int newX = getX() + dx;
      int newY = getY() + dy;
      world.swapObjects(this, world.getObject(newX, newY));
    }
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

  public void attack(Monster monster, World world) {
    if (weapon != null) {
      monster.setHealth(monster.getHealth() - weapon.getDamage());
      if (monster.getHealth() <= 0) {
        world.removeObject(monster);
        monster.setAlive(false);
      }
    } else {
      System.out.println("Run away !!  you have no weapon! ");
    }
  }
}
