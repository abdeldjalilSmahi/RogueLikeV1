package fr.uvsq.cprog.roguelike;

/**
 * Classe représentant la commande de déplacement d'un joueur.
 */
public class MoveCommand implements Command {

  /**
   * Le joueur qui doit se déplacer.
   */
  private Player player;
  /**
   * Déplacement sur l'axe x.
   */
  private int dx;
  /**
   * Déplacement sur l'axe y.
   */
  private int dy;
  /**
   * Le monde dans lequel se déplace le joueur.
   */
  private World world;

  /**
   * Constructeur de la commande de déplacement.
   *
   * @param player Le joueur qui doit se déplacer
   * @param dx     Déplacement sur l'axe x
   * @param dy     Déplacement sur l'axe y
   * @param world  Le monde dans lequel se déplace le joueur
   */


  public MoveCommand(Player player, int dx, int dy, World world) {
    this.player = player;
    this.dx = dx;
    this.dy = dy;
    this.world = world;
  }

  @Override
  public void execute() {
    player.move(dx, dy, world);
  }
}
