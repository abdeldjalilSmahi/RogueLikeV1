package fr.uvsq.cprog.roguelike;


/**
 * Classe construisant le {@link World}.
 *
 * @author jalil
 */

public class WorldBuilder {

  private World world;

  private int xBounds;
  private int yBounds;
  private int level;

  /**
   * Constructeur de {@code WorldBuilder}.
   *
   * @param level Le niveau du monde à construire.
   */
  public WorldBuilder(int level) {

    this.level = level;
    reset(level);
  }

  /**
   * Réinitialise le constructeur de monde avec un nouveau niveau.
   *
   * @param level Le niveau du monde à construire.
   */
  public void reset(int level) {
    this.world = new World(level);
    this.xBounds = this.world.getHEIGHT() - 2 - 1;
    this.yBounds = this.world.getWIDTH() - 2 - 1;
  }


}
