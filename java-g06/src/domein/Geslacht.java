package domein;

public enum Geslacht {
    Man(1),
    Vrouw(2);

    private final int val;

    private Geslacht(int val) {
        this.val = val;
    }
}
