package com.gao23.trumpbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.States.menuState;
import com.gao23.trumpbird.States.stateManager;

public class TrumpBirdMain extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Trump Bird";


	//custom manager for managing states
	private stateManager manager;

	// this is the image file to render
	private SpriteBatch batch;

	
	@Override
	public void create () {
		manager = new stateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		batch = new SpriteBatch();
		manager.push(new menuState(manager));

	}

	@Override
	public void render () {
		// this erase the current image
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// this gets the update delta time for manager to update
		manager.update(Gdx.graphics.getDeltaTime());
		// this has the manager render the file
		manager.render(batch);
	}
	

}
