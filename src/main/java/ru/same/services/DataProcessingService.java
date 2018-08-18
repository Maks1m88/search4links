package ru.same.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.same.entities.Source;
import ru.same.repositories.DestinationRepository;
import ru.same.repositories.SourceRepository;

import java.util.LinkedList;
import java.util.List;
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

    public void invoke() {
        long rows = sourceRepository.count();
        int pageSize = Integer.parseInt(environment.getProperty("load.pageSize")), pages = (int) (rows / pageSize + 1);
        AtomicInteger pageCounter = new AtomicInteger(0);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        List<PageHandler> tasks = new LinkedList<>();
        for (int i = 0; i < pool.getParallelism() && i < pages; i++) {
            PageHandler handler = new PageHandler(pageCounter, pages, pageSize, destinationRepository, sourceRepository);
            pool.execute(handler);
        }
        System.out.println(pages);
    }

    public void generateData(){
        for (int i = 0;i<100000;i++){
            StringBuilder builder = new StringBuilder(RandomStringUtils.randomAlphanumeric(1000, 2000)).append(" http://")
                    .append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" ").append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" http://").append(RandomStringUtils.randomAlphanumeric(1000, 2000))
                    .append(" ").append(RandomStringUtils.randomAlphanumeric(10000, 20000));
//            System.out.println(builder.toString());
            Source source=new Source(builder.toString());
            sourceRepository.save(source);
//            builder.append()
        }
    }
}
