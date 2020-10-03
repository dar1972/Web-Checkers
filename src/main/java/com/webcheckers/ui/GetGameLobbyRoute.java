package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.boardComponents.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameLobbyRoute implements Route{
    // File created by Beck Anderson, code by Marcus, code adjusted by Kelly

    private static final Logger LOG = Logger.getLogger(GetGameLobbyRoute.class.getName());
    private static final Message GAME_LOBBY_MSG = Message.info("You are being transferred into the game.");
    static final String USER_PARAM = "userName";

    private final String VIEW_TITLE = "Checkers Game";
    private final String VIEW_NAME = "game.ftl";
    private String viewMode, redirect;
    private static String PLAYER_ALREADY_IN_GAME = "The player you've selected is already in a game.";
    private static String PLAYER_DOES_NOT_EXIST = "The player by that name does not exist";
    private static String PLAYER_INVALID = "You cannot play a game with yourself";
    private static String PLAYER_RESIGN = "The other player is not in the game";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    private Player playerRed;
    private Player playerWhite;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetGameLobbyRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby, final GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
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
        String userName = request.session().attribute(USER_PARAM);
        Player player = playerLobby.getPlayers().get(userName);
        playerRed = new Player("red");
        playerWhite = new Player("White");
        BoardView board = new BoardView();


        Map<String, Object> vm = new HashMap<>();
        if(true) { // Safety net
            vm.put("title", "Game Time!");
            vm.put("currentUser", player);
            vm.put("redPlayer", playerRed);
            vm.put("whitePlayer", playerWhite);
            vm.put("activeColor", 0);
            vm.put("board", board);
            vm.put("gameID", 123);
            // vm.put("modeOptionsAsJSON", gson.toJson(modeOptions))
            vm.put("viewMode", 0);
        }

        // display a user message in the Home page
        vm.put("message", GAME_LOBBY_MSG);

        vm.put("currentUser", player);

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}