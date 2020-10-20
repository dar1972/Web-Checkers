package com.webcheckers.ui.SprintTwoRoutes;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.ui.PostSignInRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

//Made and worked on my Beck Anderson

public class PostCheckTurnRoute implements Route{

    public static final String GAME_WON = "You won the game :D Please head back to the home menu.";
    public static final String GAME_LOST = "You lost the game :( Please head back to the home menu.";

    static final String USER_PARAM = "userName";

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final Gson gson;
    //private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostCheckTurnRoute(GameCenter gameCenter,Gson gson) {
        Objects.requireNonNull( gameCenter );
        //this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostCheckTurnRoute is invoked.");

        String name = request.session().attribute(USER_PARAM);
        //String outcome = Boolean.toString(gameCenter.isPlayerActive(name));
        String winner = "gameCenter.gameWon(name);";
        Message message;

        if (winner.equals(name)) {
            message = Message.info(GAME_WON);
        } else {
            message = Message.info(GAME_LOST);
        }

        String json;
        json = gson.toJson(message);
        return json;
    }
}
