package com.gao23.trumpbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.States.menuState;
import com.gao23.trumpbird.States.stateManager;

public class TrumpBirdMain extends ApplicationAdapter {
	public static final int WIDTH = 720;
	public static final int HEIGHT = 1280;
	public static final String TITLE = "Flappy Trump";


	//custom manager for managing states
	private stateManager manager;






	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		manager = new stateManager(new SpriteBatch());
		manager.push(new menuState(manager));

	}

	@Override
	public void render () {
		// this erase the current image
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// this gets the update delta time for manager to update
		manager.update(Gdx.graphics.getDeltaTime());
		// this has the manager render the file
		manager.render();
	}
	

}
