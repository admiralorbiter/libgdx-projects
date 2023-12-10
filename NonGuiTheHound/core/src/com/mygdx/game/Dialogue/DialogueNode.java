package com.mygdx.game.Dialogue;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
    private int id;
    private List<String> message;
    private List<Link> links;

    public List<Link> getAvailableLinks() {
        List<Link> availableLinks = new ArrayList<>();
        for(Link link : links) {
            if (link.isAvailable()) {
                availableLinks.add(link);
            }
        }
        return availableLinks;
    }


    //Getters and Setters
    public int getID(){return id;}
    public void setID(int id){this.id=id;}
    public List<String> getMessage(){return message;}
    public void setMessage(List<String> message){this.message=message;}
    public void setMessage(String message){this.message.add(message);}
    public List<Link> getLinks(){return links;}
    public void setLinks(List<Link> links){this.links=links;}
}
