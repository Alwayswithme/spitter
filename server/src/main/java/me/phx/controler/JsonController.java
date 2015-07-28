package me.phx.controler;

import me.phx.mapper.PersonMapper;
import me.phx.model.Device;
import me.phx.model.House;
import me.phx.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    PersonMapper personMapper;

    @RequestMapping(value = "consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person consume(@RequestBody Device device,
                          @RequestParam String jwt) {
        System.out.println("consume object <" + device + ">");
        Person person = personMapper.selectByPrimaryKey(device.getOwnerId());
        System.out.println("produce object <" + person + ">");
        return person;
    }

    @RequestMapping(value = "run")
    public Map<String,Object> run(String... strings) throws Exception {
        Map<String, Object> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        Map quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Map.class);
        result.put("quote", quote);

        RestTemplate restTemplate1 = new RestTemplate();
        ParameterizedTypeReference<List<Map<String, Object>>> responseType1 = new ParameterizedTypeReference<List<Map<String, Object>>>() {};
        Map[] maps = restTemplate1.postForObject("http://localhost:8080/house/selectHouseAsMaps", null, Map[].class);
        result.put("exchange1", maps);

        RestTemplate restTemplate2 = new RestTemplate();
        ParameterizedTypeReference<List<House>> responseType2 = new ParameterizedTypeReference<List<House>>() {};
        ResponseEntity<List<House>> exchange2 = restTemplate2.exchange("http://localhost:8080/house/selectHouseAsObject", HttpMethod.POST, null, responseType2);
        result.put("exchange2", exchange2.getBody());

        String jwt = "secret";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/json/consume")
                .queryParam("jwt", jwt);
        String url = builder.build().encode().toString();
        Device d = new Device();
        d.setId(1);
        d.setName("test");
        d.setOwnerId(2);
        d.setType(Device.Type.PC);
        RestTemplate restTemplate3 = new RestTemplate();
        Person exchange3 = restTemplate3.postForObject(url, d, Person.class);
        result.put("exchange3", exchange3);
        return result;
    }
}
