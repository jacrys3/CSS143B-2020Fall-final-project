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
                if(words.get(title[j]) != null){
                    continue;
                }
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
                for (int k = 0; k < line.length; k++) {
                    if (word[i].equals(line[k])) {
                        doc.add(k);
                    }
                }
                pos.add(doc);
            }
            indexes.put((String) word[i], pos);
        }

        /*for (int i = 0; i < docs.size(); i++) { //goes through each doc
            List<List<Integer>> pos = new ArrayList<>();
            String str = docs.get(i);
            String[] title = str.split(" ");
            for (int k = 0; k < docs.size(); k++) { // goes through each doc again to get words to compare
                List<Integer> doc = new ArrayList<>();
                String str2 = docs.get(k);
                String[] strin = str2.split(" ");
                for (int j = 0; j < strin.length; j++) { // goes through each word in strin and compares to the first word in title
                    if (title[i].equals(strin[j])) { // if i word in title is the same as the word in strin add index to list
                        doc.add(j);
                    }
                }
                pos.add(doc);
            }
            indexes.put(title[i], pos);
        }*/

        return indexes;
    }
}