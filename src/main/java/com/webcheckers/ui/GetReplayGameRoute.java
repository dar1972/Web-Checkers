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



public class GetReplayGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayGameRoute.class.getName());

    static final String TITLE = "Game Spectate";
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    private enum ViewMode {
        PLAY, SPECTATOR, REPLAY
    }

    public GetReplayGameRoute(final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson,
            final PlayerLobby playerLobby) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        LOG.config("GetReplayGameRoute is initialized.");
    }


    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("GetReplayGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);
        
        if (httpSession.attribute("userName") == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
        }
        Player currentUser = playerLobby.getPlayers().get(httpSession.attribute("userName"));

        currentUser.setSpectating(true);
        Game game = gameCenter.getArchivedGames().get(httpSession.attribute("viewedGameID"));
        int replayIndex;
        if (httpSession.attribute("replayIndex") == null) {
            replayIndex = 0;
            httpSession.attribute("replayIndex", 0);
        }
        else {
            replayIndex = httpSession.attribute("replayIndex");
        }


        vm.put("title", "Game Time!");
        vm.put("currentUser", currentUser);
        //vm.put(Game_ID, gameID);
        vm.put("redPlayer", game.getRed());
        vm.put("whitePlayer", game.getWhite());
        vm.put("activeColor", game.getActiveColor());
        vm.put("viewMode", ViewMode.REPLAY);
        vm.put("board", game.getRedSnapshots().get(replayIndex));

        final Map<String, Object> modeOptions = new HashMap<>();


        if (replayIndex <= 0) {
            modeOptions.put("hasPrevious", false);
        }
        else {
            modeOptions.put("hasPrevious", true);
        }
        if (replayIndex >= game.getRedSnapshots().size()-1) {
            modeOptions.put("hasNext", false);
            
        }
        else {
            modeOptions.put("hasNext", true);

        }
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));  

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}