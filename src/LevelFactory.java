//package practiceCode;

import java.util.Arrays;
import java.util.Random;

public interface LevelFactory {
	// Operations
	public int[] createLevel();
}
class EasyLevelFactory implements LevelFactory {
	// Operations
	public int[] createLevel() 
	{
		Random rand = new Random();
		int x = rand.nextInt(10);
		
		if (x > 6)
			return new int[]{x - 3, x - 2, x - 1, x};
		else
			return new int[]{x, x + 1, x + 2, x + 3};
		
	}
}
class MediumLevelFactory implements LevelFactory {
	// Operations
	public int[] createLevel() 
	{
		Random rand = new Random();
		int countDown = 10;
		
		byte[] bytes = new byte[10];
		Arrays.fill( bytes, (byte) 0 );
		
		int[] returnVal = new int[4];
		
		int x, currPos = 0;
		
		while(currPos < 4)
		{
			x = rand.nextInt(countDown);
			
			if(bytes[x] == 0)
			{
				bytes[x] = 1;
				returnVal[currPos] = x;
			}
			else
			{
				while(bytes[x % 10] != 0){x++; System.out.println("x = " + x + ", x % 10 = " + (x % 10));}
				bytes[x % 10] = 1;
				returnVal[currPos] = x % 10;
			}
			currPos++;
		}
		return returnVal;
	}
}
class HardLevelFactory implements LevelFactory {
	// Operations
	public int[] createLevel() 
	{
		Random rand = new Random();
		
		byte[] bytes = new byte[10];
		Arrays.fill( bytes, (byte) 0 );
		
		int[] returnVal = new int[4];
		
		int x;
		x = rand.nextInt(10);
			
		bytes[x] = 1;
		returnVal[0] = x;
			
		returnVal[0 + 1] = (returnVal[0] - 3 ) % 10;
		returnVal[0 + 2] = (returnVal[0] + 3 ) % 10;
		returnVal[0 + 3] = (returnVal[0] + 6 ) % 10;
			
		return returnVal;
		
	}
}