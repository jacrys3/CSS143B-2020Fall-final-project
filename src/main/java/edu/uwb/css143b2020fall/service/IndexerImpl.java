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
        List<List<Integer>> pos = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        //clearList(tmp, docs.size());

        for (int i = 0; i < docs.size(); i++) {
            String str = docs.get(i);
            String[] title = str.split(" ");
            for(int k = 0; k < title.length; k++){
                if(indexes.get(title[k]) != null){
                    pos = indexes.get(title[k]);
                    tmp = pos.get(i);
                    tmp.add(k);
                    indexes.replace(title[k], pos);
                } else{
                    tmp = new ArrayList<>();
                    tmp.add(k);
                    pos.add(tmp);
                    indexes.put(title[k], pos);
                }
            }
            //try to have separate for loops for adding to the individual doc lists and one for adding those lists to the hashmap
        }

        return indexes;
    }
}