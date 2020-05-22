package com.program;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TilesMatrix {
    private final int[][] tileMatrix = new int[4][4];
    @Getter
    private int score;
    private int row;
    private int column;
    public boolean gameOver = false;
    public boolean gameWon = false;
    private List<Tile> tiles = new ArrayList<>();

    //Create matrix without values so it can be modified later
    public void createMatrix(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; i++){
                tileMatrix[i][j] = 0;
            }
        }
    }
    //Constructor to create tiles and matrix for them
    TilesMatrix(List<Tile> Tiles){
        tiles = Tiles;
        createMatrix();
    }

    //Creating new tile after move
    private void createNewTile(){

    }

    //Method to return score based on moves
    public String getScore(){
        return String.valueOf(this.getScore());
    }

    private void check_down(int column, int row1, int row2){
        if(tileMatrix[row1][column] == tileMatrix[row2][column]){
            tileMatrix[row2][column] = tileMatrix[row2][column] + tileMatrix[row1][column];
        }
    }
    private void check_up(int column, int row1, int row2){
        //if(tileMatrix)
    }
}
