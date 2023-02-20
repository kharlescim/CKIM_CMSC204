
public class ArraySum {
	public int sumOfArray(Integer[] array, int index) {
		if(index == 0)
			return array[0];
		else 
			return array[index] + sumOfArray(array, index-1);
	}
}
