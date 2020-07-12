package guru.sfg.beer.order.service.services.listeners;

import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeerOrderAllocationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(Message message) {
        AllocateOrderRequest request = (AllocateOrderRequest) message.getPayload();

        request.getBeerOrderDto().getBeerOrderLines()
                .forEach(beerOrderLineDto -> beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity()));

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, AllocateOrderResult.builder()
                .beerOrderDto(request.getBeerOrderDto())
                .pendingInventory(false)
                .allocationError(false)
                .build());
    }

}
