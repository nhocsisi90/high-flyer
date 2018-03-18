package com.relic.highflyer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public abstract class AbstractLevel extends ScreenAdapter {

	private static final int TILE_SIZE = 32;

	private static final String SPRITES = "data/sprites/spaceShooter2_spritesheet.png";

	private String map;
	private GameEngine game;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private AssetManager assetManager;
	private Player player;

	public AbstractLevel(GameEngine game) {
		this.game = game;
		init();
	}

	protected abstract String getLevelMap();

	protected void init() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.getSettings().getWindowWidth(), game.getSettings().getWindowHeight());
		camera.update();

		final String tiledMapPath = "data/screens/" + getLevelMap();

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new ClasspathFileHandleResolver()));
		assetManager.load(tiledMapPath, TiledMap.class);
		assetManager.load(SPRITES, Texture.class);
		assetManager.finishLoading();

		TiledMap map = assetManager.get(tiledMapPath);
		renderer = new OrthogonalTiledMapRenderer(map);

		player = new Player(getSpriteTextures(), map.getLayers(), 480, 1045);
		player.setPosition(0 * TILE_SIZE, 6 * TILE_SIZE);
		player.rotate(90);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// let the camera follow the player
		camera.position.x = player.getX();
		camera.position.y = player.getY();
		camera.update();
		renderer.setView(camera);
		renderer.render();

		game.getBatch().setProjectionMatrix(camera.combined);
		game.getBatch().begin();
		player.draw(game.getBatch());
		game.getBatch().end();
	}

	private Texture getSpriteTextures() {
		return (Texture) assetManager.get(SPRITES);
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
}
