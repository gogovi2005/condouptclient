package upt.proj.condominio.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import upt.proj.condominio.model.Predio;

@Service
public class PredioClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/predio"; 

    
    public Predio getPredioByNome(String nome) {
        String url = BASE_URL + "/predio/" + nome;
        return restTemplate.getForObject(url, Predio.class); 
    }
}
