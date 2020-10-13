package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

// File created by Beck Anderson, code by Marcus, code adjusted by Kelly

public class PostGameLobbyRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    static final String USER_IN_ATTR = "currentUser??"; //used by nav-bar.ftl to decide if to display username or not.
    static final String USER_NAME_ATTR = "currentUser.name"; //use by nav-bar.ftl to display the username.

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostGameLobbyRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

        LOG.config("PostGameLobbyRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (true) { // if username is valid
            LOG.finer("GetSignInRoute is invoked.");
            //final Session session = request.session();
            //session.attribute( USER_PARAM, userName );
            response.redirect(WebServer.GAME_URL); // user gets transferred to game page
            halt();

            // used to put in title name, this varies depending on which page it is
            Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Game Time!");

            // display a user message in the Home page
            return templateEngine.render(new ModelAndView(vm, "game.ftl")); // created by Marcus, adjusted by Kelly
        }

        return null;
    }
}
