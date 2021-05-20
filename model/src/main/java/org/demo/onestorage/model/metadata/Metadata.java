package org.demo.onestorage.model.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Metadata {

    HashSet<String> tags;
    HashMap<String, String> values;

    public Metadata() {
        tags = new HashSet<>();
        values = new HashMap<>();
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "tags=" + tags +
                ", values=" + values +
                '}';
    }

}
