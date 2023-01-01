package fr.uvsq.cprog.roguelike;
import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorldObjectTest {

  @Test
  void testGetX() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    int x = worldObject.getX();

    // Then
    assertEquals(5, x);
  }

  @Test
  void testSetX() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    worldObject.setX(7);
    int x = worldObject.getX();

    // Then
    assertEquals(7, x);
  }

  @Test
  void testGetY() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    int y = worldObject.getY();

    // Then
    assertEquals(3, y);
  }

  @Test
  void testSetY() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    worldObject.setY(6);
    int y = worldObject.getY();

    // Then
    assertEquals(6, y);
  }

  @Test
  void testGetAsciiChar() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    String asciiChar = worldObject.getAsciiChar();
    // When


    // Then
    assertEquals("X", asciiChar);
  }

  @Test
  void testSetAsciiChar() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    worldObject.setAsciiChar("A");
    String asciiChar = worldObject.getAsciiChar();

    // Then
    assertEquals("A", asciiChar);
  }

  @Test
  void testGetColor() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    Color color = worldObject.getColor();

    // Then
    assertEquals(Color.RED, color);
  }

  @Test
  void testSetColor() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    worldObject.setColor(Color.BLUE);
    Color color = worldObject.getColor();

    // Then
    assertEquals(Color.BLUE, color);
  }

  @Test
  void testToString() {
    // Given
    WorldObject worldObject = new WorldObject(5, 3, "X", Color.RED);

    // When
    String str = worldObject.toString();

    // Then
    assertEquals("X", str);
  }
}
