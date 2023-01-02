package fr.uvsq.cprog.roguelike;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

public class World {

  private final int WIDTH = 20;
  private final int HEIGHT = 20;

  private ArrayList<Monster> monsters;
  private ArrayList<Weapon> weapons;

  private ArrayList<WorldComponent> worldComponents;
  private WorldObject[][] world;
  private int level;

  private Player player;

  public World(int level) {
    this.world = new WorldObject[HEIGHT][WIDTH];
    monsters = new ArrayList<>();
    weapons = new ArrayList<>();
    worldComponents = new ArrayList<>();
    this.level = level;
  }

  public int getWIDTH() {
    return WIDTH;
  }

  public int getHEIGHT() {
    return HEIGHT;
  }

  public ArrayList<Monster> getMonsters() {
    return monsters;
  }

  public void setMonsters(ArrayList<Monster> monsters) {
    this.monsters = monsters;
  }

  public ArrayList<Weapon> getWeapons() {
    return weapons;
  }

  public void setWeapons(ArrayList<Weapon> weapons) {
    this.weapons = weapons;
  }

  public ArrayList<WorldComponent> getWorldComponents() {
    return worldComponents;
  }

  public void setWorldComponents(ArrayList<WorldComponent> worldComponents) {
    this.worldComponents = worldComponents;
  }

  public WorldObject[][] getWorld() {
    return world;
  }

  public void setWorld(WorldObject[][] world) {
    this.world = world;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public WorldObject getObject(int x, int y) {
    return world[x][y];
  }

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

    // Vérifier ensuite si l'objet à cette position est le même que celui qui est passé en paramètre
    if (!world[x][y].equals(worldObject)) {
      throw new IllegalArgumentException("Object at position is different from the object being removed");
    }

    // Supprimer l'objet en remplaçant par un composant de type SOL (sol)
    if (worldObject instanceof Monster) {
      this.monsters.remove((Monster) worldObject);
    }
    if (worldObject instanceof WorldComponent) {
      this.worldComponents.remove((WorldComponent) worldObject);
    }
    if (worldObject instanceof Weapon) {
      this.weapons.remove((Weapon) worldObject);
    }
    world[x][y] = new WorldComponent(x, y, WorldComponentsType.SOL, Color.BLUE);
    addWorldComponent((WorldComponent) world[x][y]);
  }

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

  public void addMonster(Monster monster) {
    this.monsters.add(monster);
  }

  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  public void addWorldComponent(WorldComponent worldComponent) {
    this.worldComponents.add(worldComponent);
  }

  public void removeWorldComponent(WorldComponent worldComponent) {
    this.worldComponents.remove(worldComponent);
  }

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
