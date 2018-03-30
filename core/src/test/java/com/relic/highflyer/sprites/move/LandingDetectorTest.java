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
import com.relic.highflyer.screens.EndLevel;
import com.relic.highflyer.screens.Level2;
import com.relic.highflyer.screens.ScreenManager;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class LandingDetectorTest {

	private LandingDetector subject;
	private SoundPlayer mockSoundPlayer;
	private Player mockPlayer;
	private EndLevel endLevel;
	

	@Before
	public void setup() {
		mockPlayer = Mockito.mock(Player.class);
		endLevel = Mockito.mock(EndLevel.class);
		Mockito.when(mockPlayer.getPlayerHeight()).thenReturn(80);
		mockSoundPlayer = Mockito.mock(SoundPlayer.class);
		subject = new LandingDetector(endLevel);
	}

	@Test
	public void testDetect_hasLanding() {
		GameState state = new GameState();
		
		
		//state.setScore(110);
		GameEngine game = new GameEngine(new GameSettings(800, 600), state, new ScreenManager());
		Level2 lvl2 = Mockito.mock(Level2.class);
		game.setScreen(lvl2);
		

		List<Cell> nextCells = new ArrayList<>();
		Cell landingCell = new Cell();
		TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
		MapProperties mapProperties = new MapProperties();
		mapProperties.put("landing", true);
		Mockito.when(mockTile.getProperties()).thenReturn(mapProperties);
		landingCell.setTile(mockTile);
		nextCells.add(landingCell);

		subject.detect(game, nextCells, mockPlayer, 100, 100);
		
		Mockito.verify(game).getState().setLvl(Mockito.eq(2));
		Mockito.verify(mockPlayer).setX(Mockito.eq(0f));
		Mockito.verify(mockPlayer).setY(Mockito.eq(260f));

		Mockito.verify(mockSoundPlayer).playSound(Mockito.eq("data/sounds/Finish.wav"));
		
		//assertEquals("Mismatching game score", 10, state.getScore());
	}
}
