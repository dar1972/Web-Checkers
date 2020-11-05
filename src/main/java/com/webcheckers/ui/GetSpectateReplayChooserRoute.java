package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import static spark.Spark.halt;

//created by Dhruv
public class GetSpectateReplayChooserRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectateReplayChooserRoute.class.getName());

    static final String TITLE = "Redirecting...";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public GetSpectateReplayChooserRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        LOG.config("GetSpectateReplayChooser is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectateReplayChooser is invoked.");
        int gameID= Integer.parseInt(request.queryParams("viewedGameID"));
        request.session().attribute( "viewedGameID", gameID );

        request.session().attribute("spectateIndex", null);
        request.session().attribute("replayIndex", null);

        Game game = gameCenter.getGame(gameID);
        if (game.getIsOver()) {
            response.redirect("/replay/game");
        }
        else {
            response.redirect("/spectator/game");
        }

        halt();

        return null;
    }

}
