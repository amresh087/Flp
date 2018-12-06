package com.flp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DemoTest
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		int arr[] = {2, 5, 3, 8 ,7 ,2, 5, 2, 3, 3, 5, 3};
		
		printArray(arr);
		
		Map<Integer,Integer> count_in_map=createMapFecque(arr);
		
		
		List<Entry<Integer,Integer>> sorted_List =sortByValue( count_in_map);
		
		
		pushInArray(arr,sorted_List);
		System.out.println();
		
		printArray(arr);
		
		
		
		
		

	}
	
	private static void printArray(int arr[])
	{
		for(int i=0;i<arr.length;i++){
			
			System.out.print(arr[i]+" ");
			
		}
		
	}
	
	
	
	public static void pushInArray(int arr[],List<Entry<Integer,Integer>> sorted_List)
	{
		int index=0;
		
		for(Entry<Integer,Integer> enty:sorted_List)
		{
			
			for(int i=0;i<enty.getValue();i++)
			{
				
				arr[index]=enty.getKey();
				index++;
				
			}
			
			
		}
		
		
		
	}
	
	
	public static List<Entry<Integer,Integer>> sortByValue(Map<Integer,Integer> count_in_map)
	{
		List<Entry<Integer,Integer>> sortbyvalue=new ArrayList<Entry<Integer,Integer>>(count_in_map.entrySet());
		
		Collections.sort(sortbyvalue,new Comparator<Entry<Integer,Integer>>()
		{

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
			{
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		
		return sortbyvalue;
		
		
	}
	
	
	
	public static Map<Integer,Integer> createMapFecque(int arr[])
	{
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		
		for(int i=0;i<arr.length;i++){
			
			if(map.containsKey(arr[i])){
				map.put(arr[i],map.get(arr[i])+1);
			}else
			{
				map.put(arr[i],1);
			}
			
		}
		return map;
	}
	
	
	





	
	
	
	
	
	
	
}



