package personal.bakunevich;

import java.util.Locale;
import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    boolean isRun;
    int countWinsX;
    int countWinsO;
    int countSymbols;
    int first;
    int second;
    char[] liters;

    public Game() {
        scanner = new Scanner(System.in);
        liters = new char[]{' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '};

        System.out.printf("""
                        \t\tWelcome to my second game!!!
                        \tIt's multiplayer TIC-TAC-TOE {-_-} ^_^ [_-_]
                        
                        \tFirst player use 'X'. Second player use 'O'.
                        \t\tGood luck for all
                        
                        
                        
                        \t---1-2-3---
                        \t1  %c %c %c  1
                        \t2  %c %c %c  2
                        \t3  %c %c %c  3
                        \t---1-2-3---
                        """,
                liters[0], liters[1], liters[2],
                liters[3], liters[4], liters[5],
                liters[6], liters[7], liters[8]);


        isRun = true;
        countWinsX = 0;
        countWinsO = 0;
        countSymbols = 0;
        first = 0;
        second = 0;
    }

    public void run () {
        while (isRun) {
            if (countSymbols % 2 == 0)
                System.out.println("Player 1, your turn!");
            else
                System.out.println("Player 2, your turn!");

            if (!scanner.hasNextInt() && scanner.next().toLowerCase(Locale.ROOT).equals("exit")) {
                break;
            }

            if (!scanner.hasNextInt()) {
                System.out.println("You should write only numbers!");
                scanner.nextLine();
                continue;
            } else {
                first = scanner.nextInt();
            }
            if (!scanner.hasNextInt()) {
                System.out.println("You should write only numbers!");
                scanner.nextLine();
                continue;
            } else {
                second = scanner.nextInt();
            }

            if (first < 1 || first > 3 ||
                    second < 1 || second > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (liters[(first - 1) * 3 + (second - 1)] == 'X' ||
                    liters[(first - 1) * 3 + (second - 1)] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                if (countSymbols % 2 == 0)
                    liters[(first - 1) * 3 + (second - 1)] = 'X';
                else
                    liters[(first - 1) * 3 + (second - 1)] = 'O';
                countSymbols++;
            }

            countWinsX += checkCols(liters, 'X');
            countWinsO += checkCols(liters, 'O');
            countWinsX += checkRows(liters, 'X');
            countWinsO += checkRows(liters, 'O');
            countWinsX += checkDiags(liters, 'X');
            countWinsO += checkDiags(liters, 'O');

            System.out.printf("""
                                                        
                                                        
                            \t---1-2-3---
                            \t1  %c %c %c  1
                            \t2  %c %c %c  2
                            \t3  %c %c %c  3
                            \t---1-2-3---
                            """,
                    liters[0], liters[1], liters[2],
                    liters[3], liters[4], liters[5],
                    liters[6], liters[7], liters[8]);

            if (countWinsX == 1) {
                System.out.println("First player wins, congratulations!!!");
                isRun = false;
            } else if (countWinsO == 1) {
                System.out.println("Second player wins, congratulations!!!");
                isRun = false;
            }
            if (countSymbols == 9)
                if (countWinsX == 1) {
                    System.out.println("First player wins, congratulations!!!");
                    isRun = false;
                } else if (countWinsO == 1) {
                    System.out.println("Second player wins, congratulations!!!");
                    isRun = false;
                } else {
                    System.out.println("Draw, it's boring...");
                    isRun = false;
                }
            if (!isRun) {
                countSymbols = 0;
                countWinsO = 0;
                countWinsX = 0;
                liters = new char[]{' ', ' ', ' ',
                        ' ', ' ', ' ',
                        ' ', ' ', ' '};
                isRun = oneMoreTime(scanner);
            }
        }
    }

    private int checkCols(char[] liters, char liter) {
        int countWins = 0;
        for (int i = 0; i < 3; i++) {
            if (    liters[i * 3] == liters[i * 3 + 1] &&
                    liters[i * 3 + 1] == liters[i * 3 + 2] &&
                    liters[i * 3] == liter)
                countWins++;
        }
        return countWins;
    }

    private int checkRows(char[] liters, char liter) {
        int countWins = 0;
        for (int i = 0; i < 3; i++) {
            if      (liters[i] == liters[i + 3] &&
                    liters[i + 3] == liters[i + 6] &&
                    liters[i] == liter)
                countWins++;
        }
        return countWins;
    }

    private int checkDiags(char[] liters, char liter) {
        int countWins = 0;
        if (liters[0] == liters[4] &&
                liters[4] == liters[8] &&
                liters[0] == liter)
            countWins++;

        if (liters[2] == liters[4] &&
                liters[4] == liters[6] &&
                liters[2] == liter)
            countWins++;

        return countWins;
    }

    private boolean oneMoreTime(Scanner scanner){
        System.out.println("""



                Do you want play one more time? Please write "Yes" or "No"\s
                """);
        while (true) {
            String answer = scanner.next().toLowerCase(Locale.ROOT);
            if (answer.equals("yes"))
                return true;
            else if (answer.equals("no"))
                return false;
            else System.out.println("Write correct answer, please");
        }
    }
}
