package com.wholesail.invoice;

import com.wholesail.invoice.data.entity.Seller;
import com.wholesail.invoice.manager.SellerManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvoiceH2ApplicationTests {

    @Autowired
    private SellerManager sellerManager;

    @Test
    void contextLoads() {
        final List<Seller> sellers = sellerManager.listSellers();
        sellers.forEach(seller -> System.out.println(seller.getId()));
    }

}
