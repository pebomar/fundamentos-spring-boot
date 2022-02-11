package com.fundamentos.springboot.fundamentos.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class AnalilzaCadenaImplement implements AnalizaCadena{

    @Autowired
    InvertirCadena invertir;

    @Override
    public void esPalindromo(String cadena){
        String cadenaInvertida;

        cadenaInvertida = invertir.iniciar(cadena);

        if(cadena.equals(cadenaInvertida)){
            System.out.println("La cadena es un palindromo");
        }else{
            System.out.println("La cadena no es un palindromo");
        }

    }
}
