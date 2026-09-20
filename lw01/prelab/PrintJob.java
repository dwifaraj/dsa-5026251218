public abstract class PrintJob implements Chargeable {
    protected String id;
    protected int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }

        this.id = id;
        this.pages = pages;
    }

    @Override
    public abstract int calculateCharge();

    @Override
    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be greater than 0");
        }

        return copies * calculateCharge();
    }

    public abstract String label();

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}