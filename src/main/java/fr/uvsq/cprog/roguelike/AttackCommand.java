package fr.uvsq.cprog.roguelike;

public class AttackCommand implements Command {

  private Player player;
  private World world;

  public AttackCommand(Player player, World world) {
    this.player = player;
    this.world = world;
  }

  @Override
  public void execute() {

  }

  public boolean isMonsterNearby() {
    int x = player.getX();
    int y = player.getY();
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i == x && j == y) {
          continue;
        }
        WorldObject object = world.getObject(i, j);
        if (object instanceof Monster) {
          return true;
        }
      }
    }
    return false;
  }
}
