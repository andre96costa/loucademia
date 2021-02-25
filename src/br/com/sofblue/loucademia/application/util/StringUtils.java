package br.com.sofblue.loucademia.application.util;


public class StringUtils {
	
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}
		return s.trim().length() == 0;
	}
	
	//Metodo que formata uma string adicionando 0 a esqueda do valor passado.
	public static String leftZeroes(int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
	}
	

}
