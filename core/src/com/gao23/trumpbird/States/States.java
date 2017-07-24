package com.gao23.trumpbird.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by GAO on 7/22/2017.
 */

public abstract class States {

        protected OrthographicCamera cam;
        protected Vector3 mouse;
        protected stateManager manager;

        protected States(stateManager manager){
            this.manager = manager;
            cam = new OrthographicCamera();
            mouse = new Vector3();
        }

        protected abstract void input();
        public abstract void update(float time);
        public abstract void render(SpriteBatch ab);
    // this is to disapose the background to prevent memory leak
        public abstract void dispose();






}
