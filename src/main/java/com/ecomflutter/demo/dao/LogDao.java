package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<Log,Long> {
}
