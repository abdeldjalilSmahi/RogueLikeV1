package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WorldTest {


  private World world;
  private Player player;
  private Monster monster;
  private Weapon weapon;

  @BeforeEach
  public void setUp() {
    world = new World(1);
    player = new Player(0, 0);
    monster = new Monster(1, 1);
    weapon = new Weapon(2, 2, TypeWeapon.Axe);
  }

  @Test
  public void testGetMonsters() {
    assertTrue(world.getMonsters().isEmpty());
  }

  @Test
  public void testSetMonsters() {
    world.setMonsters(new ArrayList<>());
    assertTrue(world.getMonsters().isEmpty());
  }

  @Test
  public void testGetWeapons() {
    assertTrue(world.getWeapons().isEmpty());
  }

  @Test
  public void testSetWeapons() {
    world.setWeapons(new ArrayList<>());
    assertEquals(0, world.getWeapons().size());
    Weapon weapon = new Weapon(5, 5, TypeWeapon.Axe);
    world.addWeapon(weapon);
    assertEquals(1, world.getWeapons().size());
  }

  @Test
  public void testRemoveWeapon() {
    Weapon weapon = new Weapon(5, 5, TypeWeapon.Axe);
    world.addWeapon(weapon);
    assertEquals(1, world.getWeapons().size());
    world.getWeapons().remove(weapon);
    assertEquals(0, world.getWeapons().size());
  }

  @Test
  public void testAddWorldComponent() {
    WorldComponent worldComponent = new WorldComponent(5, 5, WorldComponentsType.OBSTACLE, Color.RED);
    assertEquals(0, world.getWorldComponents().size());
    world.addWorldComponent(worldComponent);
    assertEquals(1, world.getWorldComponents().size());
  }

  @Test
  public void testRemoveWorldComponent() {
    WorldComponent worldComponent = new WorldComponent(5, 5, WorldComponentsType.OBSTACLE, Color.RED);
    world.addWorldComponent(worldComponent);
    assertEquals(1, world.getWorldComponents().size());
    world.removeWorldComponent(worldComponent);
    assertEquals(0, world.getWorldComponents().size());
  }

}
