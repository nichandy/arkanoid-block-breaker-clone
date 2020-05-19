package org.nhandy.resource_loaders;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.Drawable;
import org.nhandy.gameobjects.movable.blocks.BreakBlock;
import org.nhandy.gameobjects.stationary.walls.unBreakWall;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MapLoader {

    public MapLoader() {}

    public void loadMap(List<Drawable> drawables, List<Collidable> collidables) {
        try {

            InputStreamReader isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/test-map.csv"));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null) {
                throw new IOException("no data in file");
            }
            String[] mapInfo = row.split(",");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split(",");

                for(int curCol = 0; curCol < numCols; curCol++) {
                    switch(mapInfo[curCol]) {
                        // TODO: Replace with new spritesheet and map images
                        case "2":
//                            BreakWall br = new BreakWall(curCol*32, curRow*32, Resource.getResourceImage("breakWall"));
//                            drawables.add(br);
//                            collidables.add(br);
//                            break;
                        case "3":
//                            HealthPowerUp hp = new HealthPowerUp(curCol*32, curRow*32, Resource.getResourceImage("healthPowerUp"));
//                            gameObjects.add(hp);
//                            break;
                        case "4":
//                            WeaponPowerUp wp = new WeaponPowerUp(curCol*32, curRow*32, Resource.getResourceImage("weaponPowerUp"));
//                            gameObjects.add(wp);
//                            break;
                        case "5":
//                            ShieldPowerUp sp = new ShieldPowerUp(curCol*32, curRow*32, Resource.getResourceImage("shieldPowerUp"));
//                            gameObjects.add(sp);
//                            break;
                        case "BW":  BreakBlock blockWhite = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.WHITE, Resource.getResourceImage("defaultBlockWhite"));
                                    drawables.add(blockWhite);
                                    collidables.add(blockWhite);
                                    break;
                        case "BO":  BreakBlock blockOrange = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.ORANGE, Resource.getResourceImage("defaultBlockOrange"));
                                    drawables.add(blockOrange);
                                    collidables.add(blockOrange);
                                    break;
                        case "BC":  BreakBlock blockCyan = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.CYAN, Resource.getResourceImage("defaultBlockCyan"));
                                    drawables.add(blockCyan);
                                    collidables.add(blockCyan);
                                    break;
                        case "BG":  BreakBlock blockGreen = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.GREEN, Resource.getResourceImage("defaultBlockGreen"));
                                    drawables.add(blockGreen);
                                    collidables.add(blockGreen);
                                    break;
                        case "BR":  BreakBlock blockRed = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.RED, Resource.getResourceImage("defaultBlockRed"));
                                    drawables.add(blockRed);
                                    collidables.add(blockRed);
                                    break;
                        case "BB":  BreakBlock blockBlue = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.BLUE, Resource.getResourceImage("defaultBlockBlue"));
                                    drawables.add(blockBlue);
                                    collidables.add(blockBlue);
                                    break;
                        case "BP":  BreakBlock blockPink = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.PINK, Resource.getResourceImage("defaultBlockPink"));
                                    drawables.add(blockPink);
                                    collidables.add(blockPink);
                                    break;
                        case "BY":  BreakBlock blockYellow = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.YELLOW, Resource.getResourceImage("defaultBlockYellow"));
                                    drawables.add(blockYellow);
                                    collidables.add(blockYellow);
                                    break;
                        case "BAU": BreakBlock blockGold = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.GOLD, Resource.getResourceImage("defaultBlockGold1A"));
                                    drawables.add(blockGold);
                                    collidables.add(blockGold);
                                    break;
                        case "BAG": BreakBlock blockSilver = new BreakBlock(curCol*8, curRow*8, GameConstants.BLOCK_TYPE.SILVER, Resource.getResourceImage("defaultBlockSilver1A"));
                                    drawables.add(blockSilver);
                                    collidables.add(blockSilver);
                                    break;
                        case "WT":
                            unBreakWall ubWallTop = new unBreakWall(curCol*8, curRow*8, Resource.getResourceImage("unBreakWallTop"));
                            drawables.add(ubWallTop);
                            collidables.add(ubWallTop);
                            break;
                        case "WL":
                            unBreakWall ubWallLeft = new unBreakWall(curCol*8, curRow*8, Resource.getResourceImage("unBreakWallLeft"));
                            drawables.add(ubWallLeft);
                            collidables.add(ubWallLeft);
                            break;
                        case "WR":
                            unBreakWall ubWallRight = new unBreakWall(curCol*8, curRow*8, Resource.getResourceImage("unBreakWallRight"));
                            drawables.add(ubWallRight);
                            collidables.add(ubWallRight);
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


