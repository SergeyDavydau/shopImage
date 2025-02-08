package com.shop.shopimage.repo;

import com.shop.shopimage.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository <News, Long> {
}
