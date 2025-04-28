package br.com.dio.ui.custom.input;

import static java.awt.Font.PLAIN;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.dio.enums.EventEnum;
import br.com.dio.model.Space;
import br.com.dio.service.EventListener;;
public class NumberText extends JTextField implements EventListener{
	
	private final Space space;
	
	public NumberText(final Space space) {
		this.space = space;
		var dimension = new Dimension(50, 50);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setVisible(true);
		this.setFont(new Font("Arial", PLAIN, 20));
		this.setHorizontalAlignment(CENTER);
		this.setDocument(new NumberTextLimit());
		this.setEnabled(!space.isFixed());
		
		if(space.isFixed()) {
			this.setText(space.getActual().toString());
		}
		
		this.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changeSpaces();				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changeSpaces();			
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changeSpaces();				
			}
			
			private void changeSpaces() {
				if (getText().isEmpty()) {
					space.clearSpace();
					return;
				}
				
				space.setActual(Integer.parseInt(getText()));
			}
			
		});
	}

	@Override
	public void update(EventEnum eventType) {
		if(eventType.equals(EventEnum.CLEAR_SPACE) && this.isEnabled()) {
			this.setText("");
		}
		
	}
	
	
	
}
