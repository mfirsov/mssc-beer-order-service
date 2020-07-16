package guru.sfg.beer.order.service.services;

import guru.sfg.brewery.model.CustomerList;

import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerList getCustomerList(Pageable pageable);

}
