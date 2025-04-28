package br.com.dio.ui.custom.panel;

import static java.awt.Color.black;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.com.dio.ui.custom.input.NumberText;


public class SudokuSector extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SudokuSector(final List<NumberText> textFields) {
		var dimension = new Dimension(170, 170);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setBorder(new LineBorder(black, 2, true));
		this.setVisible(true);
		textFields.forEach(this:: add);
	}

}
