
import java.util.Arrays;
import java.util.Comparator;

public class Cakes {
	
	public static void main (String ... args) {
		
		// (7, 160), (3, 90), (2, 15)
		Cake[] cakes = {new Cake(7, 160),
						new Cake(3, 90),
						new Cake(2, 15)};
		
		int capacity = 20;
		
		System.out.println("Cakes    : " + Arrays.toString(cakes));
		System.out.println("Capacity : " + capacity);
		
		System.out.println("Solution (slow, optimal in all cases)   : " + optimal(cakes, capacity));
		System.out.println("Solution (fast, but not always optimal) : " + fast(cakes, capacity));

		// (3, 40), (5, 70)
	    Cake[] cakes2 = {new Cake(3, 40),
		        		 new Cake(5, 70)};
		
		int capacity2 = 9;
		                            
		System.out.println("Cakes    : " + Arrays.toString(cakes2));
		System.out.println("Capacity : " + capacity2);
				                                         				
		System.out.println("Solution (slow, optimal in all cases)   : " + optimal(cakes2, capacity2));
		System.out.println("Solution (fast, but not always optimal) : " + fast(cakes2, capacity2));

	}
	
	public static int optimal(Cake[] cakes, int capacity) {
		
		// check for infinitively valuable cakes (weight == 0, value > 0) 
		for (Cake c : cakes) {
			if (c.getValuePerWeight() == Double.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
		}
		
		int[] maxValueAtCapacity = new int[capacity + 1];
		for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
			int currentMax = 0;
			for (Cake c : cakes) {
				if (c.getWeight() <= currentCapacity) {
					currentMax = Math.max(currentMax, c.getValue() + maxValueAtCapacity[currentCapacity-c.getWeight()]);
				}
			}
			maxValueAtCapacity[currentCapacity] = currentMax;
		}
		
		return maxValueAtCapacity[capacity];
	}
	
	public static int fast(Cake[] cakes, int capacity) {
		
		// check for infinitively valuable cakes (weight == 0, value > 0) 
		for (Cake c : cakes) {
			if (c.getValuePerWeight() == Double.MAX_VALUE) {
				return Integer.MAX_VALUE; 
			}
		}
		
		// sort the cakes, most valuable per weight first
		Arrays.sort(cakes, new Comparator<Cake>() {
			public int compare(Cake c1, Cake c2) {
				if (c1.getValuePerWeight() < c2.getValuePerWeight()) {
					return 1;
				} else if (c1.getValuePerWeight() > c2.getValuePerWeight()) {
					return -1;
				}
				return 0;
			}
		});
		
		int currentValue  = 0;

		// take the most valuable first, and fill the bag until you reach capacity
		// then, move along, and fill the rest if possible
		for (int i = 0, currentWeight = 0; i < cakes.length && currentWeight < capacity; i++) {
			while (cakes[i].getWeight() + currentWeight <= capacity) {
				currentValue  += cakes[i].getValue();
				currentWeight += cakes[i].getWeight();
			}
		}

		return currentValue;
	}
}

class Cake {
	
	private int weight; 
	private int value;
	
	public Cake(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
	
	public double getValuePerWeight() {
		if (weight == 0 && value > 0) {
			return Double.MAX_VALUE;
		} else if (weight == 0 && value == 0) {
			return 0.0;
		} else {
			return value/weight; 
		}
	}

	public String toString() {
		return "(weight=" + weight + ", value=" + value + ")";
	}
}

