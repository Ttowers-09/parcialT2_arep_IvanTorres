package com.parcialT2.parcial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CollatzController {
    @GetMapping("/collatz")
    public String collatz (String number){
        System.out.println("Si tiene entrada: ");
        System.out.println(number);
        int entrada = Integer.parseInt(number);
        int valorTemporal = 0;
        List <String> valoresFinales = new ArrayList<>();
        valoresFinales.add(entrada + " -> ");

        for(int i=0; i<100;i++){
            System.out.println("Enttro al bucle");
            if (entrada % 2 == 0){
                valorTemporal = entrada / 2;
                if (entrada == 1){
                    valoresFinales.add(valorTemporal + "");
                }
                valoresFinales.add(valorTemporal + " -> ");
                entrada = valorTemporal;
                if(valorTemporal==1){
                    break;
                }
            }else{
                valorTemporal = (3*entrada) + 1;
                if(valorTemporal != 1){
                    valoresFinales.add(valorTemporal + " -> ");
                    entrada = valorTemporal;
                }else{
                    valoresFinales.add(valorTemporal + "");
                }
               
                if(valorTemporal == 1){
                    break;
                }
            }
    
        }
        
        return "{" + "operation: 'collatz', " +
        "\nlistaValores: '" + String.join(",", valoresFinales) + "', " +
        "\nvalorBuscado: '" + entrada + "', " +
        "}";

    }
}

