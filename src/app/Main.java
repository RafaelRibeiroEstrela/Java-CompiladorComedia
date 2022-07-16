package app;

import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entrada: ");
		String input = sc.nextLine();
		
		Lexico lexico = new Lexico(input);
		
		TabelaSimbolos.mostrar();
		
		
		
		
		
		
		
		
	}
}
