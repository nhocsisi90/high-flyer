package com.relic.highflyer.sprites.move;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.screens.EndLevel;
import com.relic.highflyer.screens.Level2;
import com.relic.highflyer.sprites.Player;

public class LandingDetector implements CollisionDetector {

	private EndLevel endLevel= new EndLevel();
	@Override
	public boolean detect(GameEngine game, Cell currentCell, Player player, float nextX, float nextY) {
		if (isLandingCell(currentCell, nextX, nextY)) {
			endLevel.finishStage(game.getState());
			Level2 lvl2 = new Level2(game);
			game.getState().setLvl(2);
			game.setScreen(lvl2);
			player.setX(0);
			player.setY(32*4);
			
			
			return true;
		}
		
		return false;
	}

	private boolean isLandingCell(Cell cell, float x, float y) {
		return cell != null && cell.getTile().getProperties().containsKey("landing");
	}

}
