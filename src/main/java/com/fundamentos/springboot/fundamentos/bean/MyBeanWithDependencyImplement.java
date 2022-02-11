package com.fundamentos.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    Log log = LogFactory.getLog(MyBeanWithDependencyImplement.class);

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
        log.info("Hemos ingresado al método printWithDependency");
        int numero = 1;
        log.debug("El número enviado como parametro a la dependencia operation es: ["+numero+"]");
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola Desde mi implementacion de un bean con dependencia");
    }
}
