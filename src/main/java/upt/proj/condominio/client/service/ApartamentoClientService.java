package upt.proj.condominio.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upt.proj.condominio.model.Apartamento;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApartamentoClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/apartamento";

    public Apartamento getApartamentoByUsername(String username) {
        String url = BASE_URL + "/username/" + username;
        return restTemplate.getForObject(url, Apartamento.class);
    }

    public void createApartamento(Integer tamanho, Boolean garagem, Integer nResidentes, Integer wc, Integer andar, Character fracao, String ocupante, Integer nApartamento) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("tamanho",tamanho);
        map.put("garagem",garagem);
        map.put("n_residentes",nResidentes);
        map.put("wc",wc);
        map.put("andar",andar);
        map.put("fracao",fracao);
        map.put("username_ocupante",ocupante);
        map.put("n_apartamento",nApartamento);

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }
    	}

    }