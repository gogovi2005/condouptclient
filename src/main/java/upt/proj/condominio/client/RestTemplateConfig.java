package upt.proj.condominio.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upt.proj.condominio.model.Apartamento;
import upt.proj.condominio.model.Predio;

@Service
public class PredioClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/predios"; // URL do servidor REST

    // Método para buscar um prédio pelo nome (GET)
    public Predio getPredioByNome(String nome) {
        String url = BASE_URL + "/nome/" + nome;
        return restTemplate.getForObject(url, Predio.class); // Faz o GET e converte para Predio
    }

    // Método para adicionar um apartamento a um prédio (POST)
    public void addApartamentoToPredio(Long predioId, Apartamento apartamento) {
        String url = BASE_URL + "/" + predioId + "/apartamentos";
        restTemplate.postForObject(url, apartamento, Void.class); // Faz o POST
    }
}
