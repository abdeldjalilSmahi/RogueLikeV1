package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

/**
 * Classe qui définit le joueur.
 *
 * @author jalil
 */
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
    this.health = 10;
    this.score = 0;
    this.weapon = null;
    this.alive = true;
  }

  /**
   * Retourne la vie du joueur.
   *
   * @return La vie du joueur.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Modifie la vie du joueur.
   *
   * @param health La nouvelle vie du joueur.
   */
  public void setHealth(int health) {
    this.health = health;
    if (health <= 0) {
      setAlive(false);
    }
  }

  /**
   * Retourne l'arme du joueur.
   *
   * @return L'arme du joueur.
   */
  public Weapon getWeapon() {
    return weapon;
  }

  /**
   * Modifie l'arme du joueur.
   *
   * @param weapon La nouvelle arme du joueur.
   */
  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * Indique si le joueur est en vie.
   *
   * @return {@code true} si le joueur est en vie, {@code false} sinon.
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Modifie l'état de vie du joueur.
   *
   * @param alive Le nouvel état de vie du joueur.
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  /**
   * Retourne le score du joueur.
   *
   * @return Le score du joueur.
   */
  public int getScore() {
    return score;
  }

  /**
   * Modifie le score du joueur.
   *
   * @param score Le nouveau score du joueur.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Vérifie si le joueur peut se déplacer vers les coordonnées spécifiées.
   *
   * @param dx    Déplacement en x.
   * @param dy    Déplacement en y.
   * @param world Le monde dans lequel se déplace le joueur.
   * @return Vrai si le joueur peut se déplacer, faux sinon.
   */

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
      throw new IllegalArgumentException("Ouupps !! non tu peux pas, c'est un monstre !"
          + "  il faut que tu l'attaque !");
    }
    if (destination instanceof Weapon) {
      throw new IllegalArgumentException("Interdit de passer par une armer,"
          + " il vaut mieux la ramasser");
    }
    if (destination instanceof WorldComponent worldComponent) {
      if (!(worldComponent.getType().equals(WorldComponentsType.SOL))) {
        if (world.getMonsters().isEmpty()
            && (worldComponent.getType().equals(WorldComponentsType.SORTIE))) {
          return true;
        }
        throw new IllegalArgumentException("Ouupps !! non tu peux pas, c'est un obstacle ! ");
      }
    }
    return true;
  }

  /**
   * Fait avancer le personnage dans la direction spécifiée.
   *
   * @param dx    La distance à parcourir en abscisse.
   * @param dy    La distance à parcourir en ordonnée.
   * @param world Le monde dans lequel le personnage se déplace.
   */

  public void move(int dx, int dy, World world) {
    if (canMoveTo(dx, dy, world)) {
      int newX = getX() + dx;
      int newY = getY() + dy;
      world.swapObjects(this, world.getObject(newX, newY));
    }
  }

  /**
   * Ramasse une arme.
   *
   * @param weapon L'arme à ramasser.
   * @param world  Le monde dans lequel le personnage se trouve.
   */


  public void pickUpWeapon(Weapon weapon, World world) {
    if (this.weapon == null) {
      this.weapon = weapon;
      world.removeObject(weapon);  // ! --> .
    } else { // j'ai une arme deja
      Weapon tempWeapon = weapon;
      world.removeObject(weapon);
      this.weapon.setX(tempWeapon.getX());
      this.weapon.setY(tempWeapon.getY());
      world.addWeapon(this.weapon); // add mon ancienne arme to arraylist of weapons
      world.setObject(this.weapon);
      this.weapon = tempWeapon;
    }
  }

  /**
   * Attaque un monstre.
   *
   * @param monster Le monstre à attaquer.
   * @param world   Le monde dans lequel se trouve le monstre.
   */

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
