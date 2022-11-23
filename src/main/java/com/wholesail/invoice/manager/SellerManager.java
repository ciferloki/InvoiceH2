package com.wholesail.invoice.manager;

import com.wholesail.invoice.data.entity.Seller;
import com.wholesail.invoice.data.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerManager {

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> listSellers() {
        return sellerRepository.findAll();
    }
}
