package domain.GebruikerModels;

public enum Geslacht {
    Onbekend(0),
    Man(1),
    Vrouw(2);

    private final int val;

    private Geslacht(int val) {
        this.val = val;
    }
}
