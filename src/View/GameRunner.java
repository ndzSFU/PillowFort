package View;

import Model.BoardSpot;
import Model.Fort;
import Model.GameBoard;

public class GameRunner {
    UI userInterface = new UI();




    boolean gameDone = false;
    private int oppPoints = 0;

    public void RunGame(GameBoard board, String cheating){

        final int oppWinCondition = 2500;

        boolean cheat = cheating.equals("--cheat");

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
                System.out.println("HIT!");
                char targetFort = chosenSpot.getFortLabel();
                Fort hitFort = board.getFort(targetFort);
                hitFort.takeDamage();
            }else{
                System.out.println("Miss.");
            }

            int damageFromForts = 0;

            int fortCounter = 1;

            for(Fort f: board.getForts()){
                if(f.dealDamage() > 0){
                    System.out.println("Opponent #" + fortCounter + " of " + board.numWorkingForts() + " shot you for " + f.dealDamage() + " points!");
                    fortCounter++;
                    damageFromForts += f.dealDamage();
                }
            }

            oppPoints+=damageFromForts;

            userInterface.printBoard(board, gameDone);
        }

        if(board.allFortsDamaged()){
            System.out.println("Congratulations! You won!");
        } else{
            System.out.println("I'm sorry, your fort is all wet! They win!");
        }
        gameDone = true;
        userInterface.printBoard(board, gameDone);
    }
}
