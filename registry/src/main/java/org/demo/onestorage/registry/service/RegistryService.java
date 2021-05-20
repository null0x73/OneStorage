package org.demo.onestorage.registry.service;

import org.demo.onestorage.model.metadata.Metadata;
import org.springframework.stereotype.Component;

@Component
public class RegistryService {

    public int registerNewBasePack(Metadata metadata, long fileSize){
        return -1;
    }


    public int allocatePortionId(int parentPackId){
        return -1;
    }

    public void reportBasePackInboxDone(int newBasePackId) {

    }

    public void reportBasePackDuplicateDone(int newBasePackId) {

    }
}
