package com.example.marketinall.repository;

import com.example.marketinall.domain.entity.ItemFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemFileRepository extends JpaRepository<ItemFile, Long> {

    void deleteByFileName(String fileName);
}
