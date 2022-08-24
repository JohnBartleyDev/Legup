package edu.rpi.legup.puzzle.battleship.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.ContradictionRule;
import edu.rpi.legup.puzzle.battleship.BattleshipBoard;
import edu.rpi.legup.puzzle.battleship.BattleshipCell;
import edu.rpi.legup.puzzle.battleship.BattleshipType;

import java.util.List;


public class IncompleteShipContradictionRule extends ContradictionRule {

    public IncompleteShipContradictionRule() {
        super("BTSP-CONT-0002"
            "Incomplete Ship",
                "Ships must have middle and end points(besides submarines",
                "edu/rpi/legup/images/battleship/contradictions/IncompleteShip.png");
    }

     /**
     * Checks whether the transition has a contradiction at the specific
     * {@link PuzzleElement} index using this rule.
     *
     * @param board         board to check contradiction
     * @param puzzleElement equivalent {@link PuzzleElement}
     * @return <code>null</code> if the transition contains a
     * contradiction at the specified {@link PuzzleElement},
     * otherwise return a no contradiction message.
     */
    @Override
    public String checkContradictionAt(Board board, PuzzleElement puzzleElement) {
        BattleshipBoard bsBoard = (BattleshipBoard) board;
        BattleshipCell cell = (BattleshipCell) bsBoard.getPuzzleElement(puzzleElement);
        //checks if rule applies to specific cell
        if (!BattleshipType.isShip(cell.getType())) {
            return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
        }
        //ignores middle segments
        if(cell.getType() == SHIP_SEGMENT_MIDDLE){
            return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
        }
        //ignores submarines
        if(cell.getType() == SHIP_SIZE_1){
            return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
        }
        //checks ship from top segment going down
        if(cell.getType() == SHIP_SEGMENT_TOP){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            while(orthoAdjCells.get(2).getType == SHIP_SEGMENT_MIDDLE){
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //checks the end of the ship to make sure its the correct segment type
            if(!(orthoAdjCells.get(2).getType()==SHIP_SEGMENT_BOTTOM)){
                return null;
            }
            else{
                return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
            }
        }
        //checks ship from bottom segment going up
        if(cell.getType() == SHIP_SEGMENT_BOTTOM){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            while(orthoAdjCells.get(0).getType == SHIP_SEGMENT_MIDDLE){
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //checks the end of the ship to make sure its the correct segment type
            if(!(orthoAdjCells.get(0).getType()==SHIP_SEGMENT_TOP)){
                return null;
            }
            else{
                return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
            }
        }
        //checks ship from leftmost cell going right
        if(cell.getType() == SHIP_SEGMENT_LEFT){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            while(orthoAdjCells.get(1).getType == SHIP_SEGMENT_MIDDLE){
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //checks the end of the ship to make sure its the correct segment type
            if(!(orthoAdjCells.get(1).getType()==SHIP_SEGMENT_RIGHT)){
                return null;
            }
            else{
                return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
            }
        }
        //checks ship from rightmost cell going left
        if(cell.getType() == SHIP_SEGMENT_RIGHT){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            while(orthoAdjCells.get(3).getType == SHIP_SEGMENT_MIDDLE){
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //checks the end of the ship to make sure its the correct segment type
            if(!(orthoAdjCells.get(3).getType()==SHIP_SEGMENT_LEFT)){
                return null;
            }
            else{
                return super.getNoContradictionMessage() + ": " + this.NO_CONTRADICTION_MESSAGE;
            }
        }
        
    }
}
