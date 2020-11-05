package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import static spark.Spark.halt;

//created by Dhruv
public class GetReplayStopWatching implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayStopWatching.class.getName());

    static final String TITLE = "Replay Exit";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;

    public GetReplayStopWatching(GameCenter gameCenter, TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        LOG.config("GetReplayStopWatching is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("GetReplayStopWatching is invoked.");
        Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);

        String userName = httpSession.attribute("userName");
        if (userName != null) {
            Player user = playerLobby.getPlayers().get(userName);
            user.setSpectating(false);
            vm.put("currentUser", user);
        }

        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }

}
