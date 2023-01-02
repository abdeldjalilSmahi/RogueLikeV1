package fr.uvsq.cprog.roguelike;

import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickUpWeaponCommandTest {

  private World world;
  @BeforeEach
  public void setUp() {
    world = new WorldBuilder(1).addWalls().addSols().build();
  }
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
  @Test
  public void testIsWeaponNearby(){
    Player player = new Player(1, 1);
    world.setPlayer(player);
    world.setObject(new Weapon(1, 2, TypeWeapon.Axe));
    assertTrue(new PickUpWeaponCommand(player, world).isWeaponNearby(player, world));

  }
}
