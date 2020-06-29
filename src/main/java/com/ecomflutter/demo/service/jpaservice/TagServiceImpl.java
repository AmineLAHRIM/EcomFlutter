package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.Tag;
import com.ecomflutter.demo.dao.TagDao;
import com.ecomflutter.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Override
    public List<Tag> save(Product product, List<Tag> tags) {
        List<Tag> savedTags = new ArrayList<>();
        tags.forEach(tag -> {
            tag.setProduct(product);
            savedTags.add(this.tagDao.save(tag));
        });
        return savedTags;
    }


    @Override
    public List<Tag> findAllByProductId(Long productId) {
        return this.tagDao.findAllByProduct_Id(productId);
    }

    @Transactional
    @Override
    public int deleteAllByProductId(Long productId) {
        return this.tagDao.deleteAllByProduct_Id(productId);

    }
}
