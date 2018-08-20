package ru.same.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.same.entities.Destination;
import ru.same.entities.Source;
import ru.same.repositories.DestinationRepository;
import ru.same.repositories.SourceRepository;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageHandler extends RecursiveAction {
    private AtomicInteger pageCounter;
    private int pages;
    private int pageSize;
    private DestinationRepository destinationRepository;
    private SourceRepository sourceRepository;
    private String pattern;

    public PageHandler(AtomicInteger pageCounter, int pages, int pageSize, DestinationRepository destinationRepository, SourceRepository sourceRepository, String pattern) {
        this.pageCounter = pageCounter;
        this.pages = pages;
        this.pageSize = pageSize;
        this.sourceRepository = sourceRepository;
        this.destinationRepository = destinationRepository;
        this.pattern = pattern;
    }

    @Override
    protected void compute() {
        int current = pageCounter.getAndIncrement();
        if (current < pages) {
            Pageable pageable = PageRequest.of(current, pageSize,Sort.Direction.ASC,"id");
            Iterable<Source> iterable = sourceRepository.findAll(pageable);
            iterable.forEach((e) -> {
                Matcher m = Pattern.compile(pattern).matcher(e.getData());
                System.out.println(e.getId());
                while (m.find()) {
                    String currentData = m.group().toLowerCase();
//                    System.out.println(currentData);
                    Destination destination = new Destination(currentData);
                    destinationRepository.save(destination);
                }
            });
            invoke();
        }
    }
}
