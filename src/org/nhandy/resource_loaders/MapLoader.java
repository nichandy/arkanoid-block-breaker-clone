package org.nhandy.resource_loaders;

import org.nhandy.GameWorld;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.Drawable;
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


