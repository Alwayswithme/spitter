package me.phx.controler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.phx.mapper.PersonMapper;
import me.phx.model.Device;
import me.phx.model.House;
import me.phx.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
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
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person consume(@RequestBody Device device,
                          @RequestParam String jwt) {
        System.out.println("===> 接受到 object <" + device + ">");
        Person person = personMapper.selectByPrimaryKey(device.getOwnerId());
        System.out.println("===> 发送了 object <" + person + ">");
        return person;
    }

    @RequestMapping(value = "run")
    public Map<String,Object> run() throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Map.class);
        result.put("quote", quote);

        return result;
    }

    @RequestMapping(value = "run3")
    public Person run3() throws Exception {
        String jwt = "secret";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/json/consume")
                .queryParam("jwt", jwt);
        String url = builder.build().encode().toString();
        Device d = new Device();
        d.setId(1);
        d.setName("test");
        d.setOwnerId(2);
        d.setType(Device.Type.PC);
        return restTemplate.postForObject(url, d, Person.class);
    }

    @RequestMapping(value = "run4")
    public Person run4() {
        HttpHeaders requestHeaders = new HttpHeaders();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
        mvm.add("jwt", "123");
        mvm.add("id", Integer.toString(1));
        mvm.add("ownerId", Integer.toString(2));
        mvm.add("name", "test");
        mvm.add("type", "PC");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(mvm, requestHeaders);
        return restTemplate.postForObject("http://localhost:8080/json/consume", entity, Person.class);
    }

    @RequestMapping(value = "run1")
    public RestObject run1() throws Exception {
        String url = "http://localhost:8080/house/selectHouseAsMaps";
        HttpHeaders headers = new HttpHeaders();
        headers.set("t", String.valueOf(System.currentTimeMillis()));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        return restTemplate.postForObject(url, httpEntity, RestObject.class);
    }

    @RequestMapping(value = "run2")
    public List<House> run2() throws Exception {
        String url = "http://localhost:8080/house/selectHouseAsObject";
        ParameterizedTypeReference<List<House>> responseType2 = new ParameterizedTypeReference<List<House>>() {};
        ResponseEntity<List<House>> exchange2 = restTemplate.exchange(url, HttpMethod.POST, null, responseType2);
        return exchange2.getBody();
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class RestObject {
    boolean ok;
    List<House> houses;
    List<Map<String, Object>> maps;
}