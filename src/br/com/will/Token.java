package br.com.will;

public class Token {

	private String token;
	private String lexema;
	private int posInicial;
	private int posFinal;

	public Token() {

	}

	public Token(String token) {
		this.token = token;
	}

	public Token(String token, String lexema, int posInicial, int posFinal) {
		super();
		this.token = token;
		this.lexema = lexema;
		this.posInicial = posInicial;
		this.posFinal = posFinal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getPosInicial() {
		return posInicial;
	}

	public void setPosInicial(int posInicial) {
		this.posInicial = posInicial;
	}

	public int getPosFinal() {
		return posFinal;
	}

	public void setPosFinal(int posFinal) {
		this.posFinal = posFinal;
	}

	@Override
	public String toString() {
		return "Token [token = " + token + ", lexema = " + lexema + " , posInicial = " + posInicial + ", posFinal = " + posFinal
				+ "]";
	}
	
	

}
