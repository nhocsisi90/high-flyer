package com.relic.highflyer.sprites.move;

import static org.junit.Assert.assertEquals;

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

public class BuildingCollisionDetectorTest {

	private BuildingCollisionDetector subject;
	private SoundPlayer mockSoundPlayer;
	private Player mockPlayer;

	@Before
	public void setup() {
		mockPlayer = Mockito.mock(Player.class);
		Mockito.when(mockPlayer.getPlayerHeight()).thenReturn(80);
		mockSoundPlayer = Mockito.mock(SoundPlayer.class);
		subject = new BuildingCollisionDetector(mockSoundPlayer);
	}

	@Test
	public void testDetect_hasCollision() {
		GameState state = new GameState();
		state.setScore(110);
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());

		List<Cell> nextCells = new ArrayList<>();
		Cell collisionCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("damage", true);
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		collisionCell.setTile(mockTile);
		nextCells.add(collisionCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);

		Mockito.verify(mockPlayer).setX(Mockito.eq(0f));
		Mockito.verify(mockPlayer).setY(Mockito.eq(260f));

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/Shutdown.wav"));
		
		assertEquals("Mismatching game score", 10, state.getScore());
	}
}
