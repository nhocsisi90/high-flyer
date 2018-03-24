package com.relic.highflyer;

public final class GameSettings {

	private final int windowWidth;
	private final int windowHeight;
	private final int tileSize = 32;

	public GameSettings(int windowWidth, int windowHeight) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}
	
	public int getTileSize() {
		return tileSize;
	}

}
