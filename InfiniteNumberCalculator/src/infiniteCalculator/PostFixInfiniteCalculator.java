package infiniteCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Main class implementing a post-fix calculator.
 * @author Lucas Onwuchekwa
 */
public class PostFixInfiniteCalculator 
{
	public static void main(String [] args)
	{
		
		Deque228<AmusingPreciseNumber> numbers = new Deque228<>();//stack
		Deque228<String> strque = new Deque228<>();//queue
		Deque228<String> operators = new Deque228<>();//queue
		ArrayList<String> temp = new ArrayList<>();
		temp.add("-");
		temp.add("+");
		temp.add("abs");
		temp.add("neg");
		operators.addAll(temp);
		
		System.out.println("Input your expression");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();

		String arr[] = str.split("\\s++");
		for(int i = 0; i < arr.length; i++)
		{
			strque.add(arr[i]);
		}
		
		while(!strque.isEmpty())
		{
			String peeked = (String) strque.peekFirst();
			AmusingPreciseNumber numb1 = null;
			AmusingPreciseNumber numb2 = null;
			if(temp.contains(peeked))
			{
				switch(peeked)
				{		
					case "+":
						if(numbers.size() < 2)
						{
							System.out.println("!!!!!ERROR!!!!!\nThe expression is unbalanced and therefore cannot be computed");
							return;
						}
						numb1 = (AmusingPreciseNumber) numbers.pop();
						numb2 = (AmusingPreciseNumber) numbers.pop();
						numb2.add(numb1);
						numbers.push(numb2);
						break;
						
					case "-":
						if(numbers.size() < 2)
						{
							System.out.println("!!!!!ERROR!!!!!The expression is unbalanced and therefore cannot be computed");
							return;
						}
						numb1 = (AmusingPreciseNumber) numbers.pop();
						numb2 = (AmusingPreciseNumber) numbers.pop();
						numb2.subtract(numb1);
						numbers.push(numb2);
						break;
						
					case "abs":
						if(numbers.size() == 0)
						{
							System.out.println("!!!!!ERROR!!!!!The expression is unbalanced and therefore cannot be computed");
							return;
						}
						numb1 = (AmusingPreciseNumber) numbers.pop();
						numb1.abs();
						numbers.push(numb1);
						break;
						
					case "neg":
						if(numbers.size() == 0)
						{
							System.out.println("!!!!!ERROR!!!!!The expression is unbalanced and therefore cannot be computed");
							return;
						}
						numb1 = (AmusingPreciseNumber) numbers.pop();
						numb1.negate();
						numbers.push(numb1);
						break;
				}
				strque.remove();
			}
			else
			{
				numb1 = new AmusingPreciseNumber(peeked);
				numbers.push(numb1);
				strque.remove();
			}		
			
		}
		if(numbers.size() > 1)
		{
			System.out.println("!!!!!ERROR!!!!!The expression is unbalanced and therefore cannot be computed");
			return;
		}
		else
		{
			System.out.println(numbers.pop());
		}
		
		
	}
}

