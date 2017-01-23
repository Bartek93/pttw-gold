import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

/**
 * Created by Bartolio on 30.10.2016.
 */
public class Sequence {

    private Vector<Bit> sequence = new Vector<>();

    public Sequence() {
    }

    /**
     * initial Sequence
     *
     * @param length
     */
    public Sequence(int length) {
    	Bit bit = new Bit(1);
        sequence.add(bit);
        for(int i=1;i<length;i++){
            bit = new Bit(0);
            sequence.add(bit);
        }
        Writer output1 = null;
     	try {
     		output1 = new BufferedWriter(new FileWriter("C:\\PTTW\\pttw.txt", true));
     		//output1.append("register size " + sequence.size() +"\n");
     		
     		System.out.println("register size " + sequence.size());
     		output1.close();
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     		System.out.println("\n\n\nERROR FILE\n\n\n");
     	}
        
    }

    /**
     * to copy of Sequence
     *
     * @param seq
     */
    public Sequence(Sequence seq) {
        for (int i = 0; i < seq.getSize(); i++) {
            this.sequence.add(seq.getBit(i));
        }
    }

    /**
     * get last bit from sequence
     *
     * @return last bit
     */
    public Bit getLastBit() {
        return sequence.lastElement();
    }

    /**
     * get bit from specific position
     *
     * @param index
     * @return bit
     */
    public Bit getBit(int index) {
        return sequence.get(index);
    }

    /**
     * set bit at specific position in sequence
     *
     * @param bit
     * @param index
     */
    public void setBit(Bit bit, int index) {
        sequence.setElementAt(bit, index);
    }

    /**
     * get size of sequence
     *
     * @return
     */
    public int getSize() {
        return sequence.size();
    }

    /**
     * Dodanie bit'u do sekwencji
     *
     * @param bit bit0 lub bit1
     */
    public void addBitAfter(Bit bit) {
        sequence.add(bit);
    }

    public boolean isEqual(Sequence seq) {
        for (int i = 0; i < this.getSize(); i++) {
            if ((this.getBit(i)).getValue() != (seq.getBit(i)).getValue())
                return false;
        }
        return true;
    }

    @Override
    public String toString() {

        String string = new String();
        for (int i = 0; i < sequence.size(); i++) {
            string += sequence.get(i).getValue();
        }

//        return "Sequence{sequence=" + string + '}';
        return string;
    }
    /**
     * bit z konca sekwencji wstawia na jego poczatek
     * @return true jezeli operacja wykonana poprawnie
     */
    public boolean shift(){
        int size = sequence.size();
        Bit bit = sequence.remove(size-1);
        sequence.add(0, bit);
        return true;
    }
    /**
     * funkcja zwraca dlugosc sekwencji
     * @return dlugo9sc sekwencji
     */	
    public int length(){
        return sequence.size();
    }
    /**
     * iloczyn dwoch ciagow (modulo 2 każdego bitu - 1 gdy oba bity sa zgodne,0 jesli nie)
     * @param seq drugi ciag, ktory ma byc zsumowany z this
     * @return nowy, fizycznie utworzony ciag
     */
    public Sequence product(Sequence seq){
        if(this.length()!=seq.length()) return null; //blad dodawanie dwoch ciagow roznej dlugosci
        Sequence result= new Sequence();
        for(int i=0;i<this.length();i++)
            result.addBitAfter( this.sequence.get(i).multiply10(seq.sequence.get(i)) );
        return result;
    }
    /**
     * suma dwoch ciagow (modulo 2 każdego bitu)
     * @param seq drugi ciag, ktory ma byc zsumowany z this
     * @return nowy,fizycznie utworzony ciag
     */
    public Sequence sum(Sequence seq){
        if(this.length()!=seq.length()) return null; //blad dodawanie dwoch ciagow roznej dlugosci
        Sequence result= new Sequence();
        for(int i=0;i<this.length();i++)
            result.addBitAfter( this.sequence.get(i).add(seq.sequence.get(i)) );
        return result;
    }
    /**
     * zwraca sume otrzymana przez dodanie wyrazow wymnozonych dwoch ciagow z odpowiednim przesunieciem (okreslonym jako czesc bitu)
     * @param seq drugi ciag, ktory ma byc zsumowany z this
     * @param part czesc bitu dla jakiej chcemy obliczyc f. korelacji (0 < part < 1)
     * @return wynik funkcji korelacji dwoch ciagow dla przesuniecia o czesc bitu
     */
    public double sumItemOfProduct(Sequence seq,double part){
        if(this.length()!=seq.length()) return 0; //blad dodawanie dwoch ciagow roznej dlugosci
        double result= 0;
        for(int i=0;i<this.length();i++){
            if(i-1>=0) result+= ((1-part)*(this.sequence.get(i).multiply11(seq.sequence.get(i))) + part*(this.sequence.get(i).multiply11(seq.sequence.get(i-1))));
            else result+= ((1-part)*(this.sequence.get(i).multiply11(seq.sequence.get(i))) + part*(this.sequence.get(i).multiply11(seq.sequence.lastElement()))) ;
        }
        return result;
    }	
    /**
     * Oblicza sume wyrazow ciagu (traktujac 0 jak -1) i zwraca wynik
     * @return wynik
     */
    public int sumOfItems(){
        int result =0;
        for(int i=0;i<this.length();i++){
            if(this.getBitFrom(i).getValue()==0) result--;
            else result++;
        }
        return result;
    }
    /**
     * Zwraca bit z pozycji wskazanej argumentem (numeracja od 0 do length-1)
     * @param i numer rzadanego bitu
     * @return bit na ze wskazanej pozycji
     */
    public Bit getBitFrom(int i){
        return sequence.get(i);
    }
}
