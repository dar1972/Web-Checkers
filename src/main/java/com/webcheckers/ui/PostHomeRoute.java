package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

// File created by Beck Anderson, code by Marcus, code adjusted by Kelly

public class PostHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    static final String USER_IN_ATTR = "currentUser??"; //used by nav-bar.ftl to decide if to display username or not.
    static final String USER_NAME_ATTR = "currentUser.name"; //use by nav-bar.ftl to display the username.
    static final String USER_PARAM = "userName";
    static final String OPPONENT_PARAM = "opponentName";
    static final String USER_BUSY = "userBusy";

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostHomeRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        LOG.config("PostHomeRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
            // process after giving username
            LOG.finer("PostHomeRoute is invoked.");
            String userName = request.session().attribute( USER_PARAM );
            String opponentName = request.queryParams(OPPONENT_PARAM);

            if (!gameCenter.getGameLobby().containsKey(opponentName)) { // if the person is not in the game lobby, create a game.
                gameCenter.createGame(userName, opponentName);
                response.redirect(WebServer.GAME_URL);
                halt();
            }
            // used to put in title name, this varies depending on which page it is
            Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Welcome!");
            vm.put("message", Message.error("User is already in game! Try a different one."));

            request.session().attribute( USER_BUSY, "yes" );
            response.redirect(WebServer.HOME_URL); 
            halt();

            return templateEngine.render(new ModelAndView(vm, "home.ftl")); // created by Marcus, adjusted by Kelly
        

    }
}
