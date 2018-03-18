package com.sangngh.rpg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sangngh.rpg.MyGdxGame;
import com.sangngh.rpg.entities.Player;

public class CityScreen implements Screen {

	private static final int TILE_SIZE = 16;

	private static final String WORLD_MAP_TMX = "data/screens/lake_haven.tmx";
	private static final String SPRITES = "data/sprites/toen_medival_tileset.png";

	private MyGdxGame game;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private AssetManager assetManager;
	private Player player;

	public CityScreen(MyGdxGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 480);
		camera.zoom = .5f;
		camera.update();

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new ClasspathFileHandleResolver()));
		assetManager.load(WORLD_MAP_TMX, TiledMap.class);
		assetManager.load(SPRITES, Texture.class);
		assetManager.finishLoading();

		TiledMap map = getTiledMap();
		renderer = new OrthogonalTiledMapRenderer(map);

		player = new Player(getSpriteTextures(), map.getLayers(), 0, 256);
		player.setPosition(23 * TILE_SIZE, 6 * TILE_SIZE);
	}

	@Override
	public void show() {
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

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	private TiledMap getTiledMap() {
		return assetManager.get(WORLD_MAP_TMX);
	}

	private Texture getSpriteTextures() {
		return (Texture) assetManager.get(SPRITES);
	}
}
