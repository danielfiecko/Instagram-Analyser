package com.dfiecko.instagramAnalyser.algorithms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AlgorithmFactory {

    private final Collection<MiningStrategy> strategies;
    @Getter
    private Map<AlgorithmType, MiningStrategy> cache;

    @PostConstruct
    private void init() {
        cache = new HashMap<>(strategies.size());
        strategies.forEach(miningStrategy -> cache.put(miningStrategy.supports(), miningStrategy));
    }

    public MiningStrategy getMiningStrategy(AlgorithmType algorithmType) {
        return cache.get(algorithmType);
    }
}
