package br.edu.unirn.tcc.beans;

import java.util.ArrayList;
import java.util.List;

public class Codalicity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print(biggestGap(1154)); //
		
	}
	
	private static List<Integer> convertToBinary(int n){
		List<Integer> i = new ArrayList<Integer>();
		if (n == 0){
			i.add(0);
			return i;
		}
		while (n >= 2){
			i.add(n%2);
			n = n/2;
		}
		if (n == 1){
			i.add(1);
		}
		return i;
	}
	
	private static int biggestGap(int n){
		
		List<Integer>r = convertToBinary(n); //5265
		int biggest = 0;
		int count = 0;
		for (int c = r.size()-1; c>=0; c--){
			if (r.get(c) == 0){
				count++;
			} else if (r.get(c) == 1){
				biggest = count > biggest ? count : biggest;
				count = 0;
			}
		}
		return biggest;
		
	}

}/*
 8    /       2
 0            4  /  2
              0     2  /  2
                    0     1*/