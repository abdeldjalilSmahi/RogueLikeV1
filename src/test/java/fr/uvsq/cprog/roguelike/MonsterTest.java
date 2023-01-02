package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class MonsterTest {

  @Test
  void testMove() {

    World world = new World(5);
    Monster monster = new Monster(1, 1);
    world.setObject(monster);

    monster.move(world);

    assertNotEquals(2, monster.getX());
    assertEquals(1, monster.getY());
  }

  @Test
  void testAttack() {

    Player player = new Player(1, 1);
    Monster monster = new Monster(2, 2);

    monster.attack(player);

    assertEquals(90, player.getHealth());
  }

  @Test
  void testIsAlive() {

    Monster monster = new Monster(1, 1);
    monster.setHealth(0);

    boolean isAlive = monster.isAlive();

    assertFalse(isAlive);
  }
}