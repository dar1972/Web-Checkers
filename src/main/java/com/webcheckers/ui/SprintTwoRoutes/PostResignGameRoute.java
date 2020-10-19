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

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostResignGameRoute(GameCenter gameCenter, PlayerLobby playerLobby) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        
        String userName = request.session().attribute("userName");

        gameCenter.resignGame(playerLobby.getPlayers().get(userName));
        
        String json;
        Gson gson = new GsonBuilder().create();
        json = gson.toJson( Message.info("blablablatest") );

        return json;
          
    }
}
