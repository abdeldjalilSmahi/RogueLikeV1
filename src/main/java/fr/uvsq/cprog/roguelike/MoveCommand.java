package fr.uvsq.cprog.roguelike;

public class MoveCommand implements Command {

  private Player player;
  private int dx;
  private int dy;
  private World world;

  public MoveCommand(Player player, int dx, int dy, World world) {
    this.player = player;
    this.dx = dx;
    this.dy = dy;
    this.world = world;
  }

  @Override
  public void execute() {

  }
}
