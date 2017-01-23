import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

/**
 * Created by Bartolio on 30.10.2016.
 */
public class GoldGenerator {
	
	
	 //ciagi 1., 2. i 3.,  za pomoca ktorych generowane sa kody Golda
    Sequence seq1 = null;
    Sequence seq2 = null;
    Sequence seq3 = null;

    //Wektor ciagow przechowujacy wygenerowane kody Golda
    Vector<Sequence> generatedSequences = new Vector<Sequence>();

	/**
     * konstruktor domyslny
     */
    public GoldGenerator(){
    }

    /**
     * konstruktor - generator kodow (wypelnia trescia wektor kodow - generatedSequences)
     * @param sequence1 ciag 1 za pomoca ktorego generowane sa kody Golda
     * @param sequence2 ciag 2 za pomoca ktorego generowane sa kody Golda
     */
    public GoldGenerator(Sequence sequence1, Sequence sequence2){
    	
        //skopiowanie fizyczne ciagow
        seq1 = new Sequence(sequence1);
        seq2 = new Sequence(sequence2);
        Sequence sequen2=new Sequence(sequence2);

        generatedSequences.add(sequence1);
        generatedSequences.add(sequence2);
        for(int i=0;i<seq1.length();i++){
            generatedSequences.add(seq1.sum(sequen2));
            sequen2.shift();
        }
        toString();
    }

    /**
     * konstruktor - generator kodow (wypelnia trescia wektor kodow - generatedSequences)
     * @param sequence1 ciag 1 za pomoca ktorego generowane sa kody Golda
     * @param sequence2 ciag 2 za pomoca ktorego generowane sa kody Golda
     * @param sequence3 ciag 3 za pomoca ktorego generowane sa kody Golda
     */
    public GoldGenerator(Sequence sequence1, Sequence sequence2, Sequence sequence3){
    	
        //skopiowanie fizyczne ciagow
        seq1 = new Sequence(sequence1);
        seq2 = new Sequence(sequence2);
        seq3 = new Sequence(sequence3);
        Sequence sequen2=new Sequence(sequence2);
        Sequence sequen3=new Sequence(sequence3);

        generatedSequences.add(sequence1);
        generatedSequences.add(sequence2);
        generatedSequences.add(sequence3);
        for(int i=0;i<seq1.length();i++){
            for(int j=0;j<seq1.length();j++){
                generatedSequences.add(seq1.sum(sequen2.sum(sequen3)));
                sequen3.shift();
            }
            sequen2.shift();
        }
        toString();
    }
	
	/**
     * Funkcja zwraca wygenerowana sekwencje
     * @return wygenerowana sekwencja
     */	
    public Vector<Sequence> generatedSequences(){
    	return generatedSequences;
    }
    
    /**
     * Funkcja zwraca rozmiar wygenerowanej sekwencji
     * @return rozmiar wygenerowanej sekwencji
     */		
    public int getSize() {
    	return generatedSequences.size();
    }
	
    /**
     * sprawdza czy ciagi sa para preferowana
     * @param seq1 pierwszy ciag
     * @param seq2 drugi ciag
     * @param l dlugosc rejestru u�ytego do wygenerowania ciagow L=2^l-1
     * @return true je�eli ciegi tworza pare preferowana
     */
    public static boolean ifPreferredPair(Sequence seq1,Sequence seq2, int l ){
		
        if(seq1.length()!=seq2.length() || seq1.length()!=(int)Math.round(Math.pow(2, l)-1) ) return false;
		
        Correlation correlator=new Correlation();
        Vector<Point2D.Double> points = new Vector<Point2D.Double>();
        points = correlator.correlation1_1(seq1, seq2, 1);
        double value= 0;
		
		if(l%2==0) value = 1+ Math.pow(2, (l+2)/2);
        else value = 1+ Math.pow(2, (l+1)/2);
		
        double values[] = {-value, value -2, -1, seq1.length()};
        for(int i=0;i<points.size();i++){
            if(points.get(i).y!=values[0] &&points.get(i).y!=values[1] && points.get(i).y!=values[2] && points.get(i).y!=values[3] ) return false;
        }
        return true;
    }	
	
    /**
     * Funkcja zwraca zawartosc ci�g�w Golda
     */
    @Override
    public String toString(){
    	
        String info=new String();
//        info+="wygenerowane ciagi Golda ";
//        info+="o dlugosci: "+seq1.length()+"\n";
//        for(int i=0;i<generatedSequences.size();i++){
//            info+="ciag nr "+(i+1)+": \t"+ generatedSequences.get(i).toString()+"\n";
//        }


        for(int i=0;i<generatedSequences.size();i++){
            info+=generatedSequences.get(i).toString()+"\n";
        }

        Writer output1 = null;
     	try {
     		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
     		output1.append(info +"\n");
     		
     		System.out.print(info);
     		output1.close();
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     		System.out.println("\n\n\nERROR FILE\n\n\n");
     	}
		
		
        return info;
    }
	
	
	
}
