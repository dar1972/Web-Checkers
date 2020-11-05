package com.webcheckers.ui;

//Created by Dhruv
import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetSpectatorGame implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorGame.class.getName());

    static final String TITLE = "Game Spectate";
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;



    public GetSpectatorGame(final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = gameCenter;
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

        if(httpSession.attribute("currentUser") != null){
            final Player player = httpSession.attribute("currentUser");
            final String gameID = request.queryParams("gameId");
            vm.put("currentUser", player);
            vm.put("viewMode", mode.isSpectator); // set mode as spectator
            Game game = gameCenter.getGame(gameID);

            vm.put(game.getActivePlayer().toString(),game.getActiveColor());
            vm.put("boardView", game);
            vm.put(Game.ActiveColor.RED.toString(),"redPlayer");
            vm.put(Game.ActiveColor.WHITE.toString(), "whitePlayer");
            if(game.isWinner()){
                httpSession.attribute("Message", Message.info("Game is over"));
                response.redirect(WebServer.HOME_URL);
                return null;
            }
        }
        else{
            final String gameID = request.queryParams("gameID");
            gameCenter.getGame(player.getName()); // player playing the game
            response.redirect(WebServer.HOME_URL);
            return null;
        }
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}