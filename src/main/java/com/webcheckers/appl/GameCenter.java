package com.webcheckers.appl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

public class GameCenter {
    // Made by Beck Anderson

    private final PlayerLobby playerLobby;
    private Map<String, Game> gameLobby;

    public GameCenter(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        gameLobby = new HashMap<>();

    }

    public synchronized void createGame(String redName, String whiteName) {

        Player red = playerLobby.getPlayers().get(redName);
        Player white = playerLobby.getPlayers().get(whiteName);

        Game game = new Game(red, white);

        gameLobby.put(redName, game);
        gameLobby.put(whiteName, game);

    }

    public Map<String, Game> getGameLobby() {
        return gameLobby;
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

    public boolean isRed(Player user) {
        String userName = user.getName();
        Game game = gameLobby.get(userName);
        if(game.getRed().equals(user)) {
            return true;
        }
        else {
            return false;
        }
    }



    
}
