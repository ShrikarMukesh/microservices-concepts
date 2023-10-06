package com.orderservice;

//import com.orderservice.dto.InventoryResponse;
//import com.orderservice.dto.OrderLineItemsDto;
//import com.orderservice.dto.OrderRequest;
//import com.orderservice.event.OrderPlacedEvent;
//import com.orderservice.model.Order;
//import com.orderservice.repository.OrderRepository;
//import com.orderservice.service.OrderService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.CompletableFuture;
//
//import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
//public class OrderServiceTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @Mock
//    private WebClient.Builder webClientBuilder;
//
//    @Mock
//    private Tracer tracer;
//
//    @Mock
//    private StreamBridge streamBridge;
//
//    @Mock
//    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
//
//    @Mock
//    private Span span;
//
//    private OrderService orderService;
//
//
//}
