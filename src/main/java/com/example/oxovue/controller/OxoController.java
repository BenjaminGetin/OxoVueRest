package com.example.oxovue.controller;


import com.example.oxovue.entity.Oxo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OxoController {

    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index(Model model) {
        this.restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/sondages/";
        ResponseEntity<List<Oxo>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Oxo>>() {});
        List<Oxo> oxos = response.getBody();

        model.addAttribute("oxos", oxos);
        return "index";
    }

    @GetMapping("/oxos/{id}")
    public String getOxo(Model model, @PathVariable long id) {
        this.restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/sondages/{id}";
        ResponseEntity<Oxo> response = restTemplate.getForEntity(url, Oxo.class, id);
        Oxo oxo = response.getBody();

        model.addAttribute("oxo", oxo);

        return "oxo";
    }

    @GetMapping("/oxos/form/add")
    public String formOxo(Model model) {
        Oxo oxo = new Oxo();
        model.addAttribute("oxo", oxo);
        return "form";
    }

    @GetMapping("/oxos/maj/{id}")
    public String majOxo(Model model, @PathVariable long id) {
        this.restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/sondages/{id}";
        ResponseEntity<Oxo> response = restTemplate.getForEntity(url, Oxo.class, id);
        Oxo oxo = response.getBody();

        model.addAttribute("oxo", oxo);
        return "form";
    }

    @GetMapping("/oxos/del/{id}")
    public String delOxo(Model model, @PathVariable long id) {
        this.restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/sondages/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);

        return "redirect:/";
    }

    @PostMapping("/oxos/maj/{id}")
    public String updateOxo(@ModelAttribute("oxo") Oxo oxo, @PathVariable long id) {

        String url = "http://localhost:8080/api/sondages/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Oxo> request = new HttpEntity<>(oxo, headers);
        ResponseEntity<Oxo> response = restTemplate.exchange(url, HttpMethod.PUT, request, Oxo.class, id);

        return "redirect:/";
    }

    @PostMapping("/oxos/form/add")
    public String addOxo(@ModelAttribute("oxo") Oxo oxo) {
        this.restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/sondages/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Oxo> request = new HttpEntity<>(oxo, headers);
        ResponseEntity<Oxo> response = restTemplate.postForEntity(url, request, Oxo.class);

        return "redirect:/";
    }
}
