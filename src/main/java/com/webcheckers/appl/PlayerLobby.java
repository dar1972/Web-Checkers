package com.webcheckers.appl;

import java.util.HashMap;

import com.webcheckers.model.Player;

public class PlayerLobby {
    //created by Marcus
    //this should handle sign in requests?

    HashMap<Integer, Player> players = new HashMap<>();

    public PlayerLobby() {

        //do I even need this constructor?
    }

	public boolean addToLobby(Player user) {
        if (players.containsKey(user.hashCode())) {
            
            return false;
        }

        else {
            players.put(user.hashCode(), user);
            return true;
        }
    }
    
    public boolean isValid(String userName) {
        //do checks here
        boolean hasNonAlpha = !userName.matches("^.*[^a-zA-Z0-9 ].*$");

        return hasNonAlpha;
    }


    
}
