package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

//Created By Dhruv
public class PostSpectatorCheckTurnRoute implements Route {

        private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
        private GameCenter gameCenter;
        private Gson gson;

        public PostSpectatorCheckTurnRoute(GameCenter gameCenter, Gson gson){
            LOG.config("PostSpectateSpectateCheckTurnRoute is initialized.");
            this.gameCenter = gameCenter;
            this.gson = gson;
        }

        @Override
        public Object handle(Request request, Response response) {


            LOG.finer("PostSpectateCheckTurnRoute is invoked.");
            final Session httpSession = request.session();

            Player player = httpSession.attribute("currentPlayer");
            Game currGame = gameCenter.getGame(request.queryParams("gameID"));

            String json;
            if (currGame.isWinner()){
                player.isSpectating();
                json = gson.toJson(Message.info("true"));
            }
            else {
                json = gson.toJson(Message.info("false"));
            }
            return json;
        }
}
