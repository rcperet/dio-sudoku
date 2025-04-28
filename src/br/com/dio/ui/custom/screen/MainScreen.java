package br.com.dio.ui.custom.screen;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static br.com.dio.enums.EventEnum.CLEAR_SPACE;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import br.com.dio.model.Space;
import br.com.dio.service.BoardService;
import br.com.dio.service.NotifierService;
import br.com.dio.ui.custom.button.CheckGameStatusButton;
import br.com.dio.ui.custom.button.FinishGameButton;
import br.com.dio.ui.custom.button.ResetButton;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.input.NumberText;
import br.com.dio.ui.custom.panel.MainPanel;
import br.com.dio.ui.custom.panel.SudokuSector;

public class MainScreen {
	private static final Dimension dimension = new Dimension(600, 600);
	private final BoardService boardService;
	private final NotifierService notifierService;
	
	private JButton resetGameButton;
	private JButton finishGameButton;
	private JButton checkGameStatusButton;
	
	
	public MainScreen(final Map<String, String> gameConfig) {
		this.boardService = new BoardService(gameConfig);
		this.notifierService = new NotifierService();
	}
	
	public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            var endRow = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endCol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c, endCol, r, endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
	}
	
	private List<Space> getSpacesFromSector(List<List<Space>> spaces, final int initCol, final int endCol, 
			final int initRow, final int endRow){
		List<Space> spaceSector = new ArrayList<>();
		for(int r = initRow; r <= endRow; r++) {
			for(int c = initCol; c <= endCol; c++) {
				spaceSector.add(spaces.get(c).get(r));
			}
		}
		return spaceSector;
	}
	
	private JPanel generateSection(final List<Space> spaces) {
		List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
		fields.forEach(t -> notifierService.subscriber(CLEAR_SPACE, t));
		return new SudokuSector(fields);
		
	}


	private void addFinishGameButton(JPanel mainPanel) { 
		finishGameButton = new FinishGameButton(e -> {
			if(boardService.gameIsFineshed()) {
				JOptionPane.showMessageDialog(null, "Parabéns! Você concluiu o jogo!");
				resetGameButton.setEnabled(false);
				checkGameStatusButton.setEnabled(false);
				finishGameButton.setEnabled(false);
				
			} else {
				showMessageDialog(null, "Seu jogo tem alguma inconsistência. Ajuste e tente novamente");
			}
			
			
		});
		mainPanel.add(finishGameButton);
		
	}

	private void addCheckGameStatusButton(JPanel mainPanel) {
		checkGameStatusButton = new CheckGameStatusButton(e -> {
			var hasErros = boardService.hasErrors();
			var gameStatus = boardService.getStatus();
			
			var message = switch(gameStatus) {
			case NON_STARTED -> "O jogo não foi iniciado";
			case INCOMPLETE -> "O jogo está incompleto";
			case COMPLETE -> "O jogo está completo";
			
			};
			
			message += hasErros ? " e contém erros" : " e não contém erros";
			
			showMessageDialog(null, message);
		});
		
		mainPanel.add(checkGameStatusButton);
		
	}

	private void addResetButton(JPanel mainPanel) {
		resetGameButton = new ResetButton(e -> {
			var dialogResult = showConfirmDialog(
					null, 
					"Deseja realmente reiniciar o jogo?",
					"Limpar o jogo",
					YES_NO_OPTION,
					QUESTION_MESSAGE
			);
			if(dialogResult == YES_OPTION) {
				boardService.reset();
				notifierService.notify(CLEAR_SPACE);
			}
		});
		mainPanel.add(resetGameButton);
		
	}
	
}
