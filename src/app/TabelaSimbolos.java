package app;

import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolos {
	
	private static final List<String> CHAVE = new ArrayList<>();
	private static final List<String> VALOR = new ArrayList<>();
	
	public static void inserir(String chave, String valor) {
		CHAVE.add(chave);
		VALOR.add(valor);
	}
	
	public static void mostrar() {
		for (int i=0; i<CHAVE.size(); i++) {
			System.out.println(CHAVE.get(i) + " : " + VALOR.get(i));
		}
	}
	

}
