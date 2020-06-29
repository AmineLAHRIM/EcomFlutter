package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.Upsell;
import com.ecomflutter.demo.dao.UpsellDao;
import com.ecomflutter.demo.service.UpsellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpsellServiceImpl implements UpsellService {

    @Autowired
    private UpsellDao upsellDao;


    @Override
    public List<Upsell> save(Product product, List<Upsell> upsells) {
        List<Upsell> savedUpsells = new ArrayList<>();
        upsells.forEach(upsell -> {
            upsell.setProduct(product);
            savedUpsells.add(this.upsellDao.save(upsell));
        });
        return savedUpsells;
    }


    @Override
    public List<Upsell> findAllByProductId(Long productId) {
        return this.upsellDao.findAllByProduct_Id(productId);
    }

    @Transactional
    @Override
    public int deleteAllByProductId(Long productId) {
        return this.upsellDao.deleteAllByProduct_Id(productId);

    }
}
