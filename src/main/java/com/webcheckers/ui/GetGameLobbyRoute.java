package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameLobbyRoute {
    // File created by Beck Anderson, code by Marcus, code adjusted by Kelly

    private static final Logger LOG = Logger.getLogger(GetGameLobbyRoute.class.getName());
    private static final Message GAME_LOBBY_MSG = Message.info("You are being transferred into the game.");
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetGameLobbyRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameLobbyRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
//    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSignInRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game Time!");

        // display a user message in the Home page
        vm.put("message", GAME_LOBBY_MSG);

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
