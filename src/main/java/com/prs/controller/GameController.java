package com.prs.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;


@CrossOrigin
@RestController
@RequestMapping("/api/play")
public class GameController {
	
	   private final Random random = new Random();
	    private enum Choice {
	        GIANTS, WIZARDS, ELVES;
	    }
	    private char[][] board = new char[3][3];
	    private char currentPlayer = 'X'; // X always starts the game

	    public GameController() {
	        resetBoard();
	    }

	    @PostMapping("")
	    public String playGame(@RequestParam  Choice userChoice) {
	        Choice computerChoice = Choice.values()[random.nextInt(Choice.values().length)];
	        String result = determineWinner(userChoice, computerChoice);
	        return result;
	    }

	    private String determineWinner(Choice user, Choice computer) {
	        if (user == computer) {
	            return "It's a tie! Both chose " + user;
	        }

	        switch (user) {
	            case GIANTS:
	                return (computer == Choice.ELVES) ? "You win! Giants crush wizards." : "You lose! Elves beat giants.";
	            case WIZARDS:
	                return (computer == Choice.GIANTS) ? "You win! Wizards zap elves." : "You lose! Giants beat wizards.";
	            case ELVES:
	                return (computer == Choice.WIZARDS) ? "You win! Elves trip giants." : "You lose! Wizards beat elves.";
	            default:
	                return "Invalid choice.";
	        }
	    }


	    @PostMapping("/move")
	    public String makeMove(@RequestParam int x, @RequestParam int y) {
	        if (x < 0 || x >= 3 || y < 0 || y >= 3 || board[x][y] != '-') {
	            return "Invalid move";
	        }

	        board[x][y] = currentPlayer;
	        if (checkWinner(currentPlayer)) {
	            String winner = "Player " + currentPlayer + " wins!";
	            resetBoard(); // Reset board after a win
	            return winner;
	        }
	        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
	        return "Move accepted";
	    }

	    @GetMapping("/reset")
	    public String resetGame() {
	        resetBoard();
	        return "Game reset successfully";
	    }

	    private void resetBoard() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                board[i][j] = '-';
	            }
	        }
	        currentPlayer = 'X'; // X starts the game
	    }

	    private boolean checkWinner(char player) {
	        // Horizontal, Vertical & Diagonal checks
	        for (int i = 0; i < 3; i++) {
	            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
	                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
	                return true;
	            }
	        }
	        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
	            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
	            return true;
	        }
	        return false;
	    }
	}
	    
	    



	

