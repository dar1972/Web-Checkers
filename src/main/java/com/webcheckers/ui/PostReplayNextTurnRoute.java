package com.webcheckers.ui;

//Created by Dhruv
import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import static spark.Spark.halt;



public class PostReplayNextTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostReplayNextTurnRoute.class.getName());

    static final String TITLE = "Game Spectate";
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    private enum ViewMode {
        PLAY, SPECTATOR, REPLAY
    }

    public PostReplayNextTurnRoute(final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson,
            final PlayerLobby playerLobby) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        LOG.config("PostReplayNextTurnRoute is initialized.");
    }


    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("PostReplayNextTurnRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);
        
        int replayIndex = httpSession.attribute("replayIndex");

        replayIndex++;

        httpSession.attribute("replayIndex", replayIndex);
        Message message;
        if (replayIndex == (int) httpSession.attribute("replayIndex")) {
            message = Message.info("true");
        }
        else {
            message = Message.info("false");
        }


        String json;
        json = gson.toJson(message);
        return json;
    }
}