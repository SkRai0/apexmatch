package cto.iamskrai.apexmatch.controller;

import cto.iamskrai.apexmatch.OrderResponseDTO;
import cto.iamskrai.apexmatch.model.Order;
import cto.iamskrai.apexmatch.service.MatchingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final MatchingService matchingService;

    public OrderController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping
    public OrderResponseDTO addOrderController(@Valid @RequestBody Order order){
        matchingService.addOrder(order);
        return new OrderResponseDTO(order.getId(), "Accepted");
    }

}
