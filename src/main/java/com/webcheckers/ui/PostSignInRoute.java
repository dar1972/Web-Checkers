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

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;


public class PostSignInRoute implements Route {
  private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

  private static final Message SIGNIN_INFO_MSG = Message.info("Please log in using an existing username or a new one.");

  static final String USER_PARAM = "userName";

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
   * requests.
   *
   * @param templateEngine the HTML template rendering engine
   */
  public PostSignInRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    //
    LOG.config("PostSignInRoute is initialized.");
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
    LOG.finer("PostSignInRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Sign In!");

    // display a user message in the Home page
    vm.put("message", SIGNIN_INFO_MSG);

    final String userName = request.queryParams(USER_PARAM);

    Player test = new Player(userName);

    //so pass username to playerlobby?

    playerLobby.addToLobby(test);

    // render the View
    return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
  }
}
