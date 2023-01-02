package fr.uvsq.cprog.roguelike;

import java.util.ArrayList;

public class World {
  private final int WIDTH = 20;
  private final int HEIGHT = 20;

  private ArrayList<Monster> monsters;
  private  ArrayList<Weapon> weapons;

  private  ArrayList<WorldComponent> worldComponents;
  private  WorldObject[][] world;
  private int level ;

  private Player player ;
}
