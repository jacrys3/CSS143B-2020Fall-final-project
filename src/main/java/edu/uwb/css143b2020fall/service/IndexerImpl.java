package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < docs.size(); i++) {
            String str = docs.get(i);
            String[] data = str.split(" ");
            for(int k = 0; k < data.length; k++)
                words.add(data[k]);
        }

        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }
        return indexes;
    }
}