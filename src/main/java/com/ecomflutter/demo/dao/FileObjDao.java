package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.FileObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileObjDao extends JpaRepository<FileObj, Long> {
}
