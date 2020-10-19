package com.webcheckers.ui.SprintTwoRoutes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.ui.WebServer;
import com.webcheckers.util.Message;

import spark.*;
import static spark.Spark.halt;

public class PostResignGameRoute implements Route {

    private Game gameID;
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
        gameCenter.resignGame(playerLobby.getPlayers().get(userName));

        if(gameCenter.getGameLobby().containsKey(userName)) {
            msg = Message.error("Resign failed.");
        }
        else {
            msg = Message.info("Resign success");
        }
        
        String json;
        json = gson.toJson(msg);

        return json;
          
    }
}
