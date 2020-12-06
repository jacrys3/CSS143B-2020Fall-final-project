package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        boolean isRealWord = true;
        String[] words = keyPhrase.split(" ");

        for (int i = 0; i < words.length; i++) {
            List<Integer> docIndexes = getDocs(words[i], index);
            if (docIndexes == null || docIndexes.size() == 0) {
                isRealWord = false;
            } else if (words.length == 1) {
                return docIndexes;
            } else {
                isRealWord = true;
            }
        }
        if (!isRealWord) {
            return new ArrayList<>();
        }
        List<Integer> allWordsGood = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            allWordsGood = compareDocs(getDocs(words[i], index), getDocs(words[i + 1], index));
        }
        return allWordsGood;
    }

    private List<Integer> getDocs(String word, Map<String, List<List<Integer>>> source) {
        List<Integer> indexes = new ArrayList<>();

        List<List<Integer>> tmp = source.get(word);
        if (tmp != null) {
            for (int i = 0; i < tmp.size(); i++) {
                List<Integer> temp = tmp.get(i);
                if (!temp.isEmpty())
                    indexes.add(i);
            }
        }

        return indexes;
    }

    private List<Integer> compareDocs(List<Integer> l1, List<Integer> l2) {
        List<Integer> combined = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l2.size(); j++) {
                if (l1.get(i) == l2.get(j)) {
                    combined.add(l1.get(i));
                }
            }
        }

        return combined;
    }
}