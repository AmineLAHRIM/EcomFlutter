package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankDao extends JpaRepository<Rank,Long> {
}
