package fr.uvsq.cprog.roguelike;

/**
 * Classe implémentant la commande d'attaque.
 */
public class AttackCommand implements Command {

  private Player player;
  private World world;

  /**
   * Constructeur de la classe.
   *
   * @param player le joueur
   * @param world  le monde
   */
  public AttackCommand(Player player, World world) {
    this.player = player;
    this.world = world;
  }

  @Override
  public void execute() {
    if (isMonsterNearby()) {
      Monster monster = findNearbyMonster(player, world);
      if (monster != null) {
        player.attack(monster, world);
      }

    } else {
      System.out.println("Y a pas de monstre à l'entours ! ");
    }
  }


  /**
   * Méthode permettant de savoir si un monstre est proche du joueur.
   *
   * @return true si un monstre est proche, false sinon
   */
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

  /**
   * Trouve un monstre adjacent au joueur dans le monde.
   *
   * @param player Le joueur à qui chercher un monstre adjacent
   * @param world  Le monde dans lequel chercher un monstre adjacent au joueur
   * @return Le premier monstre adjacent au joueur, ou null s'il n'y en a pas
   */
  public Monster findNearbyMonster(Player player, World world) {
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
        if (i == 0 && j == 0) {
          continue;
        }
        WorldObject object = world.getObject(newX, newY);
        if (object instanceof Monster) {
          // On a trouvé un monstre !
          return (Monster) object;
        }
      }
    }

    // Aucun monstre trouvé
    return null;
  }
}
