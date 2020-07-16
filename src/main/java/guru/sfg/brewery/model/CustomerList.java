package guru.sfg.brewery.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerList extends PageImpl<CustomerDto> {
    public CustomerList(List<CustomerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomerList(List<CustomerDto> content) {
        super(content);
    }
}
