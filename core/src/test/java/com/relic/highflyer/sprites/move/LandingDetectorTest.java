package com.relic.highflyer.sprites.move;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.GameSettings;
import com.relic.highflyer.GameState;
import com.relic.highflyer.screens.Level2;
import com.relic.highflyer.screens.ScreenManager;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class LandingDetectorTest {

	private LandingDetector subject;
	private SoundPlayer mockSoundPlayer;
	private Player mockPlayer;
	private ScreenManager mockScreenManager;
	

	@Before
	public void setup() {
		mockPlayer = Mockito.mock(Player.class);
		mockScreenManager = Mockito.mock(ScreenManager.class);
		Mockito.when(mockPlayer.getPlayerHeight()).thenReturn(80);
		mockSoundPlayer = Mockito.mock(SoundPlayer.class);
		subject = new LandingDetector(mockSoundPlayer, mockScreenManager);
		
		// Mock gdx state
		Gdx.graphics = Mockito.mock(Graphics.class);
	}

	@Test
	public void testDetect_hasLanding() {
		GameState state = new GameState();
		
		
		//state.setScore(110);
		
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());
		Mockito.when(mockScreenManager.getScreen(Mockito.anyInt())).thenReturn(Mockito.mock(Screen.class));
		List<Cell> nextCells = new ArrayList<>();
		Cell landingCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("landing", true);
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		landingCell.setTile(mockTile);
		nextCells.add(landingCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);
		assertEquals("Mismatching game level", 2, game.getState().getLvl());
		Mockito.verify(mockPlayer).setX(Mockito.eq(0f));
		Mockito.verify(mockPlayer).setY(Mockito.eq(260f));

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/Finish.wav"));
		
		
		//assertEquals("Mismatching game score", 10, state.getScore());
	}
	
	@Test
	public void testDetect_noLanding() {
		GameState state = new GameState();
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());
		Mockito.when(mockScreenManager.getScreen(Mockito.anyInt())).thenReturn(Mockito.mock(Screen.class));
		List<Cell> nextCells = new ArrayList<>();
		Cell landingCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("notlanding", true);
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		landingCell.setTile(mockTile);
		nextCells.add(landingCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);
		assertEquals("Mismatching game level", 1, game.getState().getLvl());
		Mockito.verifyZeroInteractions(mockSoundPlayer);

		
	}
	
	@Test
	public void testDetect_hasMulCell() {
		GameState state = new GameState();
		
		
		//state.setScore(110);
		
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());
		Mockito.when(mockScreenManager.getScreen(Mockito.anyInt())).thenReturn(Mockito.mock(Screen.class));
		List<Cell> nextCells = new ArrayList<>();
		Cell landingCell1 = new Cell();
		Cell landingCell2 = new Cell();
		Cell landingCell3 = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("landing", true);
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		landingCell1.setTile(mockTile);
		nextCells.add(landingCell1);
		nextCells.add(landingCell2);
		nextCells.add(landingCell3);

		subject.detect(game, nextCells, mockPlayer, 100, 100);
		assertEquals("Mismatching game level", 2, game.getState().getLvl());
		Mockito.verify(mockPlayer).setX(Mockito.eq(0f));
		Mockito.verify(mockPlayer).setY(Mockito.eq(260f));

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/Finish.wav"));
		
		
		//assertEquals("Mismatching game score", 10, state.getScore());
	}
}
