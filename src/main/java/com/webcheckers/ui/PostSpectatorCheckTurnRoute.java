package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

//Created By Dhruv
public class PostSpectatorCheckTurnRoute {
    private TemplateEngine templateEngine;
    private static final Logger LOG=Logger.getLogger(PostCheckTurnRoute.class.getName());
    private Gson gson;
    private boolean gameOver = true;
    private Game spectateGame;

    public PostSpectatorCheckTurnRoute(TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("SpectateCheckTurnRoute is initialized");
        gson = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("SpectateCheckTurnRoute has been invoked");
        Message message;
        Session session = request.session();

        String gameID = session.attribute("spectateGameID");

        Player self = session.attribute("currentUser");

        Game game = new Game(gameID);
        if (session.attribute("Turn submitted!") != null) {
            session.removeAttribute("Turn submitted!");
            message = Message.info("false");
            //update Board Here
        }
        else if(BoardView.BoardUpdate) {
            message = Message.info("true");
            //update Board Here
        }
        else if (game != null ){
            message = Message.info("true");
            gameIsOver = false;
        }
        else
            message = Message.info("false");
        return gson.toJson(message);
}

