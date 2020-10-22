package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import spark.*;

public class PostResignGameRoute implements Route {

    //private Game gameID;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostResignGameRoute(GameCenter gameCenter, PlayerLobby playerLobby, Gson gson) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        
        String userName = request.session().attribute("userName");
        Message msg;
        String json;

        if (gameCenter.getGame(userName).getPlayerWhoResigned() != "") {
            msg = Message.info("Opponent already resigned!");
            json = gson.toJson(msg);
            return json;
        }
        gameCenter.getGame(userName).setPlayerWhoResigned(userName);
        Player opponent = gameCenter.getOpponent(playerLobby.getPlayers().get(userName));
        gameCenter.leaveGame(playerLobby.getPlayers().get(userName));

        if(gameCenter.getGame(opponent.getName()).getPlayerWhoResigned() == "") {
            msg = Message.error("Resign failed.");
        }
        else {
            msg = Message.info("Resign success");
        }
        
        json = gson.toJson(msg);

        return json;
          
    }
}
