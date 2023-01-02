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

  /**
   * Ajoute des murs aléatoirement sur la carte de jeu.
   *
   * @return L'instance de {@code WorldBuilder} courante.
   */

  public WorldBuilder addRandomWalls() {
    for (int i = 1; i < this.world.getHEIGHT() - 1; i++) {
      for (int j = 1; j < this.world.getWIDTH() - 2; j++) {
        double proba = new Random().nextDouble();
        if (j == 2) {
          continue;
        } else if (world.getObject(i, j) instanceof WorldComponent) {
          if (((WorldComponent) world.getObject(i, j))
              .getType().equals(WorldComponentsType.SOL)) {
            if (j % 2 != 0) {
              if (proba < 0.5) {
                world.removeWorldComponent((WorldComponent) world.getObject(i, j));
                WorldComponent worldComponent = new WorldComponent(i, j,
                    WorldComponentsType.OBSTACLE, Color.YELLOW);
                world.addWorldComponent(worldComponent);
                world.setObject(worldComponent);
              }
            } else {
              if (proba < 0.1) {
                world.removeWorldComponent((WorldComponent) world.getObject(i, j));
                WorldComponent worldComponent = new WorldComponent(i, j,
                    WorldComponentsType.OBSTACLE, Color.YELLOW);
                world.addWorldComponent(worldComponent);
                world.setObject(worldComponent);
              }

            }
          }


        }

      }
    }

    return this;
  }

  /**
   * Ajoute la sortie de la grille de jeu.
   *
   * @return L'instance actuelle de {@code WorldBuilder} pour permettre l'ajout en chaîne.
   */
  public WorldBuilder addOutput() {
    WorldComponent worldComponent = new WorldComponent(this.world.getWIDTH() - 2,
        this.world.getHEIGHT() - 2, WorldComponentsType.SORTIE, Color.GREEN);
    world.removeWorldComponent((WorldComponent) world.getObject(this.world.getWIDTH() - 2,
        this.world.getHEIGHT() - 2));
    this.world.setObject(worldComponent);
    world.addWorldComponent(worldComponent);
    return this;
  }

  /**
   * Ajoute des monstres au monde.
   *
   * @return L'instance courante de {@code WorldBuilder}.
   */

  public WorldBuilder addMonsters() {
    int nbreMonster = level;
    ;
    int i = 0;
    while (i < nbreMonster) {

      int x;
      int y;
      do {
        x = new Random().nextInt(xBounds) + 1;
        y = new Random().nextInt(this.world.getWIDTH() - 10) + 10;
      } while (!(world.getObject(x, y).getAsciiChar()
          .equals(WorldComponentsType.SOL.getAsciiChar())));
      world.removeWorldComponent((WorldComponent) world.getObject(x, y));
      Monster monster = new Monster(x, y);
      world.setObject(monster);
      world.addMonster(monster);
      i++;
    }
    return this;
  }

  /**
   * Ajoute des armes au monde.
   *
   * @return l'objet {@code WorldBuilder} avec les armes ajoutées.
   */

  public WorldBuilder addWeapons() {
    TypeWeapon[] typeWeapons = TypeWeapon.values();
    for (TypeWeapon typeWeapon : typeWeapons) {
      int x;
      int y;
      do {
        x = new Random().nextInt(xBounds) + 1;
        y = new Random().nextInt(yBounds) + 1;
      } while (!(world.getObject(x, y).getAsciiChar().equals(WorldComponentsType.SOL.getAsciiChar()))
          && !(world.getObject(1, 2).getAsciiChar().equals(WorldComponentsType.SOL.getAsciiChar())));
      world.removeWorldComponent((WorldComponent) world.getObject(x, y));
      Weapon weapon = new Weapon(x, y, typeWeapon);
      world.setObject(weapon);
      world.addWeapon(weapon);

    }
    return this;
  }

  /**
   * Méthode qui ajoute un joueur à la carte du monde.
   *
   * @return L'objet WorldBuilder
   */
  public WorldBuilder addPlayer() {
    Player player = new Player(1, 1);
    world.removeWorldComponent((WorldComponent) world.getObject(1, 1));
    this.world.setObject(player);
    this.world.setPlayer(player);
    return this;
  }

  /**
   * Méthode qui construit l'objet World en cours de création.
   *
   * @return L'objet World créé.
   */
  public World build() {
    return world;
  }

}
