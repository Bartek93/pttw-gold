/**
 * Created by Bartolio on 31.10.2016.
 */
public class Bit {
    //BIT_0(0),
    //BIT_1(1);

    private int value;

    public int getValue() {
        return value;
    }

    Bit(int value){
        this.value = value;
    }
    /**
     * Mnozenie dwoch bitow mod 2
     * @param bit argument
     * @return Bit - iloczyn dwoch bitow rowny 1 gdy bity zgodne, 0 gdy ro¿ne
     */
    public Bit multiply10(Bit bit){
        if(this.getValue()==bit.getValue()) return new Bit(1);
        else return new Bit(0);
    }
    /**
     * Mnozenie dwoch bitow mod 2
     * @param bit argument
     * @return int - iloczyn dwoch bitow rowny 1 gdy bity zgodne, -1 gdy ro¿ne
     */
    public int multiply11(Bit bit){
        if(this.getValue()==bit.getValue()) return 1;
        else return -1;
    }
    /**
     * Dodawanie dwoch bitow mod 2
     * @param bit argument
     * @return bit - suma dwoch bitow mod 2
     */
    public Bit add(Bit bit){
        int val=this.getValue()+bit.getValue();
	
        if(val==1) return new Bit(1);
        else return new Bit(0);
    }

}
