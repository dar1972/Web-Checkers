package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

/**
 * The unit test suite for the {@link GetHomeRoute} component.
 *
 * @author <a href='mailto:mok8867@rit.edu'>Marcus Kapoor</a>
 */
@Tag("UI-tier")
public class GetHomeRouteTest {

  /**
   * The component-under-test (CuT).
   *
   * <p>
   * This is a stateless component so we only need one.
   * The {@link GameCenter} component is thoroughly tested so
   * we can use it safely as a "friendly" dependency.
   */
  private GetHomeRoute CuT;

  // friendly objects
  private GameCenter gameCenter;
  private PlayerLobby testLobby;

  // mock objects
  private Request request;
  private Session session;
  private TemplateEngine engine;
  private Response response;

  /**
   * Setup new mock objects for each test.
   */
  @BeforeEach
  public void setup() {
    request = mock(Request.class);
    session = mock(Session.class);
    when(request.session()).thenReturn(session);
    response = mock(Response.class);
    engine = mock(TemplateEngine.class);

    // create a unique CuT for each test
    // the GameCenter is friendly but the engine mock will need configuration
    testLobby = new PlayerLobby();
    gameCenter = new GameCenter(testLobby);
    CuT = new GetHomeRoute(engine, testLobby, gameCenter);
  }

  /**
   * Test that CuT shows the Home view when the session is brand new.
   */
  @Test
  public void new_session() {
    // To analyze what the Route created in the View-Model map you need
    // to be able to extract the argument to the TemplateEngine.render method.
    // Mock up the 'render' method by supplying a Mockito 'Answer' object
    // that captures the ModelAndView data passed to the template engine
    final TemplateEngineTester testHelper = new TemplateEngineTester();
    when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

    // Invoke the test
    CuT.handle(request, response);

    // Analyze the results:
    //   * model is a non-null Map
    testHelper.assertViewModelExists();
    testHelper.assertViewModelIsaMap();
    //   * model contains all necessary View-Model data
    testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
    //testHelper.assertViewModelAttribute(GetHomeRoute.GAME_STATS_MSG_ATTR, GameCenter.NO_GAMES_MESSAGE);
    //testHelper.assertViewModelAttribute(GetHomeRoute.NEW_PLAYER_ATTR, Boolean.TRUE);
    //   * test view name
    testHelper.assertViewName(GetHomeRoute.VIEW_NAME);
    //   * verify that a player service object and the session timeout watchdog are stored
    //   * in the session.
    //verify(session).attribute(eq(GetHomeRoute.USER_PARAM), any(PlayerLobby.class));
  }

  /**
   * Test that CuT redirects to the Game view when a @Linkplain(PlayerLobby) object exists.
   */
  @Test
  public void old_session() {
    // Arrange the test scenario: There is an existing session with a PlayerLobby object
    when(session.attribute(GetHomeRoute.USER_PARAM)).thenReturn(testLobby.getPlayers().get(GetHomeRoute.USER_PARAM));

    // Invoke the test
    try {
      CuT.handle(request, response);
      fail("Redirects invoke halt exceptions.");
    } catch (HaltException e) {
      // expected
    }

    // Analyze the results:
    //   * redirect to the Game view
    verify(response).redirect(WebServer.GAME_URL);
  }
}
