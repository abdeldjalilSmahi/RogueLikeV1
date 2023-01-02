package fr.uvsq.cprog.roguelike;

public class AttackCommand implements Command{
  private Player player;
  private World world;

  public AttackCommand(Player player, World world) {
    this.player = player;
    this.world = world;
  }

  @Override
  public void execute() {

  }
}
