package com.brainuptech.amharicdictionary.Entities;

/**
 * Created by Roger on 3/29/2016.
 */
public class DictionaryEntitty {
    private int id;
    private String word1;
    private String definition;

    public DictionaryEntitty() {
    }

    public DictionaryEntitty(int id, String word1, String definition) {
        this.id = id;
        this.word1 = word1;
        this.definition = definition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
