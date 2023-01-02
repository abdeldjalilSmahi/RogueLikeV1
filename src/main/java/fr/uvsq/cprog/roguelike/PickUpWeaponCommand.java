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
  public boolean isWeaponNearby(Player player, World world) {
    int x = player.getX();
    int y = player.getY();

    // Vérifier les cases adjacentes
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int newX = x + i;
        int newY = y + j;
        if (newX < 0 || newX >= world.getHEIGHT() || newY < 0 || newY >= world.getWIDTH()) {
          // Case en dehors du monde, on passe
          continue;
        }
        if(i== 0 && j==0){
          continue;
          // c'est la case du joueur
        }
        WorldObject object = world.getObject(newX, newY);
        if (object instanceof Weapon) {
          // On a trouvé une arme !
          return true;
        }
      }
    }

    // Aucune arme trouvée
    return false;
  }
}
