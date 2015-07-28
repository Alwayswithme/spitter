package me.phx.controler;

import me.phx.model.Device;
import me.phx.model.House;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping(value = "consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consume(@RequestBody Device device) {
        System.out.println(device);
        return device.toString();
    }

    @RequestMapping(value = "run")
    public Map<String,Object> run(String... strings) throws Exception {
        Map<String, Object> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        Map quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Map.class);
        result.put("quote", quote);

        RestTemplate restTemplate1 = new RestTemplate();
        ParameterizedTypeReference<List<Map<String, Object>>> responseType1 = new ParameterizedTypeReference<List<Map<String, Object>>>() {};
        ResponseEntity<List<Map<String, Object>>> exchange1 = restTemplate1.postForEntity("http://localhost:8080/house/selectHouseAsMaps", null, null, responseType1);
        result.put("exchange1", exchange1.getBody());

        RestTemplate restTemplate2 = new RestTemplate();
        ParameterizedTypeReference<List<House>> responseType2 = new ParameterizedTypeReference<List<House>>() {};
        ResponseEntity<List<House>> exchange2 = restTemplate2.exchange("http://localhost:8080/house/selectHouseAsObject", HttpMethod.POST, null, responseType2);
        result.put("exchange2", exchange2.getBody());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/json/consume");
        Device d = new Device();
        d.setId(1);
        d.setName("test");
        d.setOwnerId(2);
        d.setType(Device.Type.PC);
        RestTemplate restTemplate3 = new RestTemplate();
        String exchange3 = restTemplate3.postForObject(builder.build().encode().toUri(), d, String.class);
        result.put("exchange3", exchange3);
        return result;
    }
}
