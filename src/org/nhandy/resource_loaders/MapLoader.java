package org.nhandy.resource_loaders;

import org.nhandy.GameWorld;
import org.nhandy.gameobjects.GameObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapLoader {

    public MapLoader() {}

    public void loadMap(ArrayList<GameObject> gameObjects) {
        try {

            InputStreamReader isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/map-1.csv"));
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
//                        case "2":
//                            BreakWall br = new BreakWall(curCol*32, curRow*32, Resource.getResourceImage("breakWall"));
//                            gameObjects.add(br);
//                            break;
//                        case "3":
//                            HealthPowerUp hp = new HealthPowerUp(curCol*32, curRow*32, Resource.getResourceImage("healthPowerUp"));
//                            gameObjects.add(hp);
//                            break;
//                        case "4":
//                            WeaponPowerUp wp = new WeaponPowerUp(curCol*32, curRow*32, Resource.getResourceImage("weaponPowerUp"));
//                            gameObjects.add(wp);
//                            break;
//                        case "5":
//                            ShieldPowerUp sp = new ShieldPowerUp(curCol*32, curRow*32, Resource.getResourceImage("shieldPowerUp"));
//                            gameObjects.add(sp);
//                            break;
//                        case "9":
//                            unBreakWall ubr = new unBreakWall(curCol*32, curRow*32, Resource.getResourceImage("unBreakWall"));
//                            gameObjects.add(ubr);
//                            break;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


