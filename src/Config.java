import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Bartolio on 03.11.2016.
 */
public class Config {

    public static int REGISTERS_NUMBER;
    public static int LFSR_LENGTH;

    public static int[] REGISTER_FIRST_XOR_NUMBERS;
    public static int[] REGISTER_SECOND_XOR_NUMBERS;
    public static int[] REGISTER_THIRD_XOR_NUMBERS;

    String registerFirstXorNumbersString;
    String registerSecondXorNumbersString;

    public Config() {

        try {
            readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * czytanie konfiguracji
     */
    public void readProperties() throws IOException {

        InputStream inputStream;

        Properties properties = new Properties();
        String confFileName = "config.properties";

        try {

            properties.load(new FileInputStream("resources/" + confFileName));

            REGISTERS_NUMBER = Integer.parseInt(properties.getProperty("register.number"));
            LFSR_LENGTH = Integer.parseInt(properties.getProperty("register.length"));

            registerFirstXorNumbersString = properties.getProperty("register.first.xor.numbers");
            String[] registerFirstXorNumbersStringTab = registerFirstXorNumbersString.split(",");
            REGISTER_FIRST_XOR_NUMBERS = new int[registerFirstXorNumbersStringTab.length];
            for (int i = 0; i < REGISTER_FIRST_XOR_NUMBERS.length; i++) {
                REGISTER_FIRST_XOR_NUMBERS[i] = Integer.parseInt(registerFirstXorNumbersStringTab[i]) - 1;
            }

            registerSecondXorNumbersString = properties.getProperty("register.second.xor.numbers");
            String[] registerSecondXorNumbersStringTab = registerSecondXorNumbersString.split(",");
            REGISTER_SECOND_XOR_NUMBERS = new int[registerSecondXorNumbersStringTab.length];
            for (int i = 0; i < REGISTER_SECOND_XOR_NUMBERS.length; i++) {
                REGISTER_SECOND_XOR_NUMBERS[i] = Integer.parseInt(registerSecondXorNumbersStringTab[i]) - 1;
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }

    @Override
    public String toString() {

        return "Config{REGISTERS_NUMBER=" + REGISTERS_NUMBER + ", LFSR_LENGTH=" + LFSR_LENGTH +
                ", REGISTER_FIRST_XOR_NUMBERS=" + registerFirstXorNumbersString + ", REGISTER_SECOND_XOR_NUMBERS=" + registerSecondXorNumbersString + "}";
    }
}
