package com.ecommercejob.profils.api;

import com.ecommercejob.profils.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    private final KafkaProducerService producer;

    public KafkaTestController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @GetMapping("/kafka/publish")
    public String publish(@RequestParam(defaultValue = "hello") String m) {
        producer.send(m);
        return "OK";
    }
}
