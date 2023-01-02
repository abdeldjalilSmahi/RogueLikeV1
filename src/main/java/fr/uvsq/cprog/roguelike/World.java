package fr.uvsq.cprog.roguelike;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

/**
 * Classe représentant le monde dans le jeu RogueLike.
 */
public class World {

  private final int WIDTH = 20;
  private final int HEIGHT = 20;

  private ArrayList<Monster> monsters;
  private ArrayList<Weapon> weapons;
  private ArrayList<WorldComponent> worldComponents;
  private WorldObject[][] world;
  private int level;
  private Player player;

  /**
   * Constructeur de {@code World}.
   *
   * @param level Niveau du monde.
   */
  public World(int level) {
    this.world = new WorldObject[HEIGHT][WIDTH];
    monsters = new ArrayList<>();
    weapons = new ArrayList<>();
    worldComponents = new ArrayList<>();
    this.level = level;
  }

  /**
   * Retourne la largeur du monde.
   *
   * @return Largeur du monde.
   */
  public int getWIDTH() {
    return WIDTH;
  }

  /**
   * Retourne la hauteur du monde.
   *
   * @return Hauteur du monde.
   */
  public int getHEIGHT() {
    return HEIGHT;
  }

  /**
   * Retourne la liste de monstres dans le monde.
   *
   * @return Liste de monstres dans le monde.
   */
  public ArrayList<Monster> getMonsters() {
    return monsters;
  }

  /**
   * Modifie la liste de monstres dans le monde.
   *
   * @param monsters Nouvelle liste de monstres dans le monde.
   */
  public void setMonsters(ArrayList<Monster> monsters) {
    this.monsters = monsters;
  }

  /**
   * Retourne la liste d'armes dans le monde.
   *
   * @return Liste d'armes dans le monde.
   */
  public ArrayList<Weapon> getWeapons() {
    return weapons;
  }

  /**
   * Modifie la liste d'armes dans le monde.
   *
   * @param weapons Nouvelle liste d'armes dans le monde.
   */
  public void setWeapons(ArrayList<Weapon> weapons) {
    this.weapons = weapons;
  }

  /**
   * Retourne la liste de composants du monde.
   *
   * @return Liste de composants du monde.
   */
  public ArrayList<WorldComponent> getWorldComponents() {
    return this.worldComponents;
  }

  /**
   * Modifie la liste de composants du monde.
   *
   * @param worldComponents Nouvelle liste de composants du monde.
   */
  public void setWorldComponents(ArrayList<WorldComponent> worldComponents) {
    this.worldComponents = worldComponents;
  }

  /**
   * Retourne le monde sous forme de tableau 2D.
   *
   * @return Monde sous forme de tableau 2D.
   */
  public WorldObject[][] getWorld() {
    return world;
  }

  /**
   * Modifie le monde sous forme de tableau 2D.
   *
   * @param world Nouveau monde sous forme de tableau 2D.
   */
  public void setWorld(WorldObject[][] world) {
    this.world = world;
  }

  /**
   * Retourne le niveau actuel du monde.
   *
   * @return Niveau actuel du monde.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Modifie le niveau actuel du monde.
   *
   * @param level Nouveau niveau actuel du monde.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Retourne le joueur dans le monde.
   *
   * @return Joueur dans le monde.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Modifie le joueur dans le monde.
   *
   * @param player Nouveau joueur dans le monde.
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Retourne l'objet à la position x, y du monde.
   *
   * @param x Abscisse de la position de l'objet.
   * @param y Ordonnée de la position de l'objet.
   * @return Objet à la position x, y du monde.
   */
  public WorldObject getObject(int x, int y) {
    return world[x][y];
  }

  /**
   * Modifie l'objet à la position spécifiée dans le monde.
   *
   * @param worldObject Objet à ajouter au monde.
   * @throws IllegalArgumentException Si la position de l'objet est en dehors des limites du monde.
   * @throws IllegalArgumentException Si l'objet à cette position est différent de celui passé en paramètre.
   * @throws IllegalArgumentException Si la position est déjà vide.
   */
  public void setObject(WorldObject worldObject) {
    // Vérifier d'abord si la position est valide
    int x = worldObject.getX();
    int y = worldObject.getY();
    if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
      throw new IllegalArgumentException("Object position is out of bounds");
    }
    // Supprimer l'objet existant à cette position s'il est remplie par une sol si non
    if (getObject(x, y) == null) {
      world[x][y] = worldObject;
    }
    world[x][y] = worldObject;
  }


  /**
   * Supprime l'objet à la position spécifiée dans le monde.
   *
   * @param worldObject Objet à supprimer du monde.
   * @throws IllegalArgumentException Si la position de l'objet est en dehors des limites du monde.
   * @throws IllegalArgumentException Si l'objet à cette position est différent de celui passé en paramètre.
   * @throws IllegalArgumentException Si la position est déjà vide.
   */
  public void removeObject(WorldObject worldObject) {
    // Vérifier d'abord si la position est vide
    int x = worldObject.getX();
    int y = worldObject.getY();
    if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
      throw new IllegalArgumentException("Object position is out of bounds");
    }
    if (world[x][y] == null) {
      throw new IllegalArgumentException("Object position is already empty");
    }

    // Supprimer l'objet en remplaçant par un composant de type SOL (sol)
    if(worldObject instanceof Monster){
      this.monsters.remove((Monster) worldObject);
    }
    if(worldObject instanceof WorldComponent){
      this.worldComponents.remove((WorldComponent) worldObject);
    }
    if(worldObject instanceof Weapon){
      this.weapons.remove((Weapon)worldObject);
    }

    // Supprimer l'objet en mettant à sa place une instance de WorldComponent de type SOL
    world[x][y] = new WorldComponent(x, y, WorldComponentsType.SOL, Color.BLUE);
    addWorldComponent((WorldComponent) world[x][y]);
  }

  /**
   * Échange les objets dans le monde.
   *
   * @param obj1 Premier objet à échanger
   * @param obj2 Deuxième objet à échanger
   */
  public void swapObjects(WorldObject obj1, WorldObject obj2) {
    int x1 = obj1.getX();
    int y1 = obj1.getY();
    int x2 = obj2.getX();
    int y2 = obj2.getY();
    world[x1][y1] = obj2;
    obj2.setX(x1);
    obj2.setY(y1);
    world[x2][y2] = obj1;
    obj1.setX(x2);
    obj1.setY(y2);
  }

  /**
   * Ajoute un monstre dans le monde.
   *
   * @param monster Monstre à ajouter
   */
  public void addMonster(Monster monster) {
    this.monsters.add(monster);
  }

  /**
   * Ajoute une arme dans le monde.
   *
   * @param weapon Arme à ajouter
   */
  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  /**
   * Ajoute un composant dans le monde.
   *
   * @param worldComponent Le composant à ajouter.
   */
  public void addWorldComponent(WorldComponent worldComponent) {
    this.worldComponents.add(worldComponent);
  }

  /**
   * Supprime un composant du monde.
   *
   * @param worldComponent Le composant à supprimer.
   */
  public void removeWorldComponent(WorldComponent worldComponent) {
    this.worldComponents.remove(worldComponent);
  }

  /**
   * Affiche le monde dans la console.
   */
  public void display() {
    AnsiConsole.systemInstall();
    for (int i = 0; i < this.HEIGHT; i++) {
      for (int j = 0; j < this.WIDTH; j++) {
        System.out.print(ansi().fg(world[i][j].getColor()).a(world[i][j]).reset());
      }
      System.out.println();
    }
  }

}
