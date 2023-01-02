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
                case "O":
                    game1 = loadGame();
                    System.out.println("Game loaded successfuly");

                    Thread.sleep(1000);
                    Game.clearConsole();
                    break;
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
    private static Game loadGame(){

        Player player = null ;
        ArrayList<Monster> monsters = null ;
        ArrayList<Weapon> weapons = null ;
        ArrayList<WorldComponent> worldComponents = null ;
        Game game = null ;

        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("game.json"));
            String targetString = IOUtils.toString(reader);
            game= gson.fromJson(targetString, Game.class);

            // close reader
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        World world = game.getWorld();
        WorldObject[][] loadedWorld = new WorldObject[world.getHEIGHT()][world.getWIDTH()];
        player = world.getPlayer();
        monsters = world.getMonsters() ;
        weapons = world.getWeapons();
        worldComponents = world.getWorldComponents();
//        game.setWorld(world);
        loadedWorld[player.getX()][player.getY()] = player ;
        for(Monster monster : monsters){
            loadedWorld[monster.getX()][monster.getY()] = monster ;
        }
        for(WorldComponent worldComp : worldComponents){
            loadedWorld[worldComp.getX()][worldComp.getY()]= worldComp ;
        }
        for(Weapon weapon : weapons){
            loadedWorld[weapon.getX()][weapon.getY()]=weapon ;
        }
        world.setWorld(loadedWorld);
        game.setWorld(world);
        game.setPlayer(world.getPlayer());

        return game ;
    }

}
