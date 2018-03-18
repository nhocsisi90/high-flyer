package com.relic.highflyer;

public final class GameSettings {

	private final int windowWidth;
	private final int windowHeight;

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

}
