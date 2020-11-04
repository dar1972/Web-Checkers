package com.webcheckers.ui;

//Created by Dhruv
import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static com.webcheckers.model.Piece.Color.RED;
import static com.webcheckers.model.Piece.Color.WHITE;

public class GetSpectatorGame implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorGame.class.getName());
    private final TemplateEngine templateEngine;
    private Gson gson;
    private Game spectateGame;

    public GetSpectatorGame(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;

        LOG.config("Spectator Game is initialized");

        gson = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("Get spectator was invoked");
        //vm to represent the board, and current session of the user
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        //Checks the request method
        if (request.requestMethod().equals("GET")){
            LOG.finer("Spectator game was invoked");

            //Gets the spectator and removes them from the player lobby
            Player self = session.attribute("currentUser");
            self.spectating = true;

            //Puts attributes in the vm
            vm.put("title", "Welcome");
            vm.put("currentUser", self);
            //vm.put("viewMode", SPECTATOR);

            //Gets the two names of the players you are spectating

            //Gets the game id and game of the spectated game

            //Checks who's turn it is

            //Display for the board
            vm.put("board", null);
            if(spectateGame.isOver()){
                final Map<String, Object> modeOptions = new HashMap<>(2);

                Message message = Message.info("Game is over");

                modeOptions.put("gameOverMessage", message.getText());
                modeOptions.put("isGameOver", null); //spectate game is over

                vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
            }
        }
        //Returns the rendering for the board
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}