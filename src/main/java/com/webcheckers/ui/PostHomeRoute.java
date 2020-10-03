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

            LOG.finer("PostHomeRoute is invoked.");
            String userName = request.session().attribute( USER_PARAM );
            String opponentName = request.queryParams(OPPONENT_PARAM);

            gameCenter.createGame(userName, opponentName);

            
            response.redirect(WebServer.GAME_URL);
            halt();
            //
            Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Game Time!");

            // display a user message in the Home page
            return templateEngine.render(new ModelAndView(vm, "game.ftl")); // created by Marcus, adjusted by Kelly
        

    }
}
