package org.example.evaluations.evaluation.services;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component("azure")
public class AzureKeyVault implements IKeyVault {

    Map<String,String> map = new TreeMap<>();
    @Override
    public void saveSecret(String secretName, String secretValue) {
        map.put(secretName, secretValue);
    }

    @Override
    public String retrieveSecret(String secretName) {
        return map.getOrDefault(secretName, null);
    }
}
