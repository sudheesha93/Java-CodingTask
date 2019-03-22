package walmart.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jdk.nashorn.internal.ir.SetSplitState;

public class UnsortedSet {

	public static void main(String[] args) {
		
		Set<Integer> set1= new LinkedHashSet<>();
		set1.add(6);
		set1.add(2);
		set1.add(10);
		set1.add(4);
		set1.add(8);
		set1.add(1);
		
		Set<Integer> set2= new LinkedHashSet<>();
		set2.add(2);
		set2.add(1);
		set2.add(4);
		set2.add(8);
		set1.add(5);
		set2.add(6);
		set2.add(10);
		set2.add(3);
		
		
		System.out.println("Common elements in both the sets");
		set2.retainAll(set1);
		Iterator r = set2.iterator();
		
		while(r.hasNext()) {
			System.out.println(r.next());
		}
		
		
		System.out.println("Using enhanced for loops");
		 for(int s1: set1) {
			for(int s2: set2) {
				if(s1==s2) {
					System.out.println("( "+ s1 +","+ s2 + " )");
					break;
				}
			}
		}
	
		
		System.out.println("Using HashMap");
		Map<Integer,Integer> map= new LinkedHashMap<>();
		
		for(int s1: set1) {
			map.put(s1, 1);
		}
		
		for(int s2: set2) {
			if(map.containsKey(s2)) {
				map.put(s2, map.get(s2)+1);
			}
			else {
				map.put(s2, 1);
			}
		}
		
		Set keys= map.keySet();
		for(Object k : keys) {
			if(map.get(k)==2) {
				System.out.println("( "+ k +","+ k + " )");
			}
		}
		
				

	}

}
