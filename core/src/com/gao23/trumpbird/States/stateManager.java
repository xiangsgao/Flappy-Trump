package com.gao23.trumpbird.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by GAO on 7/22/2017.
 */

public class stateManager {
    private Stack<States> states;

    public stateManager(){
        states = new Stack<States>();
    }

    public void push(States state){
        this.states.push(state);
    }

    public void pop(States state){
        this.states.pop().dispose();
    }

    public void set (States state){
        this.pop(state);
        this.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch){
          this.states.peek().render(batch);
    }



}
