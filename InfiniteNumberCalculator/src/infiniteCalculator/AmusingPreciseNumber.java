package infiniteCalculator;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * A class used to represent very large or very small numbers with arbitrary precision. 
 * @author Lucas Onwuchekwa
 */
public class AmusingPreciseNumber
{
	/**
	 * List representing the whole number part of a number.
	 */
	public LinkedList<Integer> fhalf = new LinkedList<>();
	/**
	 * List representing the decimal part of a number.
	 */
	private LinkedList<Integer> shalf = new LinkedList<>();
	/**
	 * The sign of the number either positive or negative.
	 */
	private int sign = 1;
	
	
	public AmusingPreciseNumber()
	{
	}
	/**
	 * Constructor creates an AmusingPreciseNumber from an int type. 
	 * @param numb
	 */
	public AmusingPreciseNumber(int numb)
	{
		String input = String.valueOf(numb);
		char [] charArr = input.toCharArray();
		ArrayList<Character> charList = new ArrayList<>();
		for(int i = 0; i < charArr.length; i++)
		{
			charList.add(charArr[i]);
		}
		
		if(charList.get(0) == '-')
		{
			sign = 0;
		}
		else
		{
			sign = 1;
		}
		for(int i = 0; i < charList.size(); i++)
		{
			if(Character.isDigit(charList.get(i)))//takes care of the sign at the beginning of the string if there is any
			{
				fhalf.add(Character.getNumericValue(charList.get(i)));
			}	
		}
	}
	
	/**
	 * Constructor creates an AmusingPreciseNumber from an String type. 
	 * @param numb
	 */
	public AmusingPreciseNumber(String numb)
	{
		char [] charArr = numb.toCharArray();
		ArrayList<Character> charList = new ArrayList<>();
		for(int i = 0; i < charArr.length; i++)
		{
			charList.add(charArr[i]);
		}
	
		helpSet(charList);
		
	}
	
	/**
	 * Constructor creates an AmusingPreciseNumber from a reader which reads
	 * from an input stream of some sort. Reads until it gets to the end of 
	 * the stream or a white space character is reached
	 * @param r
	 * @throws IOException
	 */
	public AmusingPreciseNumber(Reader r) throws IOException
	{
		ArrayList<Character> charList = new ArrayList<>();
		int num = 0;
		int i = 0;
		do
		{
			char [] buffer = new char[1024];
			
			num = r.read(buffer,i,1024);
			if(num != -1)
			{
				for(i = 0; i < num; i++)
				{
					charList.add(buffer[i]);
				}
			}
			else {}
		}while(num != -1 && num == 1024);
		
		while(charList.get(0) == ' ')
		{
			charList.remove(0);
		}
		
		if(charList.contains(' ') || charList.contains(null))
		{
			charList = new ArrayList<>(charList.subList(0, charList.indexOf(' ')));
		}
		
		helpSet(charList);

	}
	
	/**
	 * A copy constructor which makes a deep copy of the passed in argument.
	 * @param numb
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb)
	{
//		AmusingPreciseNumber copyNumb = new AmusingPreciseNumber(numb);
		fhalf = new LinkedList<>(numb.getFhalf());	
		shalf = new LinkedList<>(numb.getShalf());
		sign = numb.getSign();
	}
	
	/**
	 * Helper method used by the constructors having the string argument and the reader argument.
	 * This method helps check if the input string or input of the reader is valid. 
	 * If it is valid, it fills the first and second half linked list and if not,
	 * it throws a runtime exception.
	 * @param singles
	 */
	private void helpSet(ArrayList<Character> singles)
	{	int count = 0;
		for(int i = 0; i < singles.size(); i++)
		{
			if(Character.isDigit(singles.get(i)))
			{
				count++;
			}
		}
		if(count < 1)
		{
			throw new IllegalArgumentException("\n!!!!Error!!!!\nInvalid string syntax");
		}

		for(int i = 0; i < singles.size(); i++)
		{
			if(!"+-0123456789.".contains(""+singles.get(i)))
			{
				throw new IllegalArgumentException("\n!!!!Error!!!!\nInvalid string syntax");
			}
		}
//		Checking if the singles arraylist contains more than one -, + or . or both a - and +
		int plusORminus = 0, dot = 0;
		for(int i = 0; i < singles.size(); i++)
		{
			switch(singles.get(i))
			{
				case '+':
					plusORminus += 1;	
					break;
				case '-':
					plusORminus += 1;
					break;
				case '.':
					dot += 1;
					break;
			}
		}
		if((Character.isDigit(singles.get(0)) || singles.get(0) == '+' || singles.get(0) == '-') && 
			(Character.isDigit(singles.get(singles.size()-1)) || singles.get(singles.size()-1) == '.' ) && 
			plusORminus < 2 && dot < 2)
		{
//			if singles passes all the tests, it goes on to fill up the first half and second half lists
//			it checks the first item in the singles list, if it is - then change the value of sign, if it is plus or if it is an integer then value of  sign is 1
			if(singles.get(0) == '-')
			{
				sign = 0;
			}
			else
			{
//				its your normal positive number so you can just put a plus there
				sign = 1;
			}
			
//			goes through  the singles list and converts it to an int value and fills up the fhalf linked list until it finds the '.' then it starts filling up the 
//			shalf linked list.
//			if the '.' isnt found the fhalf is filled completely with all items in the arrayList; 
//			then getting to the second for loop and finding out that i is already greater than size,
//			therefore skipping it.
			int i;
			for(i = 0; i < singles.size(); i++)
			{
				if(singles.get(i) == '.')
				{
					break;
				}
				if(Character.isDigit(singles.get(i)))//takes care of the sign at the beginning of the string if there is any
				{
					fhalf.add(Character.getNumericValue(singles.get(i)));
				}
				else {}
			}
			for( i = i + 1; i < singles.size(); i++)
			{
				shalf.add(Character.getNumericValue(singles.get(i)));
			}
		}
		else
		{
			throw new RuntimeException("!!!!Error!!!!\nInvalid string syntax");
		}
		
	}
//	-----------------------------------------------------------------
	
	/**
	 * Adds numb to this AmusingPreciseNumber
	 * @param numb
	 */
	public void add(AmusingPreciseNumber numb )
	{
		AmusingPreciseNumber temp = add(this, numb);
		fhalf = new LinkedList<>(temp.getFhalf());
		shalf = new LinkedList<>(temp.getShalf());
		sign = temp.getSign();
	}
	
	/**
	 * Subtracts numb from this AmusingPreciseNumber
	 * @param numb
	 */
	public void subtract(AmusingPreciseNumber numb )
	{
		AmusingPreciseNumber temp = subtract(this, numb);
		fhalf = new LinkedList<>(temp.getFhalf());
		shalf = new LinkedList<>(temp.getShalf());
		sign = temp.getSign();
	}
	
	/**
	 * Negates this AmusingPreciseNumber
	 */
	public void negate()
	{
		if(sign == 1)
		{
			sign = 0;
		}
		else
		{
			sign = 1;
		}	
	}
	
	/**
	 * Computes and stores the absolute value of this AmusingPreciseNumber.
	 */
	public void abs()
	{
		sign = 1;
	}
	
	/**
	 * Adds together the two parameters passed in. Return an AmusingPreciseNumber 
	 * that is the sum of num1 and num2. Num1 and num2 are unchanged
	 * @param num1
	 * @param num2
	 * @return sum
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber num1, AmusingPreciseNumber num2)
	{
		AmusingPreciseNumber numb1 = new AmusingPreciseNumber(num1);
		AmusingPreciseNumber numb2 = new AmusingPreciseNumber(num2);
		addZeros(numb1,numb2);
		
//		++ and -- therefore just add and assign the right sign in the string
		if(numb1.getSign() == numb2.getSign())
		{
			int tens = 0;
			ArrayList<Character> charList = new ArrayList<Character>();
			for(int i = numb2.shalf.size()-1; i >= 0; i--)
			{
				int x = numb1.shalf.get(i) + numb2.shalf.get(i) + tens;
				int units = x%10;
				tens = x/10;
				charList.add((char) (units+48));
			}
			charList.add('.');
			for(int i = numb2.fhalf.size()-1; i >= 0; i--)
			{
				int x = numb1.fhalf.get(i) + numb2.fhalf.get(i) + tens;
				int units = x%10;
				tens = x/10;
				charList.add((char) (units+48));
			}
			if(tens>0)
			{
				charList.add((char) (tens+48));
			}
			else {}
			
			String str = "";
			if(numb1.getSign() == 1)
			{
				str += "+";
			}
			else
			{
				str += "-";
			}
			for(int i = charList.size()-1; i >= 0; i--)
			{
				str += charList.get(i);
			}
			AmusingPreciseNumber sum = new AmusingPreciseNumber(str);
			removeZeros(sum);
			return sum;
		}
		else
		{
			if(numb1.getSign() == 1)
			{
				numb2.abs();
				return subtract(numb1,numb2);
			}
			else
			{
				numb1.abs();
				return subtract(numb2,numb1);
			}
		}
		
	}
	
	/**
	 * Subtracts num2 from num1. Return an AmusingPreciseNumber 
	 * that is the difference between num1 and num2. Num1 and num2 are unchanged
	 * @param num1
	 * @param num2
	 * @return ansNumber
	 */
	public static AmusingPreciseNumber subtract(AmusingPreciseNumber num1, AmusingPreciseNumber num2)
	{
		AmusingPreciseNumber numb1 = new AmusingPreciseNumber(num1);
		AmusingPreciseNumber numb2 = new AmusingPreciseNumber(num2);
		AmusingPreciseNumber ansNumber = null;
		addZeros(numb1,numb2);
		
		if(numb1.getSign() != numb2.getSign())
		{
			if(numb1.getSign() == 1)
			{
				numb2.abs();
				return add(numb1,numb2);
			}
			else if(numb1.getSign() == 0)
			{
				numb2.negate();
				return add(numb1,numb2);
			}
		}
		ArrayList<Character> charList = new ArrayList<Character>();
		for(int i = numb2.getShalf().size()-1; i >= 0; i--)
		{	
			int lender = numb1.getShalf().get(i);
			int x = lender - numb2.getShalf().get(i);
			if(x < 0)
			{
				x = (lender + 10) - numb2.getShalf().get(i);
				if(i != 0)
				{
					int j;
					for(j = i-1; (j >= 0) && numb1.getShalf().get(j) == 0; j--)
					{
						numb1.getShalf().set(j, 9);
					}
					if(j < 0)
					{
						int k;
						for(k = numb1.getFhalf().size()-1; numb1.getFhalf().get(k) == 0; k--)
						{
							numb1.getFhalf().set(k, 9);
						}
						numb1.getFhalf().set(k,numb1.getFhalf().get(k)-1);
					}
					else
					{
						numb1.getShalf().set(j,numb1.getShalf().get(j)-1);
					}
				}
				else//Therefore your at the beginning of the second half list... entering the end of the first half.
				{
					int j;
					for(j = numb1.getFhalf().size()-1; (j >= 0) && numb1.getFhalf().get(j) == 0; j--)//We can't get to the end of this loop because if we get to this point it means numb1 > numb2
					{
						numb1.getFhalf().set(j, 9);
					}
					numb1.getFhalf().set(j,numb1.getFhalf().get(j)-1);
				}
			}
			else {}
			charList.add((char) (x+48));
		}
		charList.add('.');
		for(int i = numb2.getFhalf().size()-1; i >= 0; i--)
		{
			int lender = numb1.getFhalf().get(i);
			int x = lender - numb2.getFhalf().get(i);
			if(x < 0)
			{
				x = (lender + 10) - numb2.getFhalf().get(i);
				if(i != 0)
				{
					int k;
					for(k = i - 1; numb1.getFhalf().get(k) == 0 && k > 0; k--)//We can't get to the end of this loop because if we get to this point it means numb1 > numb2;
					{
						numb1.getFhalf().set(k, 9);
					}
					numb1.getFhalf().set(k,numb1.getFhalf().get(k)-1);
				}
				else
				{
					ansNumber = subtract(num2,num1);
					if(numb1.getSign() == 0 && numb1.getSign()== 0)
					{
						ansNumber.abs();
					}
					else
					{
						ansNumber.negate();
					}
					removeZeros(ansNumber);
					return ansNumber;
				}
			}
			else {}
			charList.add((char) (x+48));
		}
		charList.add('+');
		String ansString = "";
		for(int i = charList.size()-1; i >= 0; i--)
		{
			ansString += charList.get(i);
		}
		ansNumber = new AmusingPreciseNumber(ansString);
		
		if(numb1.getSign() == numb2.getSign())
		{
			if(numb1.getSign() == 0)
			{
				ansNumber.negate();
			}
			else {}
		}
		removeZeros(ansNumber);
		return ansNumber;
	}
	
	/**
	 * Return an AmusingPreciseNumber that is the negative of numb and leave numb unchanged.
	 * @param numb
	 * @return negated
	 */
	public static AmusingPreciseNumber negate(AmusingPreciseNumber numb)
	{
		AmusingPreciseNumber negated = new AmusingPreciseNumber(numb);
		negated.negate();
		return negated;
	}
	
	/**
	 * Return an AmusingPreciseNumber that is the absolute value of numb and leave numb unchanged.
	 * @param numb
	 * @return absolute
	 */
	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb)
	{
		AmusingPreciseNumber absolute = new AmusingPreciseNumber(numb);
		absolute.abs();
		return absolute;
	}
	
	/**
	 * Helper method to add leading and trailing zeroes to the number
	 * making the number balanced in length and calculations easier
	 * @param numb1
	 * @param numb2
	 */
	private static void addZeros(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2)
	{
//		adding trailing 0's
		if(numb1.shalf.size() > numb2.shalf.size())
		{
			int diff = numb1.shalf.size() - numb2.shalf.size();
			for(int i = 0; i < diff; i++)
			{
				numb2.shalf.add(0);
			}
		}
		else if(numb1.shalf.size() <  numb2.shalf.size())
		{
			int diff = numb2.shalf.size() - numb1.shalf.size();
			for(int i = 0; i < diff; i++)
			{
				numb1.shalf.add(0);
			}
		}
		else {}
//		adding leading 0's
		if(numb1.fhalf.size() > numb2.fhalf.size())
		{
			int diff = numb1.fhalf.size() - numb2.fhalf.size();
			for(int i = 0; i < diff; i++)
			{
				numb2.fhalf.add(i,0);
			}
		}
		else if(numb1.fhalf.size() <  numb2.fhalf.size())
		{
			int diff = numb2.fhalf.size() - numb1.fhalf.size();
			for(int i = 0; i < diff; i++)
			{
				numb1.fhalf.add(i,0);
			}
		}
		else {}
	}

	/**
	 * Helper method to remove leading and trailing zeroes after calculation
	 * @param numb
	 */
	private static void removeZeros(AmusingPreciseNumber numb)
	{
//		removes leading zeroes
		if(numb.getFhalf().size() > 1)
		{
			while(numb.getFhalf().peekFirst() == 0)
			{
				numb.getFhalf().remove();
				if(numb.getFhalf().size() == 1)
				{
					break;
				}
			}
		}
		else {}
//		removes trailing zeroes
		if(!numb.getShalf().isEmpty())
		{
			while(numb.getShalf().peekLast() == 0)
			{
				numb.getShalf().removeLast();
				if(numb.getShalf().isEmpty())
				{
					break;
				}
			}
		}
		else {}
	}
	/**
	 * Getter method to get the sign of this number
	 * @return sign
	 */
	private int getSign()
	{
		return sign;
	}

	/**
	 * Getter method to get the first half of this number
	 * @return fhalf
	 */
	private LinkedList<Integer> getFhalf()
	{
		return fhalf;
	}
	
	/**
	 * Getter method to get the second half of this number
	 * @return shalf
	 */
	private LinkedList<Integer> getShalf()
	{
		return shalf;
	}
	
	/**
	 * To string method to print this number in a string format to console
	 */
	@Override
	public String toString()
	{
		removeZeros(this);
		String str = "";
		if(sign == 1)
		{
//			str += "+"; Not needed
		}
		else
		{
			str += "-";
		}
		for(int i = 0; i < fhalf.size(); i++)
		{
			str += fhalf.get(i);
		}
		if(shalf.size() != 0)
		{
			str += ".";
			for(int i = 0; i < shalf.size(); i++)
			{
				str += shalf.get(i);
			}
		}
		else {};
		if(str.equals("-0"))
		{
			return "0";
		}
		return str;
	}

}



