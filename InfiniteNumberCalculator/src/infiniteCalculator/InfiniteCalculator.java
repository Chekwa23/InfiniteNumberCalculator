package infiniteCalculator;

import java.io.IOException;

public class InfiniteCalculator 
{
	public static void main(String [] args) throws IOException
	{
		
		String first = "+2868798767689709897867587855643447586790978977545323236897909786786546435345657867978098897876785544654545678988099098967545465.68909";
		String second = "+176876785765678798098967654665334547668909899765456679778767564354786987808545232343467586970989897654532435456756879709899778.7991";
		
		
		AmusingPreciseNumber x = new AmusingPreciseNumber(first);
		AmusingPreciseNumber y = new AmusingPreciseNumber(second);
//		System.out.println(x.toString());
//		x.negate();
//		System.out.println(x.toString());
//		x.abs();
//		System.out.println(x.toString());
//		System.out.println(y.toString());

		System.out.println(AmusingPreciseNumber.add(x,y).toString());
//		System.out.println(subtract(x,y));
		
		
//		LinkedList<Integer> temp = new LinkedList<>();
//		temp.add(5);
//		temp.add(9);
//		temp.add(6);
//		
//		LinkedList<Integer> temp2 = new LinkedList<>(temp);
//		temp2.add(3);
//		
//		System.out.println(temp);
//		System.out.println(temp2);
		
//		String numb = "+29358";
//		Reader r = new StringReader(numb);
//		AmusingPreciseNumber x = new AmusingPreciseNumber(r);
////		assertEquals("29358", x.toString());
//		System.out.println(x.toString());
//		numb = "  -000397236.0023760000 31298374";
//		r = new StringReader(numb);
//		x = new AmusingPreciseNumber(r);
////		assertEquals("-397236.002376", x.toString());
//		System.out.println(x.toString());
//		numb = "0 ";
//		r = new StringReader(numb);
//		x = new AmusingPreciseNumber(r);
////		assertEquals("0", x.toString());
//		System.out.println(x.toString());
//		numb = "";
//		final Reader b = new StringReader(numb); 
//		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(b));
//		numb = " 234897a ";
//		final Reader c = new StringReader(numb);
//		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(c));
//		numb = "+.";
//		final Reader p = new StringReader(numb);
//		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(p));
		
	}
}
