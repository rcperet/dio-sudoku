package br.com.dio.ui.custom.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResetButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	public  ResetButton(final ActionListener actionListener) {
		this.setText("Reiniciar jogo");
		this.addActionListener(actionListener);
	}
	

}
