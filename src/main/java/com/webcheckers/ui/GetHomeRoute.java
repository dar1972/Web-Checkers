package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import static spark.Spark.halt;


import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import freemarker.core.Environment;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String USER_PARAM = "userName";


  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  private final GameCenter gameCenter;
  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby, final GameCenter gameCenter) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    this.playerLobby = playerLobby;
    this.gameCenter = gameCenter;
    LOG.config("GetHomeRoute is initialized.");
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
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    // set up HashMap to add string values to the variables
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);

    // get username from players and put them in the player lobby
    String userName = request.session().attribute(USER_PARAM);
    Player player = playerLobby.getPlayers().get(userName);

    // makes it so that player does not see their own name in a list of potential opponents
    HashMap<String, Player> processedHashMap = (HashMap<String, Player>) playerLobby.getPlayers().clone();
    processedHashMap.remove(userName);

      if (userName != null) {   // if username is valid
        vm.put("currentUser", player);
        vm.put("userList",processedHashMap);
      }
      else {
        vm.put("lobbySize", playerLobby.getPlayers().size());
      }

      // once entering opponents name, both players will be sent to the game if both are available
      if (gameCenter.getGameLobby().containsKey(userName)) {
        response.redirect("/game");
        halt();
      }

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
