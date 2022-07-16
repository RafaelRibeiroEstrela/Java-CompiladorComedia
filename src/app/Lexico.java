package app;

import java.util.ArrayList;
import java.util.List;

public class Lexico {

	private String[] arrayNumeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private String[] arrayOperacoes = { "+", "-", "*", "/", "^" };
	private String[] arraySimbolos = { "(", ")", ".", "[", "]" };
	private String[] arrayExp = { "e", "x", "p" };

	private List<String> bufferNumeros = new ArrayList<>();
	private List<String> bufferExp = new ArrayList<>();
	
	public Lexico(String input) {
		executar(input);
	}

	public String[] getArrayNumeros() {
		return arrayNumeros;
	}

	public String[] getArrayOperacoes() {
		return arrayOperacoes;
	}

	public String[] getArraySimbolos() {
		return arraySimbolos;
	}

	public String[] getArrayExp() {
		return arrayExp;
	}

	public List<String> getBufferNumeros() {
		return bufferNumeros;
	}

	public List<String> getBufferExp() {
		return bufferExp;
	}

	private boolean verificaSeExiste(String elemento, String[] array) {

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(elemento)) {
				return true;
			}
		}
		return false;
	}
	
	private int verificaNumeroDeVezesRepetido(String elemento, List<String> list) {
		int cont = 0;
		for (String e : list) {
			if (e.equals(elemento)) {
				cont += 1;
			}
		}
		return cont;
	}
	
	
	private void verificaBufferNumero() {
		int cont = 0;
		if (bufferNumeros.size() > 0) {
			cont = verificaNumeroDeVezesRepetido(".", bufferNumeros);
			if (cont > 1) {
				throw new RuntimeException("O elemento " + bufferNumeros.toString() + " é invalido.");
			}
			String numero = "";
			for (String e : bufferNumeros) {
				numero += e;
			}
			TabelaSimbolos.inserir("id", numero);
			bufferNumeros.clear();
		}
	}
	
	private void verificaBufferExp() {
		if (bufferExp.size() > 0) {
			String elemento = "";
			for (String e : bufferExp) {
				elemento += e;
			}
			if (!elemento.equals("exp")) {
				throw new RuntimeException("O elemento " + elemento + " não existe na linguaem do compilador.");
			}
			TabelaSimbolos.inserir("op", "exp");
			bufferExp.clear();
		}
	}

	public void executar(String input) {
		
		for (int i=0; i<input.length(); i++) {
			String elemento = String.valueOf(input.charAt(i));
			
			if ((!verificaSeExiste(elemento, arrayNumeros)) && (!verificaSeExiste(elemento, arrayOperacoes)) && 
					(!verificaSeExiste(elemento, arraySimbolos)) && (!verificaSeExiste(elemento, arrayExp)) ){
				throw new RuntimeException("Elemento " + elemento + " não existe na linguagem do compilador.");
			}
			
			if (verificaSeExiste(elemento, arrayExp)) {
				verificaBufferNumero();
				bufferExp.add(elemento);
			}
			
			if (verificaSeExiste(elemento, arrayNumeros)) {
				bufferNumeros.add(elemento);
			}
			
			if (verificaSeExiste(elemento, arraySimbolos)) {
				if (elemento.equals(".")) {
					bufferNumeros.add(elemento);
				}
				else {
					verificaBufferNumero();
					TabelaSimbolos.inserir("si", elemento);
				}
			}
			
			if (verificaSeExiste(elemento, arrayOperacoes)) {
				verificaBufferNumero();
				TabelaSimbolos.inserir("op", elemento);
			}
			
			if (bufferExp.size() == 3) {
				verificaBufferExp();
			}
			
		}
		
		verificaBufferNumero();
		
	}

}
