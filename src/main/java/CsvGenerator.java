import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;


public class CsvGenerator {
    static String[] HEADERS = {"step", "zdrowi", "odporni", "chorzy - faza 1", "chorzy - faza 2", "cała populacja", "chorzy ogółem"};

    public static LinkedList<Integer> getRecordList(int healthy, int resistant, int sickFirst, int sickSecond, int allPopulation, int allSickness) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(healthy);
        list.add(resistant);
        list.add(sickFirst);
        list.add(sickSecond);
        list.add(allPopulation);
        list.add(allSickness);

        return list;
    }

    public static void createCSVFile(Map<Integer, LinkedList<Integer>> DATA) throws IOException {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        FileWriter out = new FileWriter("result" + ts + ".csv");

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            DATA.forEach((step, list) -> {
                try {
                    printer.printRecord(step, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
