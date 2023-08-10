package org.example.service;

import java.io.IOException;


public interface InstallService {
    void copy_to_folder(String src, String dest, int stepNum) throws IOException;

    void copy_file_to_folder(String src, String dest, int stepNum) throws IOException;

    void set_property_configuration(String propertyValue, String category, String propertyName, int stepNum);

    void tache_manuelle(int stepNum);

    String getVaultSecret(String vaultUrl, String vaultToken, String secretPath, String propertyValue) throws IOException;
    void updateConfigValue(String source, String propertyName, String propertyValue) throws IOException;
}