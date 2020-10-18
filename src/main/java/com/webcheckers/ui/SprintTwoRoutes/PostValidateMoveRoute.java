package com.webcheckers.ui.SprintTwoRoutes;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import spark.*;

import static spark.Spark.halt;

// File created by Beck Anderson

public class PostValidateMoveRoute implements Route {

    private Game gameID;
    private Move actionData;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostValidateMoveRoute() {
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}