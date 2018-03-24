package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public interface CollisionDetector {

	boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY);
}
