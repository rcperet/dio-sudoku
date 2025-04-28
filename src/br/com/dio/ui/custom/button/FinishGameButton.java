package br.com.dio.ui.custom.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishGameButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	
	public  FinishGameButton(final ActionListener actionListener) {
		this.setText("Concluir");
		this.addActionListener(actionListener);
	}

}
