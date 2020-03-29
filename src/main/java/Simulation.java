import java.util.*;

public class Simulation {

    private Map<Integer, LinkedList<Integer>> resultForCSV = new LinkedHashMap<>();

    public Map<Integer, LinkedList<Integer>> startSimulation() throws Exception {
        int i = 0;
        addToResultReport(i);
        do {
            calculateOneSimulationStep(i);
            i++;
        } while(!SimulationSuppliers.isZeroNewInfections());
        calculateOneSimulationStep(i++);

        System.out.println("Brak nowych zakażonych po " + i + " umownych jednostkach czasu");
        System.out.println("Liczba ocalałych: " + SimulationSuppliers.getAllAnimals());

        return resultForCSV;
    }

    public void calculateOneSimulationStep(int i) throws Exception {
        System.out.println((i + 1) + " etap");

        Map<AgeType, DataForOneAgeOfAnimals> tmp = copy(SimulationSuppliers.currentSituation);
        double probabilityOfInfection = (double) SimulationSuppliers.getAllSickAnimals() / SimulationSuppliers.getAllAnimals();

        for (AgeType ageType : AgeType.values()) {
            SimulationSuppliers.resultOfCalculationsMap
                    .put(ageType, SimulationSuppliers.currentSituation.get(ageType).makeCalculations(probabilityOfInfection));
        }

        calculateOneSimulationStepForFirstAgeOnly();
        for(int j=2; j<=7; j++){
            calculateOneSimulationStepForOneOfAnimalAge(tmp, j);
        }

        addToResultReport(i + 1);
        printResult();
        System.out.println("_____________________________________");
    }

    private void addToResultReport(int step){
        int healthy = SimulationSuppliers.getAllHealthy();
        int resistant = SimulationSuppliers.getAllResistant();
        int sickFirst = SimulationSuppliers.getAllSickInFirstPhase();
        int sickSecond = SimulationSuppliers.getAllSickInSecondPhase();
        int allPopulation = SimulationSuppliers.getAllAnimals();
        int allSick = SimulationSuppliers.getAllSickAnimals();

        resultForCSV.put(step, CsvGenerator.getRecordList(healthy, resistant, sickFirst, sickSecond, allPopulation, allSick));
    }

    private void printResult(){
        SimulationSuppliers.currentSituation.values().stream()
                .sorted(Comparator.comparing(a -> a.getKindOfAnimal().getId()))
                .forEach(age -> {
            String result = "Age: " + age.getKindOfAnimal().getId() + "  Zdrowych: " + age.getAmountOfHealthy() +
                    "   W tym odpornych: " + age.getAmountOfResistant() + "  Chorych: " + age.getAmountOfSickAnimals() +
                    "(1 faza: " + age.getAmountOfFirstPhaseSick() + ", 2 faza: " + age.getAmountOfSecondPhaseSick() + ")";

            System.out.println(result);
        });
    }

    /**
     * That is for AGE 1, because here is a bit different approach
     */

    public void calculateOneSimulationStepForFirstAgeOnly() {
        int newPopulation = SimulationSuppliers.getNewPopulation();
        int amountOfSickInFirstPhaseForNewPopulation = SimulationSuppliers.getAmountOfSickInFirstPhaseForNewPopulation();
        int amountOfHealthyInNewPopulation = newPopulation - amountOfSickInFirstPhaseForNewPopulation;

        SimulationSuppliers.currentSituation.get(AgeType.ONE)
                .setNewData(amountOfHealthyInNewPopulation, 0, amountOfSickInFirstPhaseForNewPopulation, 0);
    }

    /**
     * That is for AGE 2-7 (u.j.c)
     *
     * @param current  - current situation of population
//     * @param previous - Age enum for previous age
//     * @param next     - Age enum for next enum (for which we want to make calculations)
     */
    public void calculateOneSimulationStepForOneOfAnimalAge(Map<AgeType, DataForOneAgeOfAnimals> current, int ageType)
            throws Exception {

        if (ageType > 7 || ageType < 2) throw new Exception("Wrong age value");
        AgeType next = AgeType.getAgeTypeById(ageType);
        AgeType previous = AgeType.getAgeTypeById(ageType - 1);

        ResultOfCalculations result = SimulationSuppliers.resultOfCalculationsMap.get(previous);
        DataForOneAgeOfAnimals previousSituation = current.get(previous);

        int newHealthy = previousSituation.getAmountOfHealthy();
        int recovered = previousSituation.getAmountOfSecondPhaseSick() - result.getDeathCases();
        int morbidity = result.getMorbidity();
        int firstPhase = previousSituation.getAmountOfFirstPhaseSick();
        SimulationSuppliers.currentSituation.get(next)
                .setNewData(newHealthy + recovered - morbidity, recovered, morbidity, firstPhase);
    }

    public static HashMap<AgeType, DataForOneAgeOfAnimals> copy(Map<AgeType, DataForOneAgeOfAnimals> original) {
        HashMap<AgeType, DataForOneAgeOfAnimals> copy = new HashMap<>();
        for (Map.Entry<AgeType, DataForOneAgeOfAnimals> entry : original.entrySet()) {
            copy.put(entry.getKey(), new DataForOneAgeOfAnimals(entry.getValue()));
        }
        return copy;
    }
}
