package upt.proj.condominio.client.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import upt.proj.condominio.model.Mensagens;


@Service
public class MensagensClientService {

        @Autowired
        private RestTemplate restTemplate;
    
        private static final String BASE_URL = "http://localhost:8080/api/mensagens";
    
        public Mensagens getMensagensBySender(String username) {
            String url = BASE_URL + "/sender/" + username;
            return restTemplate.getForObject(url, Mensagens.class);
        }

        public Mensagens getMensagensByRecipient(String username) {
            String url = BASE_URL + "/recipient/" + username;
            return restTemplate.getForObject(url, Mensagens.class);
        }
    
        public void createMensagem(String sender, String recipient, String message) {
            String url = BASE_URL;
            Map<String,Object> map = new HashMap<>();
            map.put("sender",sender);
            map.put("recipient",recipient);
            map.put("message",message);
            
            try {
                restTemplate.postForObject(url,map,Void.class);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
        
}
