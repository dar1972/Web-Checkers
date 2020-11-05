package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

//Made and worked on my Beck Anderson

public class PostCheckTurnRoute implements Route{



    static final String USER_PARAM = "userName";

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostCheckTurnRoute(GameCenter gameCenter,Gson gson) {
        Objects.requireNonNull( gameCenter );
        this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostCheckTurnRoute is invoked.");
        String userName = request.session().attribute("userName");
        Message message;

        if (userName != null) {
            if (gameCenter.getGame(userName).getActivePlayer().getName() == userName) {
                message = Message.info("true");
            }
            else {
                message = Message.info("false");
    
            }

            if (gameCenter.getGame(userName).isWinner()){
                message = Message.info("true");
            }
        }
        else {
            message = Message.info("true"); //Not actually true, but triggers page refresh when this route is invoked after server restart.
        }


        
        String json;
        json = gson.toJson(message);
        return json;
    }
}
