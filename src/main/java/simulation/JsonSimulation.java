package simulation;

import codeamon.CodeamonFactory;
import codeamon.Type;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import trainer.Trainer;

/**
 * Contains the Data for a Codeamon Simulation that is read from a .json file. In simulation_one,
 * each Trainer will begin with a single level 10 Codeamon and battle against 5 Wild Codeamon that
 * start at level 8. In simulation_two, each Trainer will begin with a full team of 6 level 100
 * Codeamon. They will not battle against any Wild Codeamon, and instead go right into the
 * Tournament.
 */
public class JsonSimulation implements Simulation {
    private ArrayList<Trainer> trainers;
    private int wildLevel;
    private int wildBattles;

    /**
     * Sets the the data for the simulation by reading a .json file.
     *
     * @param file The .json file that has the simulation data
     */
    public JsonSimulation(String file) {
        //default values in case an exception is thrown
        trainers = new ArrayList<>();
        wildBattles = 0;
        wildLevel = 0;
        FileReader reader = null;

        try {
            reader = new FileReader(file, Charset.forName("UTF-8"));
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(reader);

            wildLevel = ((Long) obj.get("wildLevel")).intValue();
            wildBattles = ((Long) obj.get("wildBattles")).intValue();

            JSONArray arr = (JSONArray) obj.get("trainers");

            for (int i = 0; i < arr.size(); i++) {
                JSONObject jsonTrainer = (JSONObject) arr.get(i);

                JSONArray monArr = (JSONArray) jsonTrainer.get("codeamon");
                Trainer trainer = new Trainer.TrainerBuilder((String) jsonTrainer.get("name"))
                        .build();

                for (int j = 0; j < monArr.size(); j++) {
                    JSONObject mon = (JSONObject) monArr.get(j);
                    trainer.addCodeamon(CodeamonFactory
                            .createCodeamon(Type.valueOf((String) mon.get("type")),
                                    ((Long) mon.get("level")).intValue()));
                }

                trainers.add(trainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    @Override
    public int getWildLevel() {
        return wildLevel;
    }

    @Override
    public int getWildBattles() {
        return wildBattles;
    }
}
