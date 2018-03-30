package com.relic.highflyer.sprites.move;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.GameSettings;
import com.relic.highflyer.GameState;
import com.relic.highflyer.screens.ScreenManager;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class PowerUpDetectorTest {

	private PowerUpDetector subject;
	private SoundPlayer mockSoundPlayer;
	private Player mockPlayer;
	private String typePower;

	@Before
	public void setup() {
		mockPlayer = Mockito.mock(Player.class);
		Mockito.when(mockPlayer.getPlayerHeight()).thenReturn(80);
		mockSoundPlayer = Mockito.mock(SoundPlayer.class);
		subject = new PowerUpDetector(mockSoundPlayer);
	}

	@Test
	public void testDetect_hasCollision() {
		GameState state = new GameState();
		state.setScore(100);
		typePower="";
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());

		List<Cell> nextCells = new ArrayList<>();
		Cell collisionCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("power", "star");
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		collisionCell.setTile(mockTile);
		nextCells.add(collisionCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/PowerUp.wav"));

		assertEquals("Mismatching game score", 110, state.getScore());
	}
	
	
	@Test
	public void testDetect_hasCollisionMulCells() {
		GameState state = new GameState();
		state.setScore(100);
		typePower="";
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());

		List<Cell> nextCells = new ArrayList<>();
		Cell collisionCell1 = new Cell();
		Cell collisionCell2 = new Cell();
		Cell collisionCell3 = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		TiledMapTile mockTile1 = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("nopower", "star");
		
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		collisionCell1.setTile(mockTile);
		nextCells.add(collisionCell1);
		collisionCell2.setTile(mockTile);
		nextCells.add(collisionCell2);
		MapProperties mapProperties1 = new MapProperties();
		mapProperties1.put("power", "star");
		
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties1);
		collisionCell3.setTile(mockTile1);
		nextCells.add(collisionCell3);

		subject.detect(game, nextCells, mockPlayer, 100, 100);

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/PowerUp.wav"));

		assertEquals("Mismatching game score", 110, state.getScore());
	}

	@Test
	public void testDetect_noCollistion() {
		GameState state = new GameState();
		state.setScore(100);
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());

		List<Cell> nextCells = new ArrayList<>();
		Cell collisionCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("notpower", "star");
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		collisionCell.setTile(mockTile);
		nextCells.add(collisionCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);

		Mockito.verifyZeroInteractions(mockSoundPlayer);

		assertEquals("Mismatching game score", 100, state.getScore());

	}

}
