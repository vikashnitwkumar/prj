package org.example.evaluations.evaluation.services;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("google")
public class GoogleKeyVault implements IKeyVault{

  
    Map<String,String> map = new HashMap<>();
    @Override
    public void saveSecret(String secretName, String secretValue) {
        map.put(secretName, secretValue);
    }

    @Override
    public String retrieveSecret(String secretName) {
        return map.getOrDefault(secretName, null);
    }
}
