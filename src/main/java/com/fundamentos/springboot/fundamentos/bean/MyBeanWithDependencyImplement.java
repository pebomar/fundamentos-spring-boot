package com.fundamentos.springboot.fundamentos.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
   //Implementacion Curso
    /*private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }
    */


    //Implementacion opcional
    @Autowired
    private MyOperation myOperation;


    @Override
    public void printWithDependency() {
        int numero = 1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola Desde mi implementacion de un bean con dependencia");
    }
}
