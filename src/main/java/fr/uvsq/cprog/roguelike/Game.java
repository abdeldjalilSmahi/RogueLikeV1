package fr.uvsq.cprog.roguelike;


import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principale du jeu qui gère l'exécution de la partie.
 */
public class Game {

  private transient Player player;
  private World world;
  private int level;

  private boolean isFinished;


  /**
   * Constructeur de la classe Game qui crée un nouveau monde en fonction du niveau choisi.
   *
   * @param level le niveau choisi pour la partie
   */
  public Game(int level) {
    this.level = level;

    this.world = new WorldBuilder(level)
        .addSols()
        .addWalls()
        .addRandomWalls()
        .addMonsters()
        .addWeapons()
        .addPlayer()
        .addOutput()
        .build();
    this.player = world.getPlayer();
    this.isFinished = false;
  }

  /**
   * Récupère le joueur du jeu.
   *
   * @return le joueur du jeu
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Modifie le joueur du jeu.
   *
   * @param player le nouveau joueur du jeu
   */

  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Récupère le monde du jeu.
   *
   * @return le monde du jeu
   */

  public World getWorld() {
    return world;
  }

  /**
   * Modifie le monde du jeu.
   *
   * @param world le nouveau monde du jeu
   */
  public void setWorld(World world) {
    this.world = world;
  }

  /**
   * Récupère le niveau du jeu.
   *
   * @return le niveau du jeu
   */

  public int getLevel() {
    return level;
  }

  /**
   * Modifie le niveau du jeu.
   *
   * @param level le nouveau niveau du jeu
   */

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Indique si le jeu est fini ou non.
   *
   * @return vrai si le jeu est fini, faux sinon
   */
  public boolean isFinished() {
    return isFinished;
  }

  /**
   * Modifie l'état de fin du jeu.
   *
   * @param finished vrai si le jeu est fini, faux sinon
   */
  public void setFinished(boolean finished) {
    isFinished = finished;
  }

  /**
   * Méthode principale qui exécute la partie jusqu'à ce que le joueur meurt ou termine le niveau.
   */
  public void runGame() {

    Scanner scanner = new Scanner(System.in);

    while ((player.isAlive()) && !(this.isFinished())) {
      String input = scanner.nextLine();
      Game.clearConsole();
      Command command = null;
      int dx;
      int dy;
      switch (input) {
        case "z":
          dx = -1;
          dy = 0;
          command = new MoveCommand(player, dx, dy, world);
          break;

        case "s":
          dx = 1;
          dy = 0;
          command = new MoveCommand(player, dx, dy, world);
          break;

        case "d":
          dx = 0;
          dy = 1;
          command = new MoveCommand(player, dx, dy, world);
          break;

        case "q":
          dx = 0;
          dy = -1;
          command = new MoveCommand(player, dx, dy, world);
          break;

        case "e":
          command = new PickUpWeaponCommand(player, world);
          break;

        case "a":
          command = new AttackCommand(player, world);
          break;
        case "exit":
          System.out.println("vous etes au point de quitter la parite,"
              + " vous voulez sauvegareder la partie ? O/N");
          input = scanner.nextLine();
          switch (input) {
            case "O":
              command = new SaveCommand(this);
              break;
            case "N":
              System.exit(0);
              break;
            default:
              System.out.println("Commande erronée");
              break;
          }
          break;

        default:
          System.out.println("Commande erronée");
          displayGame();
          break;
      }

      if (command != null) {
        command.execute();
        moveMonsters();
        checkLevelCompleted();
        displayGame();

      }
      if (!player.isAlive()) {
        clearConsole();
        System.out.println("You have died. Game over.");
        System.out.println("Voulez-vous rejouer ? O/N");
        String input1 = scanner.nextLine();
        if (input1.equals("O")) {

          this.world = new WorldBuilder(level)
              .addSols()
              .addWalls()
              .addRandomWalls()
              .addMonsters()
              .addWeapons()
              .addPlayer()
              .addOutput()
              .build();
          this.player = world.getPlayer();
          this.isFinished = false;
          System.out.println("Restarting the level...");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          clearConsole();
          displayGame();
        } else {
          // Quitter le jeu
          break;
        }
      }
    }
  }

  /**
   * Déplace les monstres et fait attaquer les monstres situés à côté du joueur.
   */
  public void moveMonsters() {
    for (Monster monster : world.getMonsters()) {
      monster.move(world);
      if (((Math.abs(player.getX() - monster.getX()) == 1)
          && (player.getY() - monster.getY() == 0))
          || (((Math.abs(player.getY() - monster.getY()) == 1)
          && (player.getX() - monster.getX() == 0)))) {
        monster.attack(player);
      }
    }
  }

  /**
   * Vérifie si le joueur a complété le niveau en tuant tous les monstres
   * <p>
   * et en atteignant la sortie. Si c'est le cas,
   * <p>
   * démarre le prochain niveau ou termine le jeu si c'était le dernier niveau.
   */
  public void checkLevelCompleted() {
    boolean allMonstersDead = true;
    for (Monster monster : world.getMonsters()) {
      if (monster.isAlive()) {
        allMonstersDead = false;
        break;
      }
    }
    if (allMonstersDead && this.player.getX() == world.getHEIGHT() - 2
        && this.player.getY() == world.getWIDTH() - 2) {
      if (this.world.getLevel() == 3) {
        this.setFinished(true);
        System.out.println("Congratulations, you have won the game!");
        System.exit(0);
      } else {
        this.world.setLevel(this.world.getLevel() + 1);
        this.world = new WorldBuilder(this.world.getLevel()).addSols()
            .addWalls()
            .addRandomWalls()
            .addMonsters()
            .addWeapons()
            .addPlayer()
            .addOutput()
            .build();
        this.player = this.world.getPlayer();
        System.out.println("You have completed the level!");
        System.out.println("Starting next level...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  /**
   * Efface le contenu de la console.
   *
   * @throws IOException          Si une erreur d'entrée/sortie se produit
   *                              <p>
   *                              lors de l'exécution de la commande.
   * @throws InterruptedException Si l'attente pour la fin de l'exécution de la commande est interrompue.
   */
  public static void clearConsole() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Affiche l'état actuel du jeu.
   */
  public void displayGame() {
    world.display();
    System.out.println("Current level: " + world.getLevel());
    System.out.println("Health: " + player.getHealth());
    if (player.getWeapon() != null) {
      System.out.println("Current weapon: " + player.getWeapon().getType().getAsciiChar()
          + " Power: " + player.getWeapon().getDamage());
    }
    for (Monster monster : world.getMonsters()) {
      System.out.println("Monster health : " + monster.getHealth());
    }
    System.out.println("Enter a command ('z'/'q'/'s'/'d' to move, 'a' to attack"
        + ", 'e' to pick up weapon and 'exit' to exit the game):");
  }
}
