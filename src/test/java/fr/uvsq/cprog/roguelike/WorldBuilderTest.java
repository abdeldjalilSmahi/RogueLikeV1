package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.uvsq.cprog.roguelike.WorldComponent;
import fr.uvsq.cprog.roguelike.WorldComponentsType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.roguelike.WorldBuilder;

public class WorldBuilderTest {

  private WorldBuilder worldBuilder;

  @BeforeEach
  public void setUp() {
    worldBuilder = new WorldBuilder(1);
  }

  @Test
  public void testAddWalls() {
    worldBuilder.addWalls();

    // Vérifie que les coins de la matrice sont bien des obstacles
    assertTrue(worldBuilder.build().getObject(0, 0) instanceof WorldComponent &&
        ((WorldComponent) worldBuilder.build().getObject(0, 0)).getType().equals(WorldComponentsType.OBSTACLE));
    assertTrue(worldBuilder.build().getObject(0, worldBuilder.build().getWIDTH() - 1) instanceof WorldComponent &&
        ((WorldComponent) worldBuilder.build().getObject(0, worldBuilder.build().getWIDTH() - 1)).getType().equals(WorldComponentsType.OBSTACLE));
    assertTrue(worldBuilder.build().getObject(worldBuilder.build().getHEIGHT() - 1, 0) instanceof WorldComponent &&
        ((WorldComponent) worldBuilder.build().getObject(worldBuilder.build().getHEIGHT() - 1, 0)).getType().equals(WorldComponentsType.OBSTACLE));
    assertTrue(worldBuilder.build().getObject(worldBuilder.build().getHEIGHT() - 1, worldBuilder.build().getWIDTH() - 1) instanceof WorldComponent &&
        ((WorldComponent) worldBuilder.build().getObject(worldBuilder.build().getHEIGHT() - 1, worldBuilder.build().getWIDTH() - 1)).getType().equals(WorldComponentsType.OBSTACLE));
  }

  @Test
  public void testAddSols() {
    worldBuilder.addSols();

    // Vérifie que tous les éléments de la matrice sont bien des sols
    for (int i = 0; i < worldBuilder.build().getWIDTH()-1; i++) {
      for (int j = 0; j < worldBuilder.build().getHEIGHT()-1; j++) {
        assertTrue((worldBuilder.build().getObject(i, j)) instanceof WorldComponent );
      }
    }
  }

  @Test
  public void testAddRandomWalls() {
    World world = worldBuilder.addSols().addWalls().addRandomWalls().build();

    // Vérifie que certains éléments de la matrice sont bien des obstacles
    boolean hasObstacle = false;
    for (int i = 1; i < world.getHEIGHT()-1; i++) {
      for (int j = 1; j < world.getWIDTH() - 1; j++) {
        if (world.getObject(i, j) instanceof WorldComponent &&
            ((WorldComponent) world.getObject(i, j)).getType().equals(WorldComponentsType.OBSTACLE)) {
          hasObstacle = true;
          break;
        }
      }
    }
    assertTrue(hasObstacle);
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


  @Test
  void testReset() {
    worldBuilder.reset(1);
    assertNotNull(worldBuilder.build());
    assertEquals(1, worldBuilder.build().getLevel());
  }





  @Test
  void testAddRandomWeapons() {
    worldBuilder.addSols().addWeapons();
    assertTrue(worldBuilder.build().getWeapons().size() >= 1);
  }
}
