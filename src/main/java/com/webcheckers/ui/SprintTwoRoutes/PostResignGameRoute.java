package com.webcheckers.ui.SprintTwoRoutes;

import com.webcheckers.model.Game;
import spark.*;

public class PostResignGameRoute implements Route {

    private Game gameID;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostResignGameRoute() {
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
