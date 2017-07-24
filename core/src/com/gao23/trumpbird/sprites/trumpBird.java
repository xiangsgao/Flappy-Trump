package com.gao23.trumpbird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by GAO on 7/24/2017.
 */

public class trumpBird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture trumpB;


    public trumpBird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        trumpB = new Texture("Trump.png");
    }

    public Vector3 getPostion() {
        return position;
    }

    public Texture getTrumpB() {
        return trumpB;
    }

    public void update(float dt){
        this.velocity.add(0, GRAVITY, 0);
        this.velocity.scl(dt);
        this.position.add(0, this.velocity.y, 0);
        // this resets the velocity value back to the original
        this.velocity.scl(1/dt);
    }

    public void jump(){
        velocity.y = 250;
    }


}
