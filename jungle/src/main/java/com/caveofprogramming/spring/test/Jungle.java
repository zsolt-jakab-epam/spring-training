package com.caveofprogramming.spring.test;

import java.util.Map;

public class Jungle {
	private Animal largest;
	private Map<String, String> foods;
	private Map<String, Animal> animals;

	public Animal getLargest() {
		return largest;
	}

	public void setFoods(Map<String, String> foods) {
		this.foods = foods;
	}

	public void setAnimals(Map<String, Animal> animals) {
		this.animals = animals;
	}
	
	public void setLargest(Animal largest) {
		this.largest = largest;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, String> entry: foods.entrySet()) {
			sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		
		sb.append("\n");
		
		for (Map.Entry<String, Animal> entry: animals.entrySet()) {
			sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		
		return sb.toString();
	}
}
