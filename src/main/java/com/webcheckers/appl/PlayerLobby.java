package com.webcheckers.appl;

import java.util.HashMap;

import com.webcheckers.model.Player;

public class PlayerLobby {
    //created by Marcus
    //this should handle sign in requests?

    HashMap<String, Player> players = new HashMap<>();

    public PlayerLobby() {
        //do I even need this constructor?
    }

	public synchronized boolean addToLobby(Player user) {
        if (players.containsKey(user.getName())) { //I would have used hashcodes, but freemarker doesn't like iterating over non-string keys.
            return false;
        }
        else {
            players.put(user.getName(), user);
            return true;
        }
    }

    public synchronized boolean removeFromLobby(Player user) {
        if (players.containsKey(user.getName())) { //I would have used hashcodes, but freemarker doesn't like iterating over non-string keys.
            players.remove(user.getName());
            return true;
        }
        else {
            return false;
        }
    }
    
    public synchronized boolean isValid(String userName) {
        //do checks here
        //System.out.println(userName + "OVERHERE SDGSDGSDGSDGSDFGSDFGSDG");
        boolean isAlpha = !userName.matches("^.*[^a-zA-Z0-9].*$");

        if (userName.equals("")) {
            //System.out.println("OVERHERE AGAIN");
            isAlpha = false;
        }

        return isAlpha;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }
    
}
