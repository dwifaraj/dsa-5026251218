package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;
    private int units;

    public WashService(String id, int days, int units) {
        if (days <= 0 || units <= 0) {
            throw new IllegalArgumentException("Days and units must be greater than 0");
        }

        this.id = id;
        this.days = days;
        this.units = units;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public abstract int calculateCharge();

    @Override
    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than 0");
        }

        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge(units);
    }
}