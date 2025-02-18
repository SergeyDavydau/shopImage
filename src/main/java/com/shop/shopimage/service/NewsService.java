package com.shop.shopimage.service;

import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import com.shop.shopimage.repo.NewsRepo;
import com.shop.shopimage.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class NewsService {


    private final NewsRepo newsRepo;
    private final UserRepo userRepo;

    @Autowired
    public NewsService(NewsRepo newsRepo, UserRepo userRepo) {
        this.newsRepo = newsRepo;
        this.userRepo = userRepo;
    }

    public News saveNews(News news, User user) {
        news.setAuthorId(user);
//        news.setAuthorId( userRepo.findById(user.getId()).orElse(null));
        news.setCreateDate(new Date(System.currentTimeMillis()));

//        user.getNewsList().add(news);
//        userRepo.save(user);

        return newsRepo.save(news);
    }
}
