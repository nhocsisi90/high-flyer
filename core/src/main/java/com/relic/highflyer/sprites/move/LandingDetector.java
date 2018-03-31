package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.GameState;
import com.relic.highflyer.screens.ScreenManager;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class LandingDetector implements CollisionDetector {

	private ScreenManager screenManager;
	private SoundPlayer soundPlayer;
	public LandingDetector(SoundPlayer soundPlayers, ScreenManager screenManager) {
		this.soundPlayer = soundPlayers;
		this.screenManager = screenManager;
	}
	
	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isLandingCell(nextCells, nextX, nextY)) {
			finishStage(game.getState());
			game.getState().setLvl(2);
			Screen screen = screenManager.getScreen(2);
			game.setScreen(screen);
			
			player.setX(0);
			player.setY((game.getSettings().getWindowHeight()/2) - (player.getPlayerHeight()/2));
			
			return true;
		}
		
		return false;
	}

	private boolean isLandingCell(List<Cell> nextCells, float x, float y) {
		for (Cell cell: nextCells) {
			if (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("landing")) {
				return true;
			}
		}
		return false;
		
	}
	private void finishStage(GameState state)
	{
       //play sound
	   //add score
	   //move to new level
		//Sound finishSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/Finish.wav"));
		soundPlayer.playSound("data/sounds/Finish.wav");
		//finishSound.play();
		
		state.addLvlUp();	

	}
	

}
