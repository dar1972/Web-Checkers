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



public class GetSpectatorGame implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorGame.class.getName());

    static final String TITLE = "Game Spectate";
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    private final Gson gson;


    private enum ViewMode{
        PLAY,
        SPECTATOR,
        REPLAY}

    public GetSpectatorGame(final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson, final PlayerLobby playerLobby) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        LOG.config("GetSpectateGameRoute is initialized.");
    }


    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("GetSpectateGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);
        
        if (httpSession.attribute("userName") == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
        }
        Player currentUser = playerLobby.getPlayers().get(httpSession.attribute("userName"));

        currentUser.setSpectating(true);
        Game game = gameCenter.getArchivedGames().get(httpSession.attribute("viewedGameID"));

        vm.put("title", "Game Time!");
        vm.put("currentUser", currentUser);
        //vm.put(Game_ID, gameID);
        vm.put("redPlayer", game.getRed());
        vm.put("whitePlayer", game.getWhite());
        vm.put("activeColor", game.getActiveColor());
        vm.put("viewMode", ViewMode.SPECTATOR);
        vm.put("board", game.getRedSnapshots().get(game.getRedSnapshots().size()-1));

        int spectateIndex;
        if (httpSession.attribute("spectateIndex") == null) {
            spectateIndex = 0;
            httpSession.attribute("spectateIndex", 0);
        }
        else {
            spectateIndex = httpSession.attribute("spectateIndex");
        }

        
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}