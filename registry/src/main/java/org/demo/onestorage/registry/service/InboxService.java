package org.demo.onestorage.registry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InboxService {

    @Autowired
    RegistryService registryService;


    public void saveFileToBasePackInBuffer() {

    }
}
