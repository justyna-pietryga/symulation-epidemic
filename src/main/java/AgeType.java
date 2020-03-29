import java.util.Arrays;

public enum AgeType {
    ONE(1, 0, 0, 20, 5),
    TWO(2, 15, 2, 20, 5),
    THREE(3, 15,2, 20, 5),
    FOUR(4, 15,2, 30, 7),
    FIVE(5, 10, 1, 30, 7),
    SIX(6, 10, 1, 50, 15),
    SEVEN(7, 10, 1, 50, 15);

    private final int id;
    private final int naturalIncreaseRate;
    private final int deviatonForIncreaseRateRate;
    private final int mortalityRate;
    private final int deviationForMortalityRate;


    AgeType(int id, int naturalIncreaseRate, int deviatonForIncreaseRateRate, int mortalityRate, int deviationForMortalityRate) {
        this.id = id;
        this.naturalIncreaseRate = naturalIncreaseRate;
        this.deviatonForIncreaseRateRate = deviatonForIncreaseRateRate;
        this.mortalityRate = mortalityRate;
        this.deviationForMortalityRate = deviationForMortalityRate;
    }

    public int getNaturalIncreaseRate() {
        return naturalIncreaseRate;
    }

    public int getDeviatonForIncreaseRateRate() {
        return deviatonForIncreaseRateRate;
    }

    public int getMortalityRate() {
        return mortalityRate;
    }

    public int getDeviationForMortalityRate() {
        return deviationForMortalityRate;
    }

    public static AgeType getAgeTypeById(int id){
        return Arrays.stream(AgeType.values()).filter(ageType -> ageType.getId() == id).findFirst().get();
    }
    public int getId() {
        return id;
    }
}
