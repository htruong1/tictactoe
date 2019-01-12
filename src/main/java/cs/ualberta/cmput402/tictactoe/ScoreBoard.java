package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board.Player;

/**
 * ScoreBoard
 */
public class ScoreBoard {
    private int tie = 0;
    private int x_win = 0;
    private int x_lose = 0;
    private int o_win = 0;
    private int o_lose = 0;

    /**
     * Updates the score counter of the players or tie.
     * @param winner the winner of the game
     */ 
    public void update(Player winner) {
        if (winner == Player.NONE) {
            tie += 1;
        }
        if (winner == Player.X) {
            x_win += 1;
            o_lose +=1;
        }
        else if (winner == Player.O) {
            o_win += 1;
            x_lose +=1;
        }
    }

    /**
     * Gets the full scoreboard. 
     * @return a formatted string of all scores.
     */
    public void printScore() {
        System.out.print(String.format(
            "Ties:%d\n"+
            "X's wins %d\n"+ 
            "X's losses: %d\n"+ 
            "O's wins: %d\n"+
            "O's losses: %d\n",
            tie, x_win, x_lose, o_win, o_lose
            ));
    }
}