package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class MyConfigurationBean {

    @Bean
    public MyBean beanOperation(){
        //return new MyBeanImplement();
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperation2(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        //return new MyBeanWithDependencyImplement(myOperation);

        //ImplementacionOpcional
        return new MyBeanWithDependencyImplement();
    }


    @Bean
    public InvertirCadena beanProcesarString(){
        return new InvertirCadenaImplement();
    }

    @Bean
    public AnalizaCadena beanAnalizarCadena(){
        return new AnalilzaCadenaImplement();
    }
}
