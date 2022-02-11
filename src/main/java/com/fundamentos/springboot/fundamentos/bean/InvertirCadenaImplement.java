package com.fundamentos.springboot.fundamentos.bean;

public class InvertirCadenaImplement implements InvertirCadena{

    @Override
    public String iniciar(String cadena) {
        StringBuilder cadenaInvertida;
        cadenaInvertida = new StringBuilder();
        for(int i=cadena.length()-1; i>=0; i--){
            cadenaInvertida.append(cadena.charAt(i));
        }
        return cadenaInvertida.toString();
    }
}
