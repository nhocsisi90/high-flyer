package com.relic.highflyer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.GameState;
import com.relic.highflyer.sprites.Player;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public abstract class AbstractLevel extends ScreenAdapter {

	private static final int TILE_SIZE = 32;

	private static final String SPRITES = "data/sprites/spaceShooter2_spritesheet.png";

	private String map;
	private GameEngine game;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private AssetManager assetManager;
	private Player player;
	private float elapsedTime = 0;
	private BitmapFont bitMapFontName;
	private GameState state;

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
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/Amatic-Bold.ttf"));

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		//parameter.characters = "Score: "+game.getState().getScore();

		bitMapFontName = generator.generateFont(parameter);
		generator.dispose();
		
	}

	@Override
	public void render(float delta) {
		//float dt = Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f);
		
		player.update(delta);
		Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// let the camera follow the player
		camera.position.x = player.getX();
		camera.update();
		renderer.setView(camera);
		renderer.render();

		game.getBatch().setProjectionMatrix(camera.combined);
		game.getBatch().begin();
		
		//bitMapFontName = new BitmapFont();
		
		player.draw(game.getBatch());
		bitMapFontName.setColor(1.0f,1.0f,1.0f,1.0f);
		bitMapFontName.draw(game.getBatch(), "Score: "+game.getState().getScore(), 0 + camera.position.x - 350, 0+camera.position.y+280);
		bitMapFontName.draw(game.getBatch(), "Level: "+game.getState().getLvl(), 0 + camera.position.x - 500, 0+camera.position.y+280);
		game.getBatch().end();
		
		/*
	     * Get the time it took to render the last frame and make sure our delta
	     * time is never larger than 0.0166666666666667 (60 FPS). This will
	     * ensure that on fast OS the game at only 60 FPS.
	     */
	   

	    // **********************UPDATE*********************

	    //System.out.print(elapsedTime);

		
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
