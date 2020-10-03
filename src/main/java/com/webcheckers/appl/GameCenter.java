package com.webcheckers.appl;

import java.util.HashMap;
import java.util.Map;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

public class GameCenter {
    //Made by Beck Anderson

    private final PlayerLobby playerLobby;
    private Map<String,Game> gameLobby;

    public GameCenter( PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        gameLobby = new HashMap<>();

    }

    public synchronized void createGame (String redName, String whiteName) {

        Player red = playerLobby.getPlayers().get(redName);
        Player white = playerLobby.getPlayers().get(whiteName);

        Game game = new Game(red, white);

        gameLobby.put(redName, game);
        gameLobby.put(whiteName, game);

    }

    
}
