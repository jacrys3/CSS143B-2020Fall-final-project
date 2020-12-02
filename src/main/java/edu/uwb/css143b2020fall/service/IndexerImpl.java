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

        for (int i = 0; i < docs.size(); i++) {
            List<List<Integer>> pos = new ArrayList<>();
            String str = docs.get(i);
            String[] title = str.split(" ");
            for(int k = 0; k < docs.size(); k++){
                List<Integer> doc = new ArrayList<>();
                String str2 = docs.get(k);
                String[] strin = str2.split(" ");
                for (int j = 0; j < strin.length; j++) {
                    if(title[i].equals(strin[j])){
                        doc.add(j);
                    }
                }
                pos.add(doc);
            }

            indexes.put(title[i], pos);
        }

        return indexes;
    }
}