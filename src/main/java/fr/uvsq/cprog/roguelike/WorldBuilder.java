package fr.uvsq.cprog.roguelike;


import java.util.Random;
import org.fusesource.jansi.Ansi.Color;

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

  /**
   * Ajoute des murs aux quatre côtés du monde.
   *
   * @return Le constructeur de monde avec les murs ajoutés.
   */
  public WorldBuilder addWalls() {
    // Parcourir chaque ligne de la matrice
    for (int j = 0; j < world.getWIDTH(); j++) {
      // first line with obstalces
      WorldComponent worldComponent = new WorldComponent(0, j,
          WorldComponentsType.OBSTACLE, Color.RED);
      world.setObject(worldComponent);
      world.addWorldComponent(worldComponent);
    }

    for (int j = 0; j < world.getWIDTH(); j++) {
      // =last line with obstalces
      WorldComponent worldComponent = new WorldComponent(world.getHEIGHT() - 1,
          j, WorldComponentsType.OBSTACLE, Color.RED);
      world.setObject(worldComponent);
      world.addWorldComponent(worldComponent);
    }
    //
    for (int i = 1; i < world.getHEIGHT() - 1; i++) {
      WorldComponent worldComponent = new WorldComponent(i, 0,
          WorldComponentsType.OBSTACLE, Color.RED);
      world.setObject(worldComponent);
      world.addWorldComponent(worldComponent);
    }
    for (int i = 1; i < world.getHEIGHT() - 1; i++) {
      WorldComponent worldComponent = new WorldComponent(i, world.getWIDTH()
          - 1, WorldComponentsType.OBSTACLE, Color.RED);
      world.setObject(worldComponent);
      world.addWorldComponent(worldComponent);
    }

    return this;
  }

  /**
   * Ajoute des sols (WorldComponent de type SOL) dans le monde.
   *
   * @return Cet objet WorldBuilder, permettant de chaîner les appels de méthodes.
   */
  public WorldBuilder addSols() {
    for (int i = 0; i < world.getWIDTH() - 1; i++) {
      for (int j = 0; j < world.getHEIGHT() - 1; j++) {
        WorldComponent worldComponent = new WorldComponent(i, j,
            WorldComponentsType.SOL, Color.BLUE);
        world.setObject(worldComponent);
        world.addWorldComponent(worldComponent);
      }
    }
    return this;
  }



}
