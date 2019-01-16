package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import cs.ualberta.cmput402.tictactoe.ScoreBoard;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;
    private boolean play = true;
    private ScoreBoard scoreboard;

    public TicTacToeGame(){
        board = new Board();
        scoreboard = new ScoreBoard();
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

        while (play) {
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

            if(board.getWinner() == Player.NONE){
                System.out.println("Game reached a tie!");
            }else{
                System.out.println("Player " + board.getWinner() + " has won the game!");
            }

            scoreboard.update(board.getWinner());
            
            // Ask player to play again
            System.out.println("Would you like to play again? (y/n)");
            String line = keyboardScanner.nextLine(); 
            System.out.println("\n\n"); // to make things pretty!

            if (line.equals("n") ) {
                play = false;
                scoreboard.printScore();
            }
            else if (line.equals("y")){
                board = new Board();
            }
        }

        keyboardScanner.close();
    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
