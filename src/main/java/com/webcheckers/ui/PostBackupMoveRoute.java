package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;

import spark.*;

public class PostBackupMoveRoute implements Route {

    private Game game;
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     */
    public PostBackupMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String userName = request.session().attribute("userName");
        game = gameCenter.getGame(userName);
        boolean success = game.backupMove();
        Message msg;
        if (success) {
            msg = Message.info("Backup success.");
        }
        else {
            msg = Message.error("Backup failed.");
        }
        String json;
        json = gson.toJson(msg);

        return json;
    }
}
