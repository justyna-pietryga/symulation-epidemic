import java.util.HashMap;
import java.util.Map;

public class SimulationSuppliers {
    public static Map<AgeType, DataForOneAgeOfAnimals> currentSituation = new HashMap<>();
    public static Map<AgeType, ResultOfCalculations> resultOfCalculationsMap = new HashMap<>();

    public static void fillWithInitialData(){
        currentSituation.put(AgeType.ONE, new DataForOneAgeOfAnimals(AgeType.ONE, 80, 20, 10, 10));
        currentSituation.put(AgeType.TWO, new DataForOneAgeOfAnimals(AgeType.TWO, 90, 30, 10, 20));
        currentSituation.put(AgeType.THREE, new DataForOneAgeOfAnimals(AgeType.THREE, 80, 10, 5, 10));
        currentSituation.put(AgeType.FOUR, new DataForOneAgeOfAnimals(AgeType.FOUR, 70, 10, 10, 5));
        currentSituation.put(AgeType.FIVE, new DataForOneAgeOfAnimals(AgeType.FIVE, 40, 20, 7, 3));
        currentSituation.put(AgeType.SIX, new DataForOneAgeOfAnimals(AgeType.SIX, 15, 5, 6, 4));
        currentSituation.put(AgeType.SEVEN, new DataForOneAgeOfAnimals(AgeType.SEVEN, 10, 0, 0, 3));
    }

    public static int getAmountOfSickInFirstPhaseForNewPopulation() {
        return resultOfCalculationsMap.values().stream()
                .mapToInt(ResultOfCalculations::getAmountOfSickInNewPopulation)
                .sum();
    }

    public static int getNewPopulation() {
        return resultOfCalculationsMap.values().stream()
                .mapToInt(ResultOfCalculations::getNewPopulation)
                .sum();
    }

    public static int getAllSickAnimals() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAmountOfSickAnimals)
                .sum();
    }

    public static int getAllAnimals() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAllAnimalsOfThisAge)
                .sum();
    }

    public static int getAllHealthy() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAmountOfHealthy)
                .sum();
    }

    public static int getAllResistant() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAmountOfResistant)
                .sum();
    }

    public static int getAllSickInFirstPhase() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAmountOfFirstPhaseSick)
                .sum();
    }

    public static int getAllSickInSecondPhase() {
        return SimulationSuppliers.currentSituation.values().stream()
                .mapToInt(DataForOneAgeOfAnimals::getAmountOfSecondPhaseSick)
                .sum();
    }

    public static boolean isZeroNewInfections(){
        return SimulationSuppliers.resultOfCalculationsMap.values().stream()
                .mapToInt(ResultOfCalculations::getMorbidity)
                .sum() <= 0;
    }
}
