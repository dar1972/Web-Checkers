package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.logging.Logger;

//Made and worked on by Beck Anderson

public class PostSubmitTurnRoute implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    static final String USER_PARAM = "userName";
    private static final String SWAP_TURN_ERROR = "Could not submit turn, please try again!";
    private static final String SUBMIT_TURN_INFO = "Turn submitted!";
    private static final String GAME_OVER_INFO = "The game is over. Please head back to home!";

    private final GameCenter gameCenter;
    private final Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP
     * requests.
     *
     */
    public PostSubmitTurnRoute(GameCenter gameCenter,Gson gson) {
        this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.config("PostSubmitTurnRoute is invoked.");

        Message message;
        String userName = request.session().attribute( USER_PARAM );

        if(gameCenter.isPlayerInGame(userName)){
            boolean outcome = gameCenter.finishTurn(userName);

            if(!outcome){
                message = Message.error("Fix something, this ain't right!");
            }
            else if(gameCenter.isPlayerActive(userName)){
                Game game = gameCenter.getGame(userName);
                message = Message.info(SUBMIT_TURN_INFO);

                ArrayList<BoardView> activeSnapshots = game.getActiveSnapshots();
                ArrayList<BoardView> inactiveSnapshots = game.getInactiveSnapshots();
                ArrayList<BoardView> tempSnapshots = game.getTempSnapshots();

                BoardView activeBoard = activeSnapshots.get(activeSnapshots.size()-1);


                for (int i = 0; i < tempSnapshots.size(); i++) { 
                    inactiveSnapshots.add(game.copyBoard(tempSnapshots.get(i)));
                }
        
                BoardView inactiveBoard = inactiveSnapshots.get(inactiveSnapshots.size()-1);

                if (game.getActiveColor() == Game.ActiveColor.RED){

                    game.setGameBoardRed(activeBoard);
                    game.setGameBoardWhite(inactiveBoard);

                }
                else {

                    game.setGameBoardRed(activeBoard);
                    game.setGameBoardWhite(inactiveBoard);

                }

                game.resetMoveAllowed();

                tempSnapshots.clear();
                if (game.colorCleared()) { //check if a color has been fully wiped from the board. 
                    game.setWinner(game.getActivePlayer()); 
                }
                game.updateActivePlayer();


            }
            else{
                message = Message.info(SWAP_TURN_ERROR);
            }
        }
        else {
            message = Message.info(GAME_OVER_INFO);
        }
        String json;
        json = gson.toJson(message);
        return json;
    }
}

