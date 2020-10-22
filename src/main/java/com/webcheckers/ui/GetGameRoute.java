package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.boardComponents.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import static spark.Spark.halt;


public class GetGameRoute implements Route {
    // File created by Beck Anderson, code by Marcus, code adjusted by Kelly

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    static final String USER_PARAM = "userName";
    static final String Game_ID = "gameID";


    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    private Player playerRed;
    private Player playerWhite;
    private Gson gson;

    private enum ViewMode{
        PLAY,
        SPECTATOR,
        REPLAY}

    /*private enum ActiveColor {
        RED,
        WHITE
    }*/

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby, final GameCenter gameCenter, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("GetGameRoute is initialized.");
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

        LOG.finer("GetGameRoute is invoked.");

        // get username from players and put them in the player lobby
        String userName = request.session().attribute(USER_PARAM);
        //userName = request.queryParams(USER_PARAM);
        //String gameID = request.session().attribute(Game_ID);
        Player player = playerLobby.getPlayers().get(userName);

        if(!gameCenter.getGameLobby().containsKey(userName) || gameCenter.getGame(userName) == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
        }

        if (gameCenter.isRed(player)) {
            playerRed = player;
            playerWhite = gameCenter.getOpponent(player);
        }
        else {
            playerWhite = player;
            playerRed = gameCenter.getOpponent(player);
        }

        Game game = gameCenter.getGame(userName);

        // putting values into variables
        Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Game Time!");
            vm.put("currentUser", player);
            //vm.put(Game_ID, gameID);
            vm.put("redPlayer", playerRed);
            vm.put("whitePlayer", playerWhite);
            vm.put("activeColor", game.getActiveColor());

            if(player == playerRed) {
                ArrayList<BoardView> snapshotsRed = game.getRedSnapshots();
                vm.put("board", snapshotsRed.get(snapshotsRed.size()-1));
            }
            else {
                ArrayList<BoardView> snapshotsWhite = game.getWhiteSnapshots();
                vm.put("board", snapshotsWhite.get(snapshotsWhite.size()-1));
            }

            final Map<String, Object> modeOptions = new HashMap<>(2);

            String resignedPlayerName = game.getPlayerWhoResigned();
            if (resignedPlayerName != ""){
                modeOptions.put("isGameOver", true);

                if (resignedPlayerName != userName) {
                    modeOptions.put("gameOverMessage", resignedPlayerName + " resigned. You won!");
                }
                else {
                    modeOptions.put("gameOverMessage", "You resigned. You technically shouldn't see this message.");

                }

            }
            else if (game.getWinner() != null) {
                modeOptions.put("isGameOver", true);
                modeOptions.put("gameOverMessage", game.getWinner().toString() + " Won!");
            }
            else {
                modeOptions.put("isGameOver", false);
            }
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));  
            vm.put("viewMode", ViewMode.PLAY);


        // display a user message in the Home page
        vm.put("currentUser", player);

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}