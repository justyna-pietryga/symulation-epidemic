public class ResultOfCalculations {
    private int newPopulation;
    private int amountOfSickInNewPopulation;
    private int morbidity;
    private int deathCases;


    public ResultOfCalculations(int newPopulation, int amountOfSickInNewPopulation, int morbidity, int deathCases) {
        this.newPopulation = newPopulation;
        this.amountOfSickInNewPopulation = amountOfSickInNewPopulation;
        this.morbidity = morbidity;
        this.deathCases = deathCases;
    }

    public int getNewPopulation() {
        return newPopulation;
    }

    public int getAmountOfSickInNewPopulation() {
        return amountOfSickInNewPopulation;
    }

    public int getMorbidity() {
        return morbidity;
    }

    public int getDeathCases() {
        return deathCases;
    }
}
