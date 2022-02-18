package Main.EventManagers;

import Main.Main;
import Main.Util.LevelSet;
import Main.Util.LevelSetBuilder;
import Main.Util.PlayerData;
import Main.Util.PlayerDataBuilder;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    public static void SaveData(Main main) {
        try{
            System.out.println("Saving Data");
            File file = new File("TritonCorePlugin/PlayerData.dat");
            if (!file.exists()){
                new File("TritonCorePlugin").mkdirs();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream("TritonCorePlugin/PlayerData.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Map<String, PlayerDataBuilder> playerDataStorage = new HashMap<>();

            for (Player player : main.playerData.keySet()) {
                PlayerData playerData = main.playerData.get(player);
                playerDataStorage.put(player.getDisplayName(), new PlayerDataBuilder(
                        playerData.xp, playerData.kills, playerData.deaths, playerData.killStreak, playerData.longestKillStreak,
                        playerData.xpMultiplier, playerData.levelSet.name));
            }

            oos.writeObject(playerDataStorage);
            oos.close();

            file = new File("TritonCorePlugin/levelSets.json");
            if (!file.exists()){
                file.createNewFile();
            }

            /*JSONArray jsonArray = new JSONArray();

            for (LevelSet set : main.levelSets) {
                JSONObject obj = new JSONObject();
                obj.put("helmet", set.helmet.getType().name());
                obj.put("chestPlate", set.chestPlate.getType().name());
                obj.put("leggings", set.leggings.getType().name());
                obj.put("boots", set.boots.getType().name());
                obj.put("sword", set.sword.getType().name());
                obj.put("axe", set.axe.getType().name());
                obj.put("xpRequired", set.xpRequired);

                JSONObject object = new JSONObject();
                for (Enchantment enchantment : set.helmet.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.helmet.getEnchantments().get(enchantment.getName()));
                obj.put("helmetEnchants", object);

                object = new JSONObject();
                for (Enchantment enchantment : set.chestPlate.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.chestPlate.getEnchantments().get(enchantment.getName()));
                obj.put("chestPlateEnchants", object);

                object = new JSONObject();
                for (Enchantment enchantment : set.leggings.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.leggings.getEnchantments().get(enchantment.getName()));
                obj.put("leggingsEnchants", object);

                object = new JSONObject();
                for (Enchantment enchantment : set.boots.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.boots.getEnchantments().get(enchantment.getName()));
                obj.put("bootsEnchants", object);

                object = new JSONObject();
                for (Enchantment enchantment : set.sword.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.sword.getEnchantments().get(enchantment.getName()));
                obj.put("swordEnchants", object);

                object = new JSONObject();
                for (Enchantment enchantment : set.axe.getEnchantments().keySet())
                    object.put(enchantment.getName(), set.axe.getEnchantments().get(enchantment.getName()));
                obj.put("axeEnchants", object);


                jsonArray.add(obj);
            }
            FileWriter myWriter = new FileWriter("TritonCorePlugin/levelSets.json");
            myWriter.flush();
            myWriter.write(jsonArray.toJSONString());
            myWriter.close();*/
        }
        catch (Exception ev){
            ev.printStackTrace();
        }
    }
    public static void LoadData(Main main) {
        try{
            System.out.println("Loading Data");


            File file = new File("TritonCorePlugin/levelSets.json");
            if (!file.exists()){return;}

            List<LevelSet> levelSets = new ArrayList<>();

            String data = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONArray jsonArray = (JSONArray)obj;

            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                JSONObject helmetEnchantsOBJ = (JSONObject) (jsonObject.get("helmetEnchants"));
                JSONObject chestPlateEnchantsOBJ = (JSONObject) (jsonObject.get("chestPlateEnchants"));
                JSONObject leggingsEnchantsOBJ = (JSONObject) (jsonObject.get("leggingsEnchants"));
                JSONObject bootsEnchantsOBJ = (JSONObject) (jsonObject.get("bootsEnchants"));
                JSONObject swordEnchantsOBJ = (JSONObject) (jsonObject.get("swordEnchants"));
                JSONObject axeEnchantsOBJ = (JSONObject) (jsonObject.get("axeEnchants"));
                List<String> helmetEnchants = new ArrayList<>();
                List<String> chestPlateEnchants = new ArrayList<>();
                List<String> leggingsEnchants = new ArrayList<>();
                List<String> bootsEnchants = new ArrayList<>();
                List<String> swordEnchants = new ArrayList<>();
                List<String> axeEnchants = new ArrayList<>();
                List<Integer> helmetEnchantsLevels = new ArrayList<>();
                List<Integer> chestPlateEnchantsLevels = new ArrayList<>();
                List<Integer> leggingsEnchantsLevels = new ArrayList<>();
                List<Integer> bootsEnchantsLevels = new ArrayList<>();
                List<Integer> swordEnchantsLevels = new ArrayList<>();
                List<Integer> axeEnchantsLevels = new ArrayList<>();
                for (Object o : helmetEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    helmetEnchants.add(str);
                    helmetEnchantsLevels.add(Integer.parseInt(((Long)helmetEnchantsOBJ.get(str)).toString()));
                }
                for (Object o : chestPlateEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    chestPlateEnchants.add(str);
                    chestPlateEnchantsLevels.add(Integer.parseInt(((Long)chestPlateEnchantsOBJ.get(str)).toString()));
                }
                for (Object o : leggingsEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    leggingsEnchants.add(str);
                    leggingsEnchantsLevels.add(Integer.parseInt(((Long)leggingsEnchantsOBJ.get(str)).toString()));
                }
                for (Object o : bootsEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    bootsEnchants.add(str);
                    bootsEnchantsLevels.add(Integer.parseInt(((Long)bootsEnchantsOBJ.get(str)).toString()));
                }
                for (Object o : swordEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    swordEnchants.add(str);
                    swordEnchantsLevels.add(Integer.parseInt(((Long)swordEnchantsOBJ.get(str)).toString()));
                }
                for (Object o : axeEnchantsOBJ.keySet()) {
                    String str = (String) o;
                    axeEnchants.add(str);
                    axeEnchantsLevels.add(Integer.parseInt(((Long)axeEnchantsOBJ.get(str)).toString()));
                }

                levelSets.add(new LevelSetBuilder(
                        (String) jsonObject.get("helmet"),
                        (String) jsonObject.get("chestPlate"),
                        (String) jsonObject.get("leggings"),
                        (String) jsonObject.get("boots"),
                        (String) jsonObject.get("sword"),
                        (String) jsonObject.get("axe"),
                        Integer.parseInt(((Long)jsonObject.get("xpRequired")).toString()),
                        helmetEnchants.toArray(new String[helmetEnchants.size()]),
                        chestPlateEnchants.toArray(new String[chestPlateEnchants.size()]),
                        leggingsEnchants.toArray(new String[leggingsEnchants.size()]),
                        bootsEnchants.toArray(new String[bootsEnchants.size()]),
                        swordEnchants.toArray(new String[swordEnchants.size()]),
                        axeEnchants.toArray(new String[axeEnchants.size()]),
                        helmetEnchantsLevels.toArray(new Integer[helmetEnchantsLevels.size()]),
                        chestPlateEnchantsLevels.toArray(new Integer[chestPlateEnchantsLevels.size()]),
                        leggingsEnchantsLevels.toArray(new Integer[leggingsEnchantsLevels.size()]),
                        bootsEnchantsLevels.toArray(new Integer[bootsEnchantsLevels.size()]),
                        swordEnchantsLevels.toArray(new Integer[swordEnchantsLevels.size()]),
                        axeEnchantsLevels.toArray(new Integer[axeEnchantsLevels.size()]),
                        (String) jsonObject.get("name")
                ).build());
            }

            main.levelSets = levelSets;
            System.out.println(levelSets);

            file = new File("TritonCorePlugin/PlayerData.dat");
            if (file.exists()){
                FileInputStream fin = new FileInputStream("TritonCorePlugin/PlayerData.dat");
                ObjectInputStream ois = new ObjectInputStream(fin);
                Map<String, PlayerDataBuilder> playerDataStorage = (Map<String, PlayerDataBuilder>) ois.readObject();

                for (String name : playerDataStorage.keySet()) {
                    PlayerDataBuilder builder = playerDataStorage.get(name);
                    LevelSet set = null;
                    for (LevelSet levelSet : main.levelSets){
                        if (levelSet.name.equals(name)){
                            set = levelSet;
                            break;
                        }
                    }
                    main.playerDataStorage.put(name, new PlayerData(
                            builder.xp, builder.kills, builder.deaths, builder.killStreak, builder.longestKillStreak,
                            builder.xpMultiplier, set
                    ));
                }

                ois.close();
            }

        }
        catch (Exception ev){
            ev.printStackTrace();
        }


    }
}
