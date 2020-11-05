package com.webcheckers.appl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

public class GameCenter {
    // Made by Beck Anderson

    private final PlayerLobby playerLobby;
    private HashMap<String, Game> gameLobby;
    private HashMap<Integer, Game> gameArchive;
    //private static Map<Player, Integer> gameIDMap;

    public GameCenter(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        gameLobby = new HashMap<>();
        gameArchive = new HashMap<>();
    }

    public synchronized void createGame(String redName, String whiteName) {

        Player red = playerLobby.getPlayers().get(redName);
        Player white = playerLobby.getPlayers().get(whiteName);

        Game game = new Game(red, white);

        gameLobby.put(redName, game);
        gameLobby.put(whiteName, game);
        gameArchive.put(game.getGameId(), game);

    }

    public Map<String, Game> getGameLobby() {
        return gameLobby;
    }

    public Game getGame(String playerName) {
        return gameLobby.get(playerName);
    }

    public Player getOpponent(Player user) {
        String userName = user.getName();
        Game game = gameLobby.get(userName);

        if (user == game.getRed()) {
            return game.getWhite();
        }
        else {
            return game.getRed();
        }
    }

    public synchronized boolean isRed(Player user) {
        String userName = user.getName();
        Game game = gameLobby.get(userName);
        if(game.getRed().equals(user)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPlayerActive(String name){
        Game game = getGame(name);
        if(name!=null && game!=null){
            return name.equals(game.getActivePlayer().getName());
        }return false;
    }

    public synchronized boolean isPlayerInGame(String name){
        return gameLobby.containsKey(name);
    }

    public boolean finishTurn(String name){
        Game game = getGame(name);
        boolean exists = false;
        if(game!=null){
            //get real thing in a bit
            exists = true;
        }
        return exists;
    }

    public synchronized void leaveGame(Player user) {
        getGame(user.getName()).setWinner(getOpponent(user));
        gameLobby.remove(user.getName());
        
    }



    public ArrayList<Move> getMoves(Game game){
        return game.getMoves();
    }

    public void storeMove(Move move, Game game){
        game.storeMove(move);
    }

	public HashMap<Integer, Game> getArchivedGames() {
		return gameArchive;
	}

}
