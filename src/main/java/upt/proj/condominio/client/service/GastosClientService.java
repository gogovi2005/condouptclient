package upt.proj.condominio.client.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import upt.proj.condominio.model.Gastos;

@Service
public class GastosClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/gastos";

    public Gastos getGastosByUsername(String username) {
        String url = BASE_URL + "/username/" + username;
        return restTemplate.getForObject(url, Gastos.class);
    }

    public void createGastos(Integer userId, Integer mes, Integer ano, float agua, float gas, float eletricidade) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("mes",mes);
        map.put("ano",ano);
        map.put("agua",agua);
        map.put("gas",gas);
        map.put("eletricidade",eletricidade);
        map.put("userId",userId);
        
        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }
    	}
    }