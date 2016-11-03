import java.io.IOException;

/**
 * Created by Bartolio on 30.10.2016.
 * <p>
 * Generator ciągu PN, generator kodu Golda - spreader i despreader
 */
public class Main {


    public static void main(String[] args) {

        Config config = new Config();
        System.out.println(config);
        System.out.println();

        new Lfsr(config.LFSR_LENGTH, config.REGISTER_FIRST_XOR_NUMBERS);
        System.out.println();
        new Lfsr(config.LFSR_LENGTH, config.REGISTER_SECOND_XOR_NUMBERS);

        //TODO trzeci LFSR
        //new Lfsr(config.LFSR_LENGTH, config.REGISTER_THIRD_XOR_NUMBERS);
    }
}
