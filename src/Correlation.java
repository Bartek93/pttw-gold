import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created by Bartolio on 30.10.2016.
 */
public class Correlation {
	Vector<Point2D.Double> results;

	/**
    * Wyznaczenie funkcji autokorelacji
    * @param seq ciag, dla ktorego bedzie zwrocona funkcja
    * @param resolution krok z jakim sa wyznaczane punkty, jeżeli resolution =1 -> 1 bit, jeżeli 2 -> 0.5 bitu itd.
    * @return wektor punktow
    */
   public Vector<Point2D.Double> autocorrelation(Sequence seq, int resolution)
   {
       results = new Vector<Point2D.Double>();

       //fizyczne skopiowanie ciagu
       Sequence sequence1= new Sequence(seq);
       Sequence sequence2= new Sequence(seq);

       Sequence seqRes= new Sequence();
       double buffer=0;

       for(int i=0;i<seq.length();i++)
       {
           seqRes=sequence1.product(sequence2);
           buffer =seqRes.sumOfItems();
           Point2D.Double point=new Point2D.Double(i,buffer);
           results.add(point);

           //obliczenie funkcji autokorelacji dla przesuniecia o czesc bitu
           for(int j=1;j<resolution;j++)
           {
               buffer=sequence1.sumItemOfProduct(sequence2, (double)j/resolution);
               point=new Point2D.Double(i+(double)j/resolution,buffer);
               results.add(point);
           }
           sequence2.shift();
       }
       return results;
   }
	
	
   /**
    * Metoda wyznacza funkcje korelacji wzajemnej dwoch ciagow
    * @param seq1 ciag 1.
    * @param seq2 ciag 2.
    * @param resolution krok z jakim sa wyznaczane punkty, jeżeli resolution =1 -> 1 bit, jeżeli 2 -> 0.5 bitu itd.
    * @return wektor punktow
    */
   public Vector<Point2D.Double> correlation1_1(Sequence seq1, Sequence seq2,int resolution){
       results = new Vector<Point2D.Double>();

       if(seq1.length()!=seq2.length()) return null;

       //fizyczne skopiowanie ciagu
       Sequence sequence1= new Sequence(seq1);
       Sequence sequence2= new Sequence(seq2);

       Sequence sequenRes= new Sequence();
       double buffer=0;

       for(int i=0;i<sequence1.length();i++){
           sequenRes=sequence1.product(sequence2);
           buffer =sequenRes.sumOfItems();
           Point2D.Double point=new Point2D.Double(i,buffer);
           results.add(point);

           //obliczenie funkcji autokorelacji dla przesuniecia o czesc bitu
           for(int j=1;j<resolution;j++){
               buffer=sequence1.sumItemOfProduct(seq2, (double)j/resolution);
               point=new Point2D.Double(i+(double)j/resolution,buffer);
               results.add(point);
           }
           sequence2.shift();
       }
       return results;
   }
   
}
