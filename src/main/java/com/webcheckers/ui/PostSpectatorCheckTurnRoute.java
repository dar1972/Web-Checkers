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

            String userName = httpSession.attribute("userName");
            Game game = gameCenter.getArchivedGames().get(httpSession.attribute("viewedGameID"));

            Message message;
            if (userName != null) {
                int spectateIndex;
                if (httpSession.attribute("spectateIndex") == null) {
                    spectateIndex = 0;
                    httpSession.attribute("spectateIndex", 0);
                }
                else {
                    spectateIndex = httpSession.attribute("spectateIndex");
                }

                if (game.getInactiveSnapshots().size()-1 > spectateIndex) {
                    message = Message.info("true");
                    spectateIndex = game.getInactiveSnapshots().size()-1;
                    httpSession.attribute("spectateIndex", spectateIndex);

                }
                else {
                    message = Message.info("false");
                }
            }
            else {
                message = Message.info("true"); //not actually, but triggers page refresh to exit. 
            }


            String json;
            json = gson.toJson(message);
            return json;
        }
    }
