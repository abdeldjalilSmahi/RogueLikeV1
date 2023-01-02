package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldBuilderTest {

  private WorldBuilder worldBuilder;

  @BeforeEach
  void setUp() {
    worldBuilder = new WorldBuilder(1);
  }

  @Test
  void testReset() {
    worldBuilder.reset(1);
    assertNotNull(worldBuilder.build());
    assertEquals(1, worldBuilder.build().getLevel());
  }

  @Test
  void testAddWalls() {
    worldBuilder.addWalls();
    assertTrue(worldBuilder.build().getWorldComponents().size() >= 4);
  }

  @Test
  void testAddSols() {

    worldBuilder.addSols();
    assertTrue(worldBuilder.build().getWorldComponents().size() >= 19*19);
  }

  @Test
  void testAddRandomWalls() {
    worldBuilder.addSols().addRandomWalls();
    assertTrue(worldBuilder.build().getWorldComponents().size() >= 4);
  }



  @Test
  void testAddRandomWeapons() {
    worldBuilder.addSols().addWeapons();
    assertTrue(worldBuilder.build().getWeapons().size() >= 1);
  }

  @Test
  void testAddPlayer() {
    worldBuilder.addPlayer();
    assertNotNull(worldBuilder.build().getPlayer());
  }

  @Test
  void testBuild() {
    worldBuilder.addWalls().addSols().addRandomWalls().addMonsters().addWeapons().addPlayer();
    World world = worldBuilder.build();
    assertNotNull(world);
    assertTrue(world.getWorldComponents().size() >= 4);
    assertTrue(world.getMonsters().size() >= 1);
    assertTrue(world.getWeapons().size() >= 1);
    assertNotNull(world.getPlayer());
  }

}
