package upt.proj.condominio.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upt.proj.condominio.model.Apartamento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApartamentoClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/apartamento";

    public List<Apartamento> getApartamentoByDonoUsername(String username) {
        String url = BASE_URL + "/dono/" + username;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Apartamento>>() {}).getBody();
    }

    public Apartamento getApartamentoByOcupanteUsername(String username) {
        String url = BASE_URL + "/ocupante/" + username;
        return restTemplate.getForObject(url, Apartamento.class);
    }

    public void createApartamento(Integer predioId, Integer tamanho, Boolean garagem, Integer nResidentes, Integer wc, Integer andar, Character fracao, Integer nApartamento) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("nApartamento",nApartamento);
        map.put("tamanho",tamanho);
        map.put("garagem",garagem);
        map.put("nResidentes",nResidentes);
        map.put("wc",wc);
        map.put("andar",andar);
        map.put("fracao",fracao);
        map.put("predioId",predioId);
        

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }
    	}
    }