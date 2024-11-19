package upt.proj.condominio.client.main;


import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import upt.proj.condominio.client.config.AppConfig;
import upt.proj.condominio.client.service.PredioClientService;
import upt.proj.condominio.model.Predio;

public class ClientMain {

	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Scanner sc = new Scanner(System.in);
		
        System.out.println("Qual o nome do prédio?");
        String nome = sc.nextLine();
        
        PredioClientService predioClientService = context.getBean(PredioClientService.class);
        Predio predio = predioClientService.getPredioByNome(nome);
        
        if (predio != null) {
            System.out.println("Prédio encontrado: " + predio.getNomeP());
        } else {
            System.out.println("Prédio não encontrado.");
        }

        sc.close();
        context.close();

	}

}
