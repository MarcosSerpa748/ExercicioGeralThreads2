package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Utilitarios {

    public static void fecharPool(
            ExecutorService workers,
            AtomicInteger valorTotalSacado,
            ConcurrentHashMap<String,Integer> quantidadeStatus){
        workers.shutdown();

        try {
            if (!workers.awaitTermination(5, TimeUnit.SECONDS)){
                workers.shutdownNow();
            }

            System.out.println("Valor total sacado foi de R$"+valorTotalSacado);
            for(String key:quantidadeStatus.keySet()){
                System.out.println("O status de "+key+" teve o número de chamado "+quantidadeStatus.get(key));
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            workers.shutdownNow();
        }
    }
}
