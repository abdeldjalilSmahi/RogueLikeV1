package fr.uvsq.cprog.roguelike;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Classe représentant la commande de sauvegarde de la partie.
 */

public class SaveCommand implements Command {

  private Game game;

  /**
   * Constructeur de la classe SaveCommand.
   *
   * @param game Objet Game à sauvegarder
   */
  public SaveCommand(Game game) {

    this.game = game;
  }

  @Override
  public void execute() {
    try (Writer writer = new FileWriter("game.json")) {
      Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
      gson1.toJson(game, writer);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Game saved successfully");
    System.exit(0);
  }
}
