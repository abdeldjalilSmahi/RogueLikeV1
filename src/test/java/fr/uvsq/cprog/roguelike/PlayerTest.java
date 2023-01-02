package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void testGetHealth() {

    Player player = new Player(5, 3);

    int health = player.getHealth();

    assertEquals(100, health);
  }

  @Test
  void testSetHealth() {

    Player player = new Player(5, 3);

    player.setHealth(50);

    assertEquals(50, player.getHealth());
  }

  @Test
  void testIsAlive() {

    Player player = new Player(5, 3);

    boolean isAlive = player.isAlive();

    assertTrue(isAlive);

    player.setHealth(0);
    isAlive = player.isAlive();

    assertFalse(isAlive);
  }

  @Test
  void testPickUpWeapon() {

    World world = new World(10);
    Weapon weapon = new Weapon(5, 3, TypeWeapon.Axe);
    world.setObject(weapon);
    Player player = new Player(6, 3);

    player.pickUpWeapon(weapon, world);

    assertEquals(weapon, player.getWeapon());
    assertFalse(world.getWeapons().contains(weapon));
  }

  @Test
  void testAttack() {

    World world = new World(10);
    Weapon weapon = new Weapon(5, 3, TypeWeapon.Axe);
    Player player = new Player(6, 3);
    player.setWeapon(weapon);
    Monster monster = new Monster(5, 3);
    world.setObject(monster);

    player.attack(monster, world);

    assertEquals(40, monster.getHealth());
    assertFalse(world.getMonsters().contains(monster));
  }

  @Test
  void testMove() {
    // Given
    World world = new World(10);
    Player player = new Player(5, 3);
    WorldComponent worldComponent = new WorldComponent(6, 3, WorldComponentsType.SOL, Color.GREEN);
    world.setObject(player);
    world.setObject(worldComponent);

    // When
    player.move(1, 0, world);

    // Then
    assertEquals(6, player.getX());
    assertEquals(3, player.getY());
  }

  @Test
  void testCanMoveTo() {
    // Given
    World world = new World(10);
    Player player = new Player(5, 3);
    world.setObject(player);

    boolean canMove = player.canMoveTo(1, 0, world);

    assertTrue(canMove);
  }
}
