package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import cs.ualberta.cmput402.tictactoe.Scoreboard; 

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;
    private Scoreboard scoreboard;    
	
    public TicTacToeGame(){
        board = new Board();
        scoreboard = new Scoreboard();
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);
        
        Boolean play = true;
        
		while(play) {
			while (board.getWinner() == null){
				board.printBoard();
				promptNextPlayer();
				String line = keyboardScanner.nextLine();
				String input[] = line.split(",");
				try {
					board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
				} catch (InvalidMoveException e) {
					System.out.println("Invalid coordinates. Try again");
					promptNextPlayer();
				}
			}

			board.printBoard();
			if(board.getWinner() == Player.NONE){
				System.out.println("Game reached a tie!");
			}else{
				System.out.println("Player " + board.getWinner() + " has won the game!");
			}
			scoreboard.update(board.getWinner());
			scoreboard.printScore();

			String play_again = "";

			do {
				System.out.println("\nWould you like to play again? (y/n)");
				play_again = keyboardScanner.nextLine();

				if (play_again.equalsIgnoreCase("n")) {
					play = false;
				}
				else if (play_again.equalsIgnoreCase("y")) {
					board = new Board();
				}
				else {
					System.out.println("Please enter y for yes or n for no.");
				}

			} while (!(play_again.equalsIgnoreCase("y") || play_again.equalsIgnoreCase("n")));
		}
        
    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
