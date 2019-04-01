package domein;

public enum TypeGebruiker {
    Beheerder(1),
    Lid(2),
    Proefgebruiker(3);
    
    private final int val;
    
    private TypeGebruiker(int val){
        this.val = val;
    }
}
