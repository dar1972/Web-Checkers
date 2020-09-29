package com.webcheckers.appl;

import java.util.HashMap;

import com.webcheckers.model.Player;

public class PlayerLobby {
    //created by Marcus
    //this should handle sign in requests?

    HashMap<Integer, Player> players;

    public PlayerLobby() {

        //do I even need this constructor?
    }

	public boolean addToLobby(Player userName) {
        if (players.containsKey(userName.hashCode())) {
            //fuck em up fam
            return false;
        }
        else {
            players.put(userName.hashCode(), userName);
            return true;
        }
	}


    
}
