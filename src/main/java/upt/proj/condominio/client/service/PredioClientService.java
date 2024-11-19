package upt.proj.condominio.client.service;

import java.util.HashMap;
import java.util.Map;

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
    public void createPredio(Integer donoPredId, String nomeP, String zona, Integer ntotalapart, Boolean animaisP, Float totalAgua, Float totalLuz, Float totalGas) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("noneP", nomeP);
        map.put("zona",zona);
        map.put("ntotalapart",ntotalapart);
        map.put("animaisP",animaisP);
        map.put("totalAgua",totalAgua);
        map.put("totalLuz",totalLuz);
        map.put("totalGas",totalGas);
        map.put("donoPredId",donoPredId);
        

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }
    	}
}
