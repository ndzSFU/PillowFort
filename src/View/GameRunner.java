package View;

import Model.BoardSpot;
import Model.Fort;
import Model.GameBoard;

public class GameRunner {
    UI userInterface = new UI();

    private final int oppWinCondition = 2500;


    boolean gameDone = false;
    private int oppPoints = 0;

    public void RunGame(GameBoard board, String cheating){

        boolean cheat = false;

        if(cheating.equals("--cheat")){
            cheat = true;
        }

        if(cheat){
            userInterface.printBoard(board, cheat);
        }

        userInterface.printBoard(board, gameDone);


        while(oppPoints < oppWinCondition && !board.allFortsDamaged()){

            System.out.println("Opponents points: " + oppPoints + "/" + oppWinCondition + ".");

            String userSpot = userInterface.getUserHit();
            BoardSpot chosenSpot = board.GetInputtedBoardSpot(userSpot);


            while(chosenSpot.isHit()){
                System.out.println("Spot already hit.");
                userSpot = userInterface.getUserHit();
                chosenSpot = board.GetInputtedBoardSpot(userSpot);
            }
            chosenSpot.Hit();

            if(chosenSpot.isFort()){
                char targetFort = chosenSpot.getFortLabel();
                Fort hitFort = board.getFort(targetFort);
                hitFort.takeDamage();
            }

            int damageFromForts = 0;

            int tempCounter = 1;

            for(Fort f: board.getForts()){
                System.out.println(tempCounter + " hit: " + f.dealDamage());
                damageFromForts += f.dealDamage();
                tempCounter++;
            }

            oppPoints+=damageFromForts;

            userInterface.printBoard(board, gameDone);
        }

        if(board.allFortsDamaged()){
            System.out.println("YOU WIN");
        } else{
            System.out.println("YOU LOSE");
        }
        gameDone = true;
        userInterface.printBoard(board, gameDone);
    }
}
