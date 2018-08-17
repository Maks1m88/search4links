package ru.same.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.same.repositories.DestinationRepository;
import ru.same.repositories.SourceRepository;

@Component
public class Process {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private SourceRepository sourceRepository;

    public void process(){
        System.out.println("go");
    }
}
