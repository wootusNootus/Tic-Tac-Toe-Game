/*
 * This source file was generated by the Gradle 'init' task
 */
package tic.tac.toe.game;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Welcome to Tic-Tac-Toe!");
            int choice = 0;

            // Intro Menu 
            while (choice != 1 && choice != 2) 
            {
                System.out.println("Choose an option:");
                System.out.println("1. Play against a human");
                System.out.println("2. Play against the computer");
                
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice != 1 && choice != 2) {
                        System.out.println("Invalid option. Please select 1 or 2.");
                    }
                } 
                else {
                    System.out.println("Invalid input. Please enter a number (1 or 2).");
                    scanner.next(); // Clear the invalid input
                }
            }
            
            // Setting up the board
            char[] board = new char[9]; // Initializing a new board
            char currentPlayer = 'X'; // Player one starts with 'X'
            boolean gameWon = false;
            boolean boardFull = false;
            boolean isComputerPlayer = (choice == 2);
            Random random = new Random();

            while (!gameWon && !boardFull) 
            {
                printBoard(board);

                int move = 0;
                if (isComputerPlayer && currentPlayer == 'O') {
                    move = getRandomMove(board, random);
                    System.out.println("Computer chooses move " + move);
                } 
                else {
                    while (true) 
                    {
                        System.out.print("Player " + (currentPlayer == 'X' ? "one" : "two") + " - choose a move 1-9! ");
                        if (scanner.hasNextInt()) {
                            move = scanner.nextInt();
                            if (isValidMove(board, move)) {
                                break;
                            } 
                            else {
                                System.out.println("Invalid move! Please choose an available position between 1 and 9.");
                            }
                        } 
                        else {
                            System.out.println("Invalid input. Please enter a number between 1 and 9.");
                            scanner.next(); // Clear the invalid input
                        }
                    }
                }

                board[move - 1] = currentPlayer;
                gameWon = checkWin(board, currentPlayer);
                boardFull = isBoardFull(board);

                // if game hasn't been won, switch player
                if (!gameWon) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            printBoard(board);

            if (gameWon) {
                System.out.println("Player " + (currentPlayer == 'X' ? "one" : (isComputerPlayer ? "Computer" : "two")) + " wins!");
            } 
            else {
                System.out.println("Draw!");
            }

            // Exit Menu
            System.out.println("Do you want to play the same game again? (y/n)");
            char response = scanner.next().toLowerCase().charAt(0);
            playAgain = (response == 'y');

            if (!playAgain) {
                System.out.println("Thanks for playing! Goodbye!");
            }
        }

        scanner.close();
    }
    

    private static void printBoard(char[] board) 
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                char mark = board[i * 3 + j];
                System.out.print((mark == 0 ? (char) ('1' + i * 3 + j) : mark) + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    private static boolean isValidMove(char[] board, int move) 
    {
        return move >= 1 && move <= 9 && board[move - 1] == 0; // 0 is an untouched spot
    }

    /* Used https://www.geeksforgeeks.org/tic-tac-toe-game-in-java/
     * for inspiration
     * Author - sakshikulshreshtha
    */
    private static boolean checkWin(char[] board, char currentPlayer) 
    {
        for (int a = 0; a < 8; a++) 
        {
            String line = null;
    
            switch (a) 
            {
                case 0:
                    line = board[0] + "" + board[1] + "" + board[2];
                    break;
                case 1:
                    line = board[3] + "" + board[4] + "" + board[5];
                    break;
                case 2:
                    line = board[6] + "" + board[7] + "" + board[8];
                    break;
                case 3:
                    line = board[0] + "" + board[3] + "" + board[6];
                    break;
                case 4:
                    line = board[1] + "" + board[4] + "" + board[7];
                    break;
                case 5:
                    line = board[2] + "" + board[5] + "" + board[8];
                    break;
                case 6:
                    line = board[0] + "" + board[4] + "" + board[8];
                    break;
                case 7:
                    line = board[2] + "" + board[4] + "" + board[6];
                    break;
            }
    
            // Check for winner
            if (line.equals("XXX")) {
                return true; // X winning
            } 
            else if (line.equals("OOO")) {
                return true; // O winning
            }
        }
    
        return false; // No winner (draw)
    }
    

    private static boolean isBoardFull(char[] board) 
    {
        for (char cell : board) 
        {
            if (cell == 0) { 
                return false; 
            }
        }

        return true; 
    }

    private static int getRandomMove(char[] board, Random random) {
        int move = random.nextInt(9) + 1; // Random number 1 through 9
        
        while (!isValidMove(board, move)) 
        {
            move = random.nextInt(9) + 1; // generate move until it is valid
        }
        
        return move;
    }
    
    
}

