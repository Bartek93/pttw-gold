/**
 * Created by Bartolio on 31.10.2016.
 */
public enum Bit {
    BIT_0(0),
    BIT_1(1);

    private int value;

    public int getValue() {
        return value;
    }

    Bit(int value){
        this.value = value;
    }

}
