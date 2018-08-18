package ru.same.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.same.entities.Source;
import ru.same.repositories.DestinationRepository;
import ru.same.repositories.SourceRepository;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class PageHandler extends RecursiveAction {
    private AtomicInteger pageCounter;
    private int pages;
    private int pageSize;
    private DestinationRepository destinationRepository;
    private SourceRepository sourceRepository;

    public PageHandler(AtomicInteger pageCounter, int pages, int pageSize, DestinationRepository destinationRepository, SourceRepository sourceRepository) {
        this.pageCounter = pageCounter;
        this.pages = pages;
        this.pageSize = pageSize;
        this.sourceRepository = sourceRepository;
        this.destinationRepository = destinationRepository;
    }

    @Override
    protected void compute() {
        int current = pageCounter.getAndIncrement();
        if (current < pages) {
            Pageable pageable = PageRequest.of(current, pageSize);
            Iterable<Source> iterable = sourceRepository.findAll(pageable);
            iterable.forEach(System.out::print);
            System.out.println(Thread.currentThread().getName() + " todo " + current);
            //todo обрабатываем текущую страницу
            invoke();
        }
    }
}
