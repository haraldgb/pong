package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Pong extends ApplicationAdapter {
	public final static int DESKTOP_START_WIDTH = 800;
	public final static int DESKTOP_START_HEIGHT = 480;
	public final static String TITLE = "PONG";

	OrthographicCamera camera;
	ExtendViewport viewport;
	SpriteBatch batch;
	TextureAtlas textures;
	Sprite paddle1;
	Sprite paddle2;
	Sprite ball;

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(DESKTOP_START_WIDTH, DESKTOP_START_HEIGHT, camera);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		batch = new SpriteBatch();
		textures = new TextureAtlas("pong_sprites.txt");
		paddle1 = textures.createSprite("paddle");
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		paddle1.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textures.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}
}
