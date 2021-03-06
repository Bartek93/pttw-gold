import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartolio on 30.10.2016.
 */
public class Lfsr {
	
	 

    private static final int LFSR_LENGTH = 3;
    private static final int VALUE_0_XOR = 0;
    private int statesNumber;
    private List<Sequence> sequenceList;

    private Sequence generatedSequence;

    private int length;
    private int[] xor_numbers;

   
    public Lfsr(int length, int[] xor_numbers) {
        this.length = length;
        this.xor_numbers = xor_numbers;

        computeStatesNumber();
        computeLfsr();

        Writer output1 = null;
    	try {
    		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
    		//output1.append(generatedSequence+"\n");
    		
    		System.out.println(generatedSequence);
    		
    		output1.close();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("\n\n\nERROR FILE\n\n\n");
    	}
        
    }

    /**
     * compute number of LFSR states
     */
    private void computeStatesNumber() {
        statesNumber = (int) Math.pow(2, length) - 1;
        Writer output1 = null;
    	try {
    		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
    		//output1.append("Max liczba stanów = " + statesNumber +"\n");
    		
    		System.out.println("Max liczba stanów = " + statesNumber);
    		output1.close();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("\n\n\nERROR FILE\n\n\n");
    	}
        
    }

    private void computeLfsr() {
        Sequence initialSequence = new Sequence(length);
        Sequence currentState = initialSequence;
        sequenceList = new ArrayList<>();
        generatedSequence = new Sequence();

        for (int state_index = 0; state_index < statesNumber; state_index++) {

            addBitToGeneratedSequence(currentState);

            Sequence currentSequence = new Sequence(currentState);
            sequenceList.add(currentSequence);

            int firstBit = addMod2(currentState);

            for (int j = length - 1; j > 0; j--) {
                currentState.setBit(currentState.getBit(j - 1), j);
            }

            if (firstBit == 0) {
                currentState.setBit(new Bit(0), 0);
            } else if (firstBit == 1) {
                currentState.setBit(new Bit(1), 0);
            }

            print(state_index);

            currentSequence = new Sequence(currentState);

            for (int n = 0; n < state_index; n++) {
                if (sequenceList.get(n).isEqual(currentSequence) && sequenceList.size() != statesNumber) {
                	 Writer output1 = null;
                 	try {
                 		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
                 		//output1.append("Wygenerowany ciąg NIE jest ML" +"\n");
                 		
                 		System.out.println("Wygenerowany ciąg NIE jest ML");
                 		
                 		output1.close();
                 	} catch (IOException e) {
                 		// TODO Auto-generated catch block
                 		e.printStackTrace();
                 		System.out.println("\n\n\nERROR FILE\n\n\n");
                 	}
                    
                    return;
                }
            }

        }
        Writer output1 = null;
     	try {
     		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
     		//output1.append("Wygenerowany ciąg jest ML" +"\n");
     		
     		System.out.println("Wygenerowany ciąg jest ML");
     		output1.close();
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     		System.out.println("\n\n\nERROR FILE\n\n\n");
     	}
        
    }

    private void addBitToGeneratedSequence(Sequence currentState) {
        generatedSequence.addBitAfter(currentState.getLastBit());
    }

    private int addMod2(Sequence currentState) {
        int result = 0;

        for (int i = 0; i < xor_numbers.length; i++) {
            result = result ^ (currentState.getLastBit().getValue() ^ currentState.getBit(xor_numbers[i]).getValue());
        }

        return result;
    }

    /**
     * get generated LFSR sequence
     *
     * @return
     */
    //TODO wziąć i sprawdzić czy pary są preferowane
    public Sequence getGeneratedSequence() {
        return generatedSequence;
    }

    private void print(int state_index) {
        String string = "";

        for (int i = 0; i < length; i++) {
            string = string + sequenceList.get(state_index).getBit(i).getValue() + ", ";
        }
        string = string.substring(0, string.length() - 2);

        Writer output1 = null;
     	try {
     		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
     		//output1.append(string +"\n");
     		
     		System.out.println(string);
     		output1.close();
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     		System.out.println("\n\n\nERROR FILE\n\n\n");
     	}
        
    }

}
