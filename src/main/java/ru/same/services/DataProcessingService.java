package ru.same.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.same.entities.Source;
import ru.same.repositories.DestinationRepository;
import ru.same.repositories.SourceRepository;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DataProcessingService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private Environment environment;
    @Value("${load.dataPattern}")
    private String pattern;
    @Value("${load.pageSize}")
    private int pageSize;

    public void invoke() {
        long rows = sourceRepository.count();
        int pages = (int) (rows / pageSize + 1);
        AtomicInteger pageCounter = new AtomicInteger(0);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < pool.getParallelism() && i < pages; i++) {
            PageHandler handler = new PageHandler(pageCounter, pages, pageSize, destinationRepository, sourceRepository, pattern);
            pool.execute(handler);
        }
        PageHandler handler = new PageHandler(pageCounter, pages, pageSize, destinationRepository, sourceRepository, pattern);
        while (pageCounter.intValue() < pages) {
            handler.compute();
        }
    }

    public void generateData() {
        for (int i = 0; i < 100000; i++) {
            StringBuilder builder = new StringBuilder(RandomStringUtils.randomAlphanumeric(1000, 2000)).append(" http://")
                    .append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" ").append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" http://").append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" ").append(RandomStringUtils.randomAlphanumeric(10000, 20000));
//            System.out.println(builder.toString());
            Source source = new Source(builder.toString());
            sourceRepository.save(source);
        }
    }
}
