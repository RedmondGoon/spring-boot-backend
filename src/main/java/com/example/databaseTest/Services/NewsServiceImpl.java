package com.example.databaseTest.Services;

import com.example.databaseTest.ExternalAPI.NewyorkTimes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Override
    public List<String> getLatestHeadlines() {
        return NewyorkTimes.getNews();
    }
}
