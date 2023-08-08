package br.com.will;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Compilador {

	private static final List<String> KEYWORDS = new ArrayList<>(
			Arrays.asList("if", "else", "for", "switch", "while", "do", "print", "read", "foreach"));
	private static final List<String> OPERADORES = new ArrayList<>(
			Arrays.asList("+", "-", "*", "/", "%", "--", "++", "<=", ">=", "<", ">", "^", "!=", "=", "==", "!"));
	private static final List<String> SEPARADORES = new ArrayList<>(Arrays.asList("(", ")", "[", "]", "{", "}"));

	private static final Pattern IDENTIFICADOR_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
	private static final Pattern NUMERO_PATTERN = Pattern.compile("[0-9]+");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		analisaLinha(line, 1);
		scan.close();
	}

	private static void analisaLinha(String linha, int numeroLinha) {
		String[] letras = linha.split("");
		int posicaoAtual = 0;

		while (posicaoAtual < letras.length) {

			if (OPERADORES.contains(letras[posicaoAtual])) {
				Token token = verificaOperador(letras, posicaoAtual);
				posicaoAtual = token.getPosFinal();
				System.out.println(token);
				continue;
			}

			if (SEPARADORES.contains(letras[posicaoAtual])) {
				Token token = verificaSeparador(letras, posicaoAtual);
				posicaoAtual = token.getPosFinal();
				System.out.println(token);
				continue;
			}

			if (IDENTIFICADOR_PATTERN.matcher(letras[posicaoAtual]).matches()) {
				Token token = verificaIdentificador(letras, posicaoAtual);
				posicaoAtual = token.getPosFinal();
				System.out.println(token);
				continue;
			}

			if (NUMERO_PATTERN.matcher(letras[posicaoAtual]).matches()) {
				Token token = verificaConstante(letras, posicaoAtual);
				posicaoAtual = token.getPosFinal();
				System.out.println(token);
				continue;
			}

			if (posicaoAtual < letras.length && letras[posicaoAtual].equals(" ")) {
				posicaoAtual++;
				continue;
			}
			System.out.println("Algo deu pau");
			break;

		}
	}

	private static Token verificaConstante(String[] letras, int posicaoInicial) {
		int posicaoFinal = posicaoInicial + 1;
		String lexema = letras[posicaoInicial];

		while (posicaoFinal < letras.length) {
			if (SEPARADORES.contains(letras[posicaoFinal]) || OPERADORES.contains(letras[posicaoFinal])
					|| letras[posicaoFinal].equals(" ")) {
				break;
			} else {
				lexema += letras[posicaoFinal];
				posicaoFinal++;
				continue;
			}
		}

		if (NUMERO_PATTERN.matcher(lexema).matches()) {
			return new Token("CONSTANTE", lexema, posicaoInicial + 1, posicaoFinal);
		} else {
			return new Token("ERRO", lexema, posicaoInicial + 1, posicaoFinal);
		}
	}

	private static Token verificaIdentificador(String[] letras, int posicaoInicial) {
		int posicaoFinal = posicaoInicial + 1;
		String lexema = letras[posicaoInicial];

		while (posicaoFinal < letras.length) {
			if (SEPARADORES.contains(letras[posicaoFinal]) || OPERADORES.contains(letras[posicaoFinal])
					|| letras[posicaoFinal].equals(" ")) {
				break;
			} else {
				lexema += letras[posicaoFinal];
				posicaoFinal++;
				continue;
			}
		}

		if (KEYWORDS.contains(lexema)) {
			return new Token("KEYWORD", lexema, posicaoInicial + 1, posicaoFinal);
		} else if (IDENTIFICADOR_PATTERN.matcher(lexema).matches()) {
			return new Token("IDENTIFICADOR", lexema, posicaoInicial + 1, posicaoFinal);
		} else {
			return new Token("ERRO", lexema, posicaoInicial + 1, posicaoFinal);
		}
	}

	private static Token verificaSeparador(String[] letras, int posicaoInicial) {
		int posicaoFinal = posicaoInicial + 1;
		String lexema = letras[posicaoInicial];
		return new Token("SEPARADOR", lexema, posicaoInicial + 1, posicaoFinal);
	}

	private static Token verificaOperador(String[] letras, int posicaoInicial) {
		int posicaoFinal = posicaoInicial + 1;
		String lexema = letras[posicaoInicial];

		while (posicaoFinal < letras.length) {
			if (OPERADORES.contains(letras[posicaoFinal])) {
				lexema += letras[posicaoFinal];
				posicaoFinal++;
				continue;
			} else {
				break;
			}
		}

		if (OPERADORES.contains(lexema)) {
			return new Token("OPERADOR", lexema, posicaoInicial + 1, posicaoFinal);
		} else {
			return new Token("ERRO", lexema, posicaoInicial + 1, posicaoFinal);
		}

	}
}
