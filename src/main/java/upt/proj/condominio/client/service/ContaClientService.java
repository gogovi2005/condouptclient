package upt.proj.condominio.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upt.proj.condominio.model.User;
import upt.proj.condominio.model.Empresa;
import upt.proj.condominio.model.DonoPredio;
import upt.proj.condominio.model.Conta;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContaClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/conta";

    public Conta getContaByUsername(String username) {
        String url = BASE_URL + "/username/" + username;
        return restTemplate.getForObject(url, Conta.class);
    }

    public Conta getContaByEmail(String email) {
        String url = BASE_URL + "/email/" + email;
        return restTemplate.getForObject(url, Conta.class);
    }
    
    public void createUser(String username,String email,String password,Integer idade) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("tipo","Morador");
        map.put("username",username);
        map.put("email",email);
        map.put("password",password);
        map.put("idade",idade);

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }

    }

    public void createDonoP(String username,String email,String password,Integer idade) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("tipo","DonoPredio");
        map.put("username",username);
        map.put("email",email);
        map.put("password",password);
        map.put("idade",idade);

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace();}
    }

    public void createEmpresa(String username,String email,String password,String tiposervice, String zona, float preco) {
        String url = BASE_URL;
        Map<String,Object> map = new HashMap<>();
        map.put("tipo","Empresa");
        map.put("username",username);
        map.put("email",email);
        map.put("tipo_de_serviços",tiposervice);
        map.put("zona",zona);
        map.put("preco",preco);

        try {
            restTemplate.postForObject(url,map,Void.class);
        } catch (Exception e) {
            e.printStackTrace(); }
        }
    }
