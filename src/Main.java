import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by Bartolio on 30.10.2016.
 * <p>
 * Generator ciągu PN, generator kodu Golda - spreader i despreader
 */
public class Main {


    public static void main(String[] args) {

        Writer output1 = null;
        try {
            output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));


            Config config = new Config();
            output1.append(config + "\n");
            System.out.println(config);

            System.out.println();

            Lfsr ciag1 = new Lfsr(config.LFSR_LENGTH, config.REGISTER_FIRST_XOR_NUMBERS);
            System.out.println();
            output1.append("\n");
            Lfsr ciag2 = new Lfsr(config.LFSR_LENGTH, config.REGISTER_SECOND_XOR_NUMBERS);

            GoldGenerator check = new GoldGenerator();
            boolean check1 = check.ifPreferredPair(ciag1.getGeneratedSequence(), ciag2.getGeneratedSequence(), config.LFSR_LENGTH);
            if (check1 == true) {
                System.out.println("Para preferowana");
                new GoldGenerator(ciag1.getGeneratedSequence(), ciag2.getGeneratedSequence());
                output1.append("======================================");
                System.out.println("===================================");
            } else {
                System.out.println("Para nie jest preferowana");
            }
            output1.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("\n\n\nERROR FILE\n\n\n");
        }
        //TODO trzeci LFSR
        //new Lfsr(config.LFSR_LENGTH, config.REGISTER_THIRD_XOR_NUMBERS);
    }
}
