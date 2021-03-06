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

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetSignInRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

  private static final Message SIGNIN_INFO_MSG = Message.info("Please log in using an existing username or a new one.");

  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
   * requests.
   *
   * @param templateEngine the HTML template rendering engine
   */
  public GetSignInRoute(final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    LOG.config("GetSignInRoute is initialized.");
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
    LOG.finer("GetSignInRoute is invoked.");

    // putting in value to variable
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Sign In!");

    // display a user message in the Home page
    vm.put("message", SIGNIN_INFO_MSG);

    // render the View
    return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
  } 
}
