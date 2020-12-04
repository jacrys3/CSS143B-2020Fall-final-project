package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        Map<String, String> words = new HashMap<>();

        for(int i = 0; i < docs.size(); i++){ // adds all the words from the docs to a map
            String str = docs.get(i);
            String[] title = str.split(" ");
            for (int j = 0; j < title.length; j++) {
                if(title[j].equals("")) continue;
                if(words.get(title[j]) != null) continue;
                words.put(title[j], title[j]);
            }
        }

        Collection<String> wordList = words.values();
        Object[] word = wordList.toArray();

        for (int i = 0; i < word.length; i++) {
            List<List<Integer>> pos = new ArrayList<>();
            for (int j = 0; j < docs.size(); j++) {
                String str = docs.get(j);
                String[] line = str.split(" ");
                List<Integer> doc = new ArrayList<>();
                int count = 0;
                for (int k = 0; k < line.length; k++) {
                    if (word[i].equals(line[k])) {
                        doc.add(count);
                    }
                    if(!line[k].equals("")) count++; // This is here because split still counts "" as a word and so
                    // count will not increment if the word is "" because that is not actually a word.
                }
                pos.add(doc);
            }
            indexes.put((String) word[i], pos);
        }

        return indexes;
    }
}