package fr.uvsq.cprog.roguelike;

import java.util.ArrayList;

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
}
