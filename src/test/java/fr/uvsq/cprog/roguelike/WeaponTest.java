package fr.uvsq.cprog.roguelike;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

  @Test
  void testConstructor() {
    // Given
    int x = 5;
    int y = 3;
    TypeWeapon type = TypeWeapon.Gun;

    // When
    Weapon weapon = new Weapon(x, y, type);

    // Then
    assertEquals(x, weapon.getX());
    assertEquals(y, weapon.getY());
    assertEquals(type, weapon.getType());
    assertEquals(type.getAsciiChar(), weapon.getAsciiChar());
    assertEquals(type.getDamage(), weapon.getDamage());
  }

  @Test
  void testSetType() {
    // Given
    int x = 5;
    int y = 3;
    TypeWeapon type = TypeWeapon.Gun;
    Weapon weapon = new Weapon(x, y, type);

    // When
    TypeWeapon newType = TypeWeapon.Gun;
    weapon.setType(newType);

    // Then
    assertEquals(newType, weapon.getType());
    assertEquals(newType.getAsciiChar(), weapon.getAsciiChar());
    assertEquals(newType.getDamage(), weapon.getDamage());
  }
}
