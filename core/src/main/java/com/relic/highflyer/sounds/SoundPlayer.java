package com.relic.highflyer.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {

	public void playSound(String soundFilePath) {
		Sound shutdownSound = Gdx.audio.newSound(Gdx.files.internal(soundFilePath));
		shutdownSound.play();
	}
}
