package org.example;

public class CofreBanco {
    private static Integer saldo = 5000;


    public static synchronized Boolean fazerSaque(Integer valorSaque){
        if (Thread.currentThread().isInterrupted()){
            return false;
        }

        if(saldo > valorSaque){
            saldo -= valorSaque;
            return true;
        }else{
            return false;
        }
    }
}
