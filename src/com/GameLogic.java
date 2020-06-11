package com;

import com.sun.tools.javac.Main;
import lombok.Getter;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class GameLogic implements Serializable {
    @Getter
    public static final int COLUMNS = 4;
    @Getter
    public static final int ROWS = 4;
    private int[][] grid;
    @Getter
    private int score = 0;

    public void setScore(int score) {
        this.score = this.score + score;
    }
    public GameLogic(){
        grid = new int[getCOLUMNS()][getROWS()];
        for (int col = 0; col < getCOLUMNS(); col++){
            for (int row = 0; row < getROWS(); row++){
                grid[col][row] = 0;
            }
        }
    }


    public int getTileValue(int col, int row){
        return grid[col][row];
    }

    public void setTileValue(int col, int row, int value){
        grid[col][row] = value;
    }
    //Generating tiles on board
    public boolean generateTile(){
        int col;
        int row;
        if (isGridFull()){
            return false;
        }
        do{
            col = new Random().nextInt(getCOLUMNS()) ;
            row = new Random().nextInt(getROWS());
        }while (grid[col][row] != 0);

        if((new Random().nextInt() * 9) % 2 == 0){
            grid[col][row] = 2;
        }
        else
            grid[col][row] = 4;

        return true;
    }
    //Checks if board is full(no space left to generate new tile)
    public boolean isGridFull(){
        for (int col = 0; col < getCOLUMNS(); col++){
            for (int row = 0; row < getROWS(); row++){
                if(grid[col][row] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    //Checks if there is a possible move left on the grid
    public boolean canPlay(){
        if(!isGridFull())
            return true;
        for (int col = 0; col < getCOLUMNS(); col++){
            for (int row = 0; row < getROWS(); row++){
                if(col == 0 && row == 0){
                    if(grid[col][row] == grid[col][row+1]
                            || grid[col][row] == grid[col+1][row])
                        return true;
                }
                else if (col == 0 && row == getROWS() - 1){
                    if(grid[col][row] == grid[col+1][row]
                        || grid[col][row] == grid[col][row-1])
                        return true;
                }
                else if (col == getCOLUMNS() - 1 && row == 0){
                    if(grid[col][row] == grid[col-1][row]
                            || grid[col][row] == grid[col][row+1])
                        return true;
                }
                else if (col == getCOLUMNS() - 1 && row == getROWS() - 1){
                    if(grid[col][row] == grid[col-1][row]
                            || grid[col][row] == grid[col][row-1])
                        return true;
                }
                else if (col == 0){
                    if(grid[col][row] == grid[col+1][row]
                            || grid[col][row] == grid[col][row-1]
                            || grid[col][row] == grid[col][row+1])
                        return true;
                }
                else if (col == getCOLUMNS() - 1){
                    if(grid[col][row] == grid[col-1][row]
                            || grid[col][row] == grid[col][row-1]
                            || grid[col][row] == grid[col][row+1])
                        return true;
                }
                else if (row == 0){
                    if(grid[col][row] == grid[col-1][row]
                            || grid[col][row] == grid[col+1][row]
                            || grid[col][row] == grid[col][row+1])
                        return true;
                }
                else if (row == getROWS() - 1){
                    if(grid[col][row] == grid[col-1][row]
                            || grid[col][row] == grid[col+1][row]
                            || grid[col][row] == grid[col][row-1])
                        return true;
                }
                else{
                    if(grid[col][row] == grid[col][row+1]
                            || grid[col][row] == grid[col][row-1]
                            || grid[col][row] == grid[col+1][row]
                            || grid[col][row] == grid[col-1][row]){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Move tiles to the left
     * If two tiles with the same value are together, value will be timed by 2
     * Empty tiles will be filled with 0(no text on GUI)
     */
    public void goUp(){
        int resCol;
        for (int row = 0; row < getROWS(); row++){
            resCol = 0;
            for (int col = 1; col < getCOLUMNS(); col++){
                if(resCol == col || grid[col][row] == 0){
                    continue;
                }
                else if (grid[resCol][row] == grid[col][row]){
                    //Merge and increase of the value
                    grid[resCol][row] = grid[resCol][row] * 2;
                    //update score
                    setScore(getTileValue(resCol,row));
                    grid[col][row] = 0;
                    resCol++;
                }
                else {
                    if(grid[resCol][row] != 0)
                        resCol++;
                    if(resCol != col){
                        grid[resCol][row] = grid[col][row];
                        grid[col][row] = 0;
                    }
                }
            }
        }
    }
    /**
     * Move tiles to the right
     * If two tiles with the same value are together, value will be timed by 2
     * Empty tiles will be filled with 0(no text on GUI)
     */
    public void goDown(){
        int resCol;
        for (int row = 0; row < getROWS(); row++){
            resCol = getCOLUMNS() - 1;
            for (int col = getCOLUMNS() - 1; col >= 0 ; col--){
                if(resCol == col || grid[col][row] == 0){
                    continue;
                }
                else if (grid[resCol][row] == grid[col][row]){
                    //Merge and increase of the value
                    grid[resCol][row] = grid[resCol][row] * 2;
                    //update score
                    setScore(getTileValue(resCol,row));
                    grid[col][row] = 0;
                    resCol--;
                }
                else {
                    if(grid[resCol][row] != 0)
                        resCol--;
                    if(resCol != col){
                        grid[resCol][row] = grid[col][row];
                        grid[col][row] = 0;
                    }
                }
            }
        }
    }
    /**
     * Move tiles up
     * If two tiles with the same value are together, value will be timed by 2
     * Empty tiles will be filled with 0(no text on GUI)
     */
    public void goLeft(){
        int resRow;
        for (int col = 0; col < getCOLUMNS(); col++){
            resRow = 0;
            for (int row = 1; row < getROWS(); row++){
                if(resRow == row || grid[col][row] == 0){
                    continue;
                }
                else if (grid[col][resRow] == grid[col][row]){
                    //Merge and increase of the value
                    grid[col][resRow] = grid[col][resRow] * 2;
                    //update score
                    setScore(getTileValue(col,resRow));
                    grid[col][row] = 0;
                    resRow++;
                }
                else {
                    if(grid[col][resRow] != 0)
                        resRow++;
                    if(resRow != row){
                        grid[col][resRow] = grid[col][row];
                        grid[col][row] = 0;
                    }
                }
            }
        }
    }
    /**
     * Move tiles down
     * If two tiles with the same value are together, value will be timed by 2
     * Empty tiles will be filled with 0(no text on GUI)
     */
    public void goRight(){
        int resRow;
        for (int col = 0; col < getCOLUMNS(); col++){
            resRow = getROWS() - 1;
            for (int row = getROWS() - 1; row >= 0; row--){
                if(resRow == row || grid[col][row] == 0){
                    continue;
                }
                else if (grid[col][resRow] == grid[col][row]){
                    //Merge and increase of the value
                    grid[col][resRow] = grid[col][resRow] * 2;
                    //update score
                    setScore(getTileValue(col,resRow));
                    grid[col][row] = 0;
                    resRow--;
                }
                else {
                    if(grid[col][resRow] != 0)
                        resRow--;
                    if(resRow != row){
                        grid[col][resRow] = grid[col][row];
                        grid[col][row] = 0;
                    }
                }
            }
        }
    }
}
