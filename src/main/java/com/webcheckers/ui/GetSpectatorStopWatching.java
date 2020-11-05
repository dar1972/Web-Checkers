package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import static spark.Spark.halt;

//created by Dhruv
public class GetSpectatorStopWatching implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorStopWatching.class.getName());

    static final String TITLE = "Spectate Exit";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;


    public GetSpectatorStopWatching(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        LOG.config("GetSpectatorStopWatching is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("GetSpectateStopWatching is invoked.");
        Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);

        final Player player = httpSession.attribute("currentPlayer");

        player.isSpectating(false);
        vm.put("currentUser",player);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }

}
