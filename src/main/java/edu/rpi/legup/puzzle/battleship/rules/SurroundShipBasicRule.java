package edu.rpi.legup.puzzle.battleship.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.BasicRule;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;

public class SurroundShipBasicRule extends BasicRule {

    public SurroundShipBasicRule() {
        super("Surround Ship",
                "",
                "edu/rpi/legup/images/battleship/rules/SurroundShip.png");
    }

     /**
     * Checks whether the transition has a contradiction at the specific
     * {@link PuzzleElement} index using this rule.
     *
     * @param board         board to check rule
     * @param transition    transition to check
     * @param puzzleElement equivalent {@link PuzzleElement}
     * @return null if logicially follows from parent, otherwise return error message
     */
    @Override
    public String checkRuleRawAt(Board board, PuzzleElement puzzleElement) {
        BattleshipBoard bsBoard = (BattleshipBoard) board;
        BattleshipCell cell = (BattleshipCell) bsBoard.getPuzzleElement(puzzleElement);
        //checks for ships
        if (!BattleshipType.isShip(cell.getType())) {
            return null;
        }
        
        //submarine check
        if(cell.getType() == SHIP_SIZE_1){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            BattleshipCell up = orthoAdjCells.get(0);
            if((up.getType()!=WATER)){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            BattleshipCell right = orthoAdjCells.get(1);
            if((right.getType()!=WATER)){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            BattleshipCell down = orthoAdjCells.get(2);
            if((down.getType()!=WATER)){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            BattleshipCell left = orthoAdjCells.get(3);
            if((left.getType()!=WATER)){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            return null;
        }
        //checks ship from top segment going down
        if(cell.getType() == SHIP_SEGMENT_TOP){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            //checks all left and right adjacent cells to make sure they are surronded by water
            while(orthoAdjCells.get(2).getType == SHIP_SEGMENT_MIDDLE){
                if(orthoAdjCells.get(1)!= WATER){
                    return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
                }
                if(orthoAdjCells.get(3)!= WATER){
                    return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
                }
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //Final ship surround cell check
            if(orthoAdjCells.get(1)!= WATER){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            if(orthoAdjCells.get(3)!= WATER){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            
        }
        //checks ship from leftmost cell going right
        if(cell.getType() == SHIP_SEGMENT_LEFT){
            List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            //checks all up and down adjacent cells to make sure they are surronded by water
            while(orthoAdjCells.get(1).getType == SHIP_SEGMENT_MIDDLE){
                if(orthoAdjCells.get(2)!= WATER){
                    return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
                }
                if(orthoAdjCells.get(0)!= WATER){
                    return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
                }
                List<BattleshipCell> orthoAdjCells= bsBoard.getAdjOrthogonals(cell);
            }
            //Final ship surround cell check
            if(orthoAdjCells.get(2)!= WATER){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
            if(orthoAdjCells.get(0)!= WATER){
                return super.getInvalidUseOfRuleMessage() + ": This ship must be surrounded by water";
            }
        }
        
    }
}

