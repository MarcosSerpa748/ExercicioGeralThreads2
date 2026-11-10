package org.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ExecutorService workers = Executors.newFixedThreadPool(4);
        ScheduledExecutorService scheduledWorkers = Executors.newScheduledThreadPool(1);
        ConcurrentHashMap<String,Integer> quantidadeStatus = new ConcurrentHashMap<>();
        AtomicInteger valorTotalSacado = new AtomicInteger(0);

        quantidadeStatus.put("APROVADO",0);
        quantidadeStatus.put("NEGADO",0);

        for (int i = 1;i<=16;i++){

            CompletableFuture.supplyAsync(()->{
                return CofreBanco.fazerSaque(500);
            },workers).thenAccept(valorPassado ->{
                if (valorPassado){
                    quantidadeStatus.merge("APROVADO",1,Integer::sum);
                    valorTotalSacado.getAndAdd(500);
                }else{
                    quantidadeStatus.merge("NEGADO",1,Integer::sum);
                }
            });
        }

        Utilitarios.fecharPool(workers,valorTotalSacado,quantidadeStatus);

    }
}