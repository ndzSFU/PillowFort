package View;

import Model.BoardSpot;
import Model.Fort;
import Model.GameBoard;

public class GameRunner {
    UI userInterface = new UI();

    boolean gameDone = false;
    private int oppPoints = 0;

    private final int oppWinCondition = 2500;

    private void printEndGameStats(GameBoard board){
        System.out.println("Opponents points: " + oppPoints + "/" + oppWinCondition + ".");

        if(board.allFortsDamaged()){
            System.out.println("Congratulations! You won!");
        } else{
            System.out.println("I'm sorry, your fort is all wet! They win!");
        }

        userInterface.printBoard(board, true, false);
        System.out.println("Lower case fort letters are where you shot.");
    }

    private int getDamageFromForts(GameBoard board){
        int damageFromForts = 0;

        int fortCounter = 1;

        for(Fort f: board.getForts()){
            if(f.dealDamage() > 0){
                System.out.println("Opponent #" + fortCounter + " of " + board.numWorkingForts() + " shot you for " + f.dealDamage() + " points!");
                fortCounter++;
                damageFromForts += f.dealDamage();
            }
        }
        return damageFromForts;
    }

    BoardSpot getBoardSpotFromUser(GameBoard board){
        String userSpot = userInterface.getUserHit();
        BoardSpot chosenSpot = board.GetInputtedBoardSpot(userSpot);

        while(chosenSpot.isHit()){
            System.out.println("Spot already hit.");
            userSpot = userInterface.getUserHit();
            chosenSpot = board.GetInputtedBoardSpot(userSpot);
        }

        return chosenSpot;
    }


    public void RunGame(GameBoard board, String cheating){

        boolean cheat = cheating.equals("--cheat");

        if(cheat){
            //Pretends game is done so the cheat end game view of the board can be displayed
            userInterface.printBoard(board, true, cheat);
        }
        userInterface.printBoard(board, gameDone, cheat);

        while(oppPoints < oppWinCondition && !board.allFortsDamaged()){
            System.out.println("Opponents points: " + oppPoints + "/" + oppWinCondition + ".");

            BoardSpot chosenSpot = getBoardSpotFromUser(board);
            chosenSpot.Hit();

            if(chosenSpot.isFort()){
                System.out.println("HIT!");
                char targetFort = chosenSpot.getFortLabel();
                Fort hitFort = board.getFort(targetFort);
                hitFort.takeDamage();
            }else{
                System.out.println("Miss.");
            }
            oppPoints+=getDamageFromForts(board);

            userInterface.printBoard(board, gameDone, cheat);
        }
        printEndGameStats(board);
    }
}
