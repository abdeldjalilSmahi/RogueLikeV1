package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PickUpWeaponCommandTest {
  @Test
  public void testFindNearbyWeapon() {
    // Créer un monde avec un joueur et une arme
    World world = new World(1);
    Player player = new Player(1, 1);
    Weapon weapon = new Weapon(1, 2, TypeWeapon.Axe);
    world.setObject(player);
    world.setObject(weapon);

    PickUpWeaponCommand command = new PickUpWeaponCommand(player, world);

    // Vérifier que findNearbyWeapon renvoie l'arme
    assertEquals(weapon, command.findNearbyWeapon(player, world));

    // Déplacer le joueur et vérifier que findNearbyWeapon renvoie null
    player.setX(10);
    assertNull(command.findNearbyWeapon(player, world));
  }
}
