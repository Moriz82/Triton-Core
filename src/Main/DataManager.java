package Main;

import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataManager {
    public static void SaveData(Main main) {
        try{
            System.out.println("Saving Data");
            File file = new File("TritonCorePlugin\\expMapStorage.dat");
            if (!file.exists()){
                new File("TritonCorePlugin\\").mkdirs();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream("TritonCorePlugin\\expMapStorage.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(main.expMapStorage);
            oos.close();


            file = new File("TritonCorePlugin\\levelSets.json");
            if (!file.exists()){
                file.createNewFile();
            }

            JSONArray jsonArray = new JSONArray();

            for (LevelSet set : main.levelSets) {
                JSONObject obj = new JSONObject();
                obj.put("helmet", set.helmet.getType().name());
                obj.put("chestPlate", set.chestPlate.getType().name());
                obj.put("leggings", set.leggings.getType().name());
                obj.put("boots", set.boots.getType().name());
                obj.put("sword", set.sword.getType().name());
                obj.put("axe", set.axe.getType().name());
                obj.put("xpRequired", set.xpRequired);
                jsonArray.add(obj);
            }
            FileWriter myWriter = new FileWriter("TritonCorePlugin\\levelSets.json");
            myWriter.flush();
            myWriter.write(jsonArray.toJSONString());
            myWriter.close();
        }
        catch (Exception ev){
            ev.printStackTrace();
        }
    }
    public static void LoadData(Main main) {
        try{
            System.out.println("Loading Data");
            File file = new File("TritonCorePlugin\\expMapStorage.dat");
            if (!file.exists()){return;}
            FileInputStream fin = new FileInputStream("TritonCorePlugin\\expMapStorage.dat");
            ObjectInputStream ois = new ObjectInputStream(fin);
            main.expMapStorage = (Map<String, Double>) ois.readObject();
            ois.close();


            file = new File("TritonCorePlugin\\levelSets.json");
            if (!file.exists()){return;}

            List<LevelSet> levelSets = new ArrayList<>();

            String data = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONArray jsonArray = (JSONArray)obj;

            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                levelSets.add(new LevelSetBuilder(
                        (String) jsonObject.get("helmet"),
                        (String) jsonObject.get("chestPlate"),
                        (String) jsonObject.get("leggings"),
                        (String) jsonObject.get("boots"),
                        (String) jsonObject.get("sword"),
                        (String) jsonObject.get("axe"),
                        (Double) jsonObject.get("xpRequired")
                ).build());
            }

            main.levelSets = levelSets;
        }
        catch (Exception ev){
            ev.printStackTrace();
        }
    }
}
