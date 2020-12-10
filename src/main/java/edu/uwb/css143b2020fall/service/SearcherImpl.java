package edu.uwb.css143b2020fall.service;

import edu.uwb.css143b2020fall.model.Index;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    private Map<String, List<List<Integer>>> map; // I hope this is allowed, just wanted to be able to use the index
    //map in a function without having to pass it through each time in a function
    
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        map = index;
        String[] words = keyPhrase.split(" ");

        for (int i = 0; i < words.length; i++) {
            List<Integer> docIndexes = getDocs(words[i], index);
            if (docIndexes == null || docIndexes.size() == 0) {
                return new ArrayList<>();
            } else if (words.length == 1) {
                return docIndexes;
            }
        }
        List<Integer> commonIndexes = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            if(words[i].equals(words[i + 1])) return new ArrayList<>();
            commonIndexes = compareDocs(getDocs(words[i], index), getDocs(words[i + 1], index));
            if(commonIndexes == null) return new ArrayList<>();
            List<Integer> checkedIndexes = checkIndexOrder(words[i], words[i + 1], commonIndexes);
            if(checkedIndexes != null){
                commonIndexes = checkedIndexes;
            }
        }
        return commonIndexes;
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
        for (int doc : l1) {
            for (int doc2 : l2){
                if(doc == doc2){
                    combined.add(doc);
                }
            }
        }
        return combined;
    }

    private List<Integer> checkIndexOrder(String s1, String s2, List<Integer> docNumbers){
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> index1 = map.get(s1);
        List<List<Integer>> index2 = map.get(s2);
        for (int k = 0; k < docNumbers.size(); k++) {
            List<Integer> index1Mini = index1.get(docNumbers.get(k));
            List<Integer> index2Mini = index2.get(docNumbers.get(k));
            for (int i = 0; i < index1Mini.size(); i++) {
                for (int j = 0; j < index2Mini.size(); j++) {
                    if(index1Mini.get(i) < index2Mini.get(j) && index2Mini.get(j) - index1Mini.get(i) == 1) {
                        result.add(docNumbers.get(k));
                    }
                }
            }
        }
        return result;
    }
}