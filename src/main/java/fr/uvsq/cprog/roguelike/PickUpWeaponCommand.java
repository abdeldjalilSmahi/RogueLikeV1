package fr.uvsq.cprog.roguelike;

public class PickUpWeaponCommand implements Command {

  private Player player;
  private World world;

  public PickUpWeaponCommand(Player player, World world) {
    this.player = player;
    this.world = world;
  }

  @Override
  public void execute() {

  }
}
