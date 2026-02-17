package cto.iamskrai.apexmatch.controller;

import cto.iamskrai.apexmatch.dto.OrderBookResponseDTO;
import cto.iamskrai.apexmatch.dto.OrderRequestDTO;
import cto.iamskrai.apexmatch.dto.OrderResponseDTO;
import cto.iamskrai.apexmatch.model.Order;
import cto.iamskrai.apexmatch.service.MatchingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final MatchingService matchingService;

    public OrderController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping
    public OrderResponseDTO addOrderController(@Valid @RequestBody OrderRequestDTO request){

        Order order = new Order(
                request.getId(),
                request.getPrice(),
                request.getQty(),
                request.getType()
        );

        matchingService.addOrder(order);
        return new OrderResponseDTO(order.getId(), "Accepted");
    }

    @GetMapping("/book")
    public OrderBookResponseDTO getOrderBook(){
        return matchingService.getOrderBookSnapshot();
    }

}
