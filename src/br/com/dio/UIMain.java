package br.com.dio;

import java.awt.Dimension;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.panel.MainPanel;
import br.com.dio.ui.custom.screen.MainScreen;

public class UIMain {

	public static void main(String[] args) {
		final var gameConfig = Stream.of(args)
				.collect(Collectors.toMap(
						k -> k.split(";")[0],	
						v -> v.split(";")[1]));
		
		var mainScreen = new MainScreen(gameConfig);
		mainScreen.buildMainScreen();
	}

}
