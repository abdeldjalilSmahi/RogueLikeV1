package fr.uvsq.cprog.roguelike;

import org.junit.jupiter.api.Test;
import org.fusesource.jansi.Ansi.Color;
import static org.junit.jupiter.api.Assertions.*;

class WorldComponentTest {
  @Test
  void testConstructor() {
    // Given
    int x = 5;
    int y = 3;
    WorldComponentsType type = WorldComponentsType.OBSTACLE;
    Color color = Color.RED;

    // When
    WorldComponent worldComponent = new WorldComponent(x, y, type, color);

    // Then
    assertEquals(x, worldComponent.getX());
    assertEquals(y, worldComponent.getY());
    assertEquals(type, worldComponent.getType());
    assertEquals(color, worldComponent.getColor());
  }
}
