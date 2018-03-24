package com.relic.highflyer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.relic.highflyer.GameState;

public class EndLevel {
	
	//private GameState state;
	
	public void finishStage(GameState state)
	{
       //play sound
	   //add score
	   //move to new level
		Sound finishSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/Finish.wav"));
		finishSound.play();
		
		state.addLvlUp();	

	}

}
