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
        sequence.add(Bit.BIT_1);
        for (int i = 0; i < length - 1; i++) {
            sequence.add(Bit.BIT_0);
        }
        System.out.println("register size " + sequence.size());
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

        return "Sequence{sequence=" + string + '}';
    }
}
