package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;

public class Player extends Personnage{

  private int  health;
  private Weapon weapon;
  private boolean alive ;
  private int score  ;


  /**
   * Constructeur de {@code Personnage}.
   *
   * @param x         Coordonnée x du personnage dans le monde.
   * @param y         Coordonnée y du personnage dans le monde.
   * @param asciiChar Caractère ASCII représentant le personnage dans le monde.
   * @param color     Couleur du personnage dans le monde.
   */
  public Player(int x, int y, String asciiChar, Color color) {
    super(x, y, " @ ", Color.CYAN);
    this.health = 100 ;
    this.score = 0 ;
    this.weapon = null ;
    this.alive = true ;
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
  public void canMoveTo(int dx, int dyn, World world) {



  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
