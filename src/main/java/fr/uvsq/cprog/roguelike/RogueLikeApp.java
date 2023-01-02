package fr.uvsq.cprog.roguelike;


import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.core.util.IOUtils;

public class RogueLikeApp {
    public static void main(String[] args) throws InterruptedException {


        Scanner scanner = new Scanner(System.in);
        Game game1 = null ;
        File savedGameFile = new File("game.json");
        if (savedGameFile.exists()) {
            System.out.println("Il existe une partie déja sauvegardé, tu veux la continuer ? O/N");
            String response = scanner.nextLine();
            switch (response){

                case "N":
                    game1 = new Game(1);
                    System.out.println("Une nouvelle partie vas démarrer");

                    Thread.sleep(1000);
                    Game.clearConsole();
                    break;

            }
        }
        else{
            System.out.println("Une nouvelle partie va démarrer");
            game1 = new Game(1);
            Thread.sleep(1000);
            Game.clearConsole();

        }
        game1.displayGame();

        while (game1.getWorld().getLevel() <= 3) {
            try{
                game1.runGame();
                game1.checkLevelCompleted();
            }
            catch(Exception e){
                game1.displayGame();
                System.out.println(e.getMessage());
            }
        }
    }

}
