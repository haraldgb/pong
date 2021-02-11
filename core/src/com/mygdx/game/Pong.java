// Bytt til fra sprites til Textures? Lag klasse for paddles og ball, tror det er enklest...
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Pong extends ApplicationAdapter  {
	public final static int DESKTOP_START_WIDTH = 800;
	public final static int DESKTOP_START_HEIGHT = 400;
	public final static String TITLE = "PONG";

	public static int winScore = 2;

	OrthographicCamera camera;
	ExtendViewport viewport;
	BitmapFont font;
	SpriteBatch batch;
	TextureAtlas textures;
	Drawable[] gameParts;

	int p1Score;
	int p2Score;
	Score score;

	Paddle paddle1;
	Paddle paddle2;
	Ball ball;

	float scale;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		scale = Gdx.graphics.getHeight() / Pong.DESKTOP_START_HEIGHT;
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		font = new BitmapFont();
		batch = new SpriteBatch();
		textures = new TextureAtlas("pong_sprites.txt");

		score = Score.getInstance();
		score.addGame(this);
		p1Score = score.getP1Score();
		p2Score = score.getP2Score();
		paddle1 = new Paddle(batch, viewport, textures.createSprite("paddle"), 0, 0,0, false);
		paddle2 = new Paddle(batch, viewport, textures.createSprite("paddle"), 1, DESKTOP_START_WIDTH - 10*scale,0, true);
		ball =	new Ball(batch, viewport, textures.createSprite("ball"), DESKTOP_START_WIDTH/2 - 10*scale,DESKTOP_START_HEIGHT/2-40*scale, paddle1, paddle2);
		ball.setScoreKeeper(score);
		gameParts = new Drawable[]{paddle1, paddle2, ball};
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		update();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void dispose () {
		batch.dispose();
		textures.dispose();
	}

	public void update() {
		float delta = Gdx.graphics.getDeltaTime();
		for (Drawable part : gameParts) {
			part.update(delta);
			part.draw();
		}


		font.draw(batch, String.format("%d", p1Score), viewport.getWorldWidth() / 2 - 100*scale, viewport.getWorldHeight() - 20*scale);
		font.draw(batch, "|", viewport.getWorldWidth() / 2, viewport.getWorldHeight() - 20*scale);
		font.getData().setScale(3*scale);
		font.draw(batch, String.format("%d", p2Score), viewport.getWorldWidth() / 2 + 100*scale, viewport.getWorldHeight() - 20*scale);
	}

	public void endGame() {
		System.out.println("Game over.");
		Gdx.app.exit();
	}

	public void goal(int p1s, int p2s) {
		this.p1Score = p1s;
		this.p2Score = p2s;
		if (p1s >= winScore || p2s >= winScore) {
			this.endGame();
		}
	}
}
