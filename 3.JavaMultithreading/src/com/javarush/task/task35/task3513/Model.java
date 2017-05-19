package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rumata on 18.05.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
        this.score = 0;
        this.maxTile = 2;
    }

    List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) result.add(gameTiles[i][j]);
            }
        }
        return result;
    }

    void addTile() {
        List<Tile> list = getEmptyTiles();
        list.get((int) (list.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
    }

    void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private Tile[] compressTiles(Tile[] tiles) {
        Tile temp;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                }
            }
        }
        return tiles;
    }

    private Tile[] mergeTiles(Tile[] tiles) {

            for (int j = 0; j < 3; j++) {
                if (tiles[j].getValue() != 0 && tiles[j].getValue() == tiles[j + 1].getValue()) {
                    tiles[j].setValue(tiles[j].getValue() *2);
                    tiles[j+1].setValue(0);
                    if (tiles[j].getValue() > maxTile) maxTile = tiles[j].getValue();
                    score+= tiles[j].getValue();
                    tiles = compressTiles(tiles);
                }
            }


        return tiles;
    }
}
