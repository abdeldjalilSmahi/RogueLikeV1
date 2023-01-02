package fr.uvsq.cprog.roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveCommandTest {

  private World world;

  @BeforeEach
  public void setUp() {
    world = new WorldBuilder(1).addWalls().addSols().build();
  }

  @Test
  public void testMoveThrowsIllegalArgumentException() {
    Player player = new Player(1, 1);
    world.setPlayer(player);
    world.setObject(new WorldComponent(1, 2, WorldComponentsType.OBSTACLE, Color.RED));
    assertThrows(IllegalArgumentException.class, () -> new MoveCommand(player, 0, 1, world).execute());

  }

  @Test
  public void testMoveThrowsIndexOutOfException() {
    Player player = new Player(1, 1);
    world.setPlayer(player);
    world.setObject(new WorldComponent(1, 2, WorldComponentsType.OBSTACLE, Color.RED));
    assertThrows(IndexOutOfBoundsException.class, () -> new MoveCommand(player, 0, -2, world).execute());

  }

  @Test
  public void playerMoves() {
    Player player = new Player(1, 1);
    world.setPlayer(player);
    world.setObject(new WorldComponent(1, 2, WorldComponentsType.OBSTACLE, Color.RED));
    new MoveCommand(player, 1, 0, world).execute();
    assertEquals(2, player.getX() );
  }

}
