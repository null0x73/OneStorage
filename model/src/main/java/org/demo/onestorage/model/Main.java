package org.demo.onestorage.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.demo.onestorage.model.metadata.Metadata;
import org.demo.onestorage.util.format.json.JsonUtil;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        Metadata metadata = new Metadata();

        metadata.getTags().add("youtube");
        metadata.getTags().add("yurucamp");
        metadata.getTags().add("nature");
        metadata.getTags().add("outdoors");

        metadata.getValues().put("type","anime");
        metadata.getValues().put("resolution","1080p");

        System.out.println(JsonUtil.getJsonFromObject(metadata));;

    }

}
