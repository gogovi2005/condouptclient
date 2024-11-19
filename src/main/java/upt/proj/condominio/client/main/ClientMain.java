package upt.proj.condominio.client.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upt.proj.condominio.model.Apartamento;
import upt.proj.condominio.model.Predio;
import java.util.Scanner;
import upt.proj.condominio.client.service.PredioClientService;

public class ClientMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual o nome do prédio?");
		String nome = sc.nextLine();
		
		 PredioClientService predioClientService = new PredioClientService();
		 Predio predio = predioClientService.getPredioByNome(nome);
		 
		 if (predio != null) {
	            System.out.println("Prédio encontrado: " + predio.getNomeP());
	        } else {
	            System.out.println("Prédio não encontrado.");
	        }

	        sc.close();

	}

}
