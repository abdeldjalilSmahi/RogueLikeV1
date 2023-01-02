package fr.uvsq.cprog.roguelike;


/**
 * Classe principale du jeu qui gère l'exécution de la partie.
 */
public class Game {

  private transient Player player;
  private World world;
  private int level;

  private boolean isFinished;


  /**
   * Constructeur de la classe Game qui crée un nouveau monde en fonction du niveau choisi.
   *
   * @param level le niveau choisi pour la partie
   */
  public Game(int level) {
    this.level = level;

    this.world = new WorldBuilder(level)
        .addSols()
        .addWalls()
        .addRandomWalls()
        .addMonsters()
        .addWeapons()
        .addPlayer()
        .addOutput()
        .build();
    this.player = world.getPlayer();
    this.isFinished = false;
  }
}
