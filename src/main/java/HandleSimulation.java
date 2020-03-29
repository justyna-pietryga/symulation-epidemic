import java.util.LinkedList;
import java.util.Map;

public class HandleSimulation {
    public static void main(String[] args) throws Exception {
        Simulation simulation = new Simulation();
        SimulationSuppliers.fillWithInitialData();
        Map<Integer, LinkedList<Integer>> resultForCSV  = simulation.startSimulation();
        CsvGenerator.createCSVFile(resultForCSV);
    }
}
