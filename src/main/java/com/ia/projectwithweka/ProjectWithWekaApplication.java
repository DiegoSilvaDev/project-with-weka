package com.ia.projectwithweka;

import com.ia.ml.weka.DeteccaoDeFraudeBancaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

// Ferramentas de log usadas para ocultar avisos do Weka (não afetam o funcionamento do código)
import java.util.logging.Level; // Controla o nível de importância dos avisos exibidos
import java.util.logging.Logger; // Usado para configurar o sistema de logs do java

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjectWithWekaApplication {

    public static void main(String[] args) {
        // Oculta avisos sobre bibliotecas nativas ANTES do Spring Boot inicializar (não afetam o funcionamento)
        Logger.getLogger("com.github.fommil.netlib").setLevel(Level.SEVERE);

        SpringApplication.run(ProjectWithWekaApplication.class, args);
    }

    // O CommandLineRunner garante que este código rode automaticamente APÓS o Spring ser totalmente inicializado
    @Bean
    public CommandLineRunner executarDetector(){
        return  args -> {
            DeteccaoDeFraudeBancaria detector = new DeteccaoDeFraudeBancaria();

            try {
                // Definir atributos
                detector.definirAtributos();

                // Adição de exemplos
                detector.adicionarExemplos();

                // Treinar modelo
                detector.treinarModelo();

                // Classificar novas transações
                String resultado1 = detector.classificarTransacao(5000, "internacional");
                String resultado2 = detector.classificarTransacao(200, "nacional");
                String resultado3 = detector.classificarTransacao(10000, "internacional");
                String resultado4 = detector.classificarTransacao(150, "nacional");
                String resultado5 = detector.classificarTransacao(7000, "internacional");
                String resultado6 = detector.classificarTransacao(300, "nacional");
                String resultado7 = detector.classificarTransacao(8000, "internacional");
                String resultado8 = detector.classificarTransacao(400, "nacional");

                String resultado9 = detector.classificarTransacao(9000, "nacional");
                String resultado10 = detector.classificarTransacao(309, "internacional");

                // Impressão dos resultados
                System.out.println("Teste 1: " + resultado1);
                System.out.println("Teste 2: " + resultado2);
                System.out.println("Teste 3: " + resultado3);
                System.out.println("Teste 4: " + resultado4);
                System.out.println("Teste 5: " + resultado5);
                System.out.println("Teste 6: " + resultado6);
                System.out.println("Teste 7: " + resultado7);
                System.out.println("Teste 8: " + resultado8);

                System.out.println("Teste 9: " + resultado9);
                System.out.println("Teste 10: " + resultado10);

            } catch (Exception e) {
                System.err.println("Erro ao classificar a transação: " + e.getMessage());
            }
        };
    }


}
