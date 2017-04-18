import java.util.Scanner;
//import java.awt.*;
//import java.math.*;
import java.util.*;

public class TicTacToe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int gameOver = 0;
		int[] moves = new int[9];
		Scanner sc = new Scanner(System.in);
		int nextMove;
		String board = "";
		int winner = 0;
		int computerMove = 0;
		
		while(gameOver == 0){
			board = getBoardString(moves);
			System.out.println(board);
			System.out.println("Please enter a number 1 - 9: ");
			nextMove = sc.nextInt();
			
			//checks if valid move on the board
			if(nextMove > 9 || nextMove < 1){
				System.out.println("Not a valid move on the board");
			}
			//checks if position has already been played.
			else if(moves[nextMove - 1] == 1 || moves[nextMove -1] == -1){
				System.out.println("Not an available space. \n Please play a valid position");
			}
			//
			else{
					moves[nextMove - 1] = 1;
					winner = checkForWinner(moves);
					if(winner == 1){
						board = getBoardString(moves);
						System.out.println(board);
						System.out.println("First player wins!");
						gameOver = 1;
					}
					else{
						//computerMove = minimax(moves);
						computerMove = getComputerMove(moves);
						if(computerMove == -1){
							System.out.println("No more valid spaces, game over");
							gameOver = 1;
						}
						else{
							moves[computerMove] = -1;
							winner = checkForWinner(moves);
							
							if(winner == -1){
								board = getBoardString(moves);
								System.out.println(board);
								System.out.println("Computer wins!");
								gameOver = 1;
							}
						}	
					}
			}
		}
	}
	
	public static int checkForWinner(int[] moves){
		int winTracker = 0;
		for(int i = 0; i < moves.length - 2; i++){
			if(moves[i] == 1){
				if(i==0){
					if(moves[1] == 1 && moves[2] == 1){
						winTracker = 1;
					}
					if(moves[4] == 1 && moves[8] == 1){
						winTracker = 1;
					}
					if(moves[3] == 1 && moves[6] == 1){
						winTracker = 1;
					}
				}
				else if(i==1){
					if(moves[4] == 1 && moves[7] == 1){
						winTracker = 1;
					}
				}
				else if(i==2){
					if(moves[5] == 1 && moves[8] == 1){
						winTracker = 1;
					}
					if(moves[4] == 1 && moves[6] == 1){
						winTracker = 1;
					}
				}
				else if(i==3){
					if(moves[4] == 1 && moves[5] == 1){
						winTracker = 1;
					}
				}

				else if(i==6){
					if(moves[7] == 1 && moves[8] == 1){
						winTracker = 1;
					}
				}
			}
			else if(moves[i] == -1){
					if(i==0){
						if(moves[1] == -1 && moves[2] == -1){
							winTracker = -1;
						}
						if(moves[4] == -1 && moves[8] == -1){
							winTracker = -1;
						}
						if(moves[3] == -1 && moves[6] == -1){
							winTracker = -1;
						}
					}
					else if(i==1){
						if(moves[4] == -1 && moves[7] == -1){
							winTracker = -1;
						}
					}
					else if(i==2){
						if(moves[5] == -1 && moves[8] == -1){
							winTracker = -1;
						}
						if(moves[4] == -1 && moves[6] == -1){
							winTracker = -1;
						}
					}
					else if(i==3){
						if(moves[4] == -1 && moves[5] == -1){
							winTracker = -1;
						}
					}

					else if(i==6){
						if(moves[7] == -1 && moves[8] == -1){
							winTracker = -1;
						}
					}
				}
		}
		return winTracker;
	}
	public static String getBoardString(int[] positions){
		String board = "";
		int middlePiece = 0;

		for(int i = 0; i < positions.length; i++){
			
			//checks if is middle piece
			if(i % 3 == 1){
				middlePiece = 1;
				board = board + " | ";
			}
			if(positions[i] == 1){
				board = board + "X";
			}
			else if(positions[i] == -1){
				board = board + "O";
			}
			else{
				board = board + "-";
			}
			if(middlePiece == 1){
				board = board + " | ";
			}
			if(i % 3 == 2){
				board = board + "\n";
			}
			middlePiece = 0;
		}
			
		return board;
	}
	public static int getComputerMove(int[] moves){
		int random = 0;
		int validMove = 0;
		int fullBoard = 1;
		int bestMove = 0;
		
		for (int i = 0; i < moves.length; i++){
			if(moves[i] == 0){
				fullBoard = 0;
			}
		}
		if(fullBoard == 0){
			int temp = 0;
			for(int q = 0; q <moves.length; q++){
				temp = temp + numInlineWithPlayer(moves);
				if(q==5){
					temp = temp + 10;
				}
			}
			
			
			while(validMove != 1){
				random = (int)(Math.random()*8);
				if(moves[random] == 0){
					validMove = 1;
				}
			}
		}
		else{
			random = -1;
		}
		return random;
	}
	public static int numInlineWithPlayer(int[] moves){
		return 0;
	}
	public static int numInlineWithComputer(int[] moves){
		return 0;
	}
	public static int minimax(int[] moves){
		int winner = checkForWinner(moves);
		int numMoves = 0;
		int turn = 0;
		int max = 100000;
		int min = -10000;
		int tmpMinimax = 0;
		List<int[]> availableMoves = new ArrayList<int[]>();
		if(winner!=0){
			return winner;
		}
		for(int i = 0; i < moves.length; i++){
			if(moves[i]!=0){
				numMoves++;
			}
		}
		turn = numMoves % 2;
		for(int i = 0; i < moves.length; i++){
			 tmpMinimax = minimax(moves);
			if(turn == 0){
				if(tmpMinimax > min){
					min = tmpMinimax;
				}
			}
			else{
				if(tmpMinimax < max){
					max = tmpMinimax;
				}
			}
		}
		return 0;
	}
}
