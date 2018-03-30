package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.screens.EndLevel;
import com.relic.highflyer.screens.Level2;
import com.relic.highflyer.sprites.Player;

public class LandingDetector implements CollisionDetector {

	private EndLevel endLevel= new EndLevel();
	
	public LandingDetector(EndLevel endLLevel) {
		this.endLevel = endLLevel;
	}
	
	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isLandingCell(nextCells, nextX, nextY)) {
			endLevel.finishStage(game.getState());
			Level2 lvl2 = new Level2(game);
			game.getState().setLvl(2);
			game.setScreen(lvl2);
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

}
