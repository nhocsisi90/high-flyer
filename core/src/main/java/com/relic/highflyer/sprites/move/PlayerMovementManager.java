package com.relic.highflyer.sprites.move;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.screens.ScreenManager;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class PlayerMovementManager {

	private List<CollisionDetector> collisionDetectors = new ArrayList<>();

	public PlayerMovementManager() {
		SoundPlayer soundPlayer = new SoundPlayer();
		
		collisionDetectors.add(new BuildingCollisionDetector(soundPlayer));
		collisionDetectors.add(new WindowCollisionDetector());
		collisionDetectors.add(new LandingDetector(soundPlayer, ScreenManager.INSTANCE));
		//collisionDetectors.add(new PowerUpDetector(soundPlayer));
		//collisionDetectors.add(new LandingDetector(endlevl));
		collisionDetectors.add(new PowerUpDetector(soundPlayer));
	}

	public boolean tryMove(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		boolean canMove = true;
		for (CollisionDetector collisionDetector : collisionDetectors) {
			boolean hasCollision = collisionDetector.detect(game, nextCells, player, nextX, nextY);
			if (hasCollision) {
				return false;
			}
			
		}
		
		if (canMove) {
			player.setX(nextX);
			player.setY(nextY);
		}

		return canMove;
	}
}
