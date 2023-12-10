package com.mygdx.game.Dialogue;

import java.util.List;

public class Link {
    private DialogueNode next;
    private DialogueNode previous;
    private List<Condition> conditions;

    public boolean isAvailable() {
        for (Condition condition : conditions) {
            if(!condition.evaluate())
                return false;
        }
        return true;
    }

    //Getters and Setters
    public DialogueNode getNext() {return next;}
    public DialogueNode getPrevious() {return previous;}
    public void setNext(DialogueNode next) {this.next = next;}
    public void setPrevious(DialogueNode previous) {this.previous = previous;}
    public List<Condition> getConditions(){return conditions;}
    public void setConditions(List<Condition> conditions) {this.conditions = conditions;}
}
