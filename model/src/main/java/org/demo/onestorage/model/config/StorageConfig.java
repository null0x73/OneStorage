package org.demo.onestorage.model.config;

public class StorageConfig {

    boolean cacheEnabled;   // 指示是否缓存。true 则缓存。
    int backupRedundancy;   // 备份冗余度。是大于等于 0 的正整数。例如，0 表述没有备份、3 表示在原件（archive）和缓存（cache）外总共还有三个独立的备份。

    public StorageConfig(boolean cacheEnabled, int backupRedundancy) {
        this.cacheEnabled = cacheEnabled;
        this.backupRedundancy = backupRedundancy;
    }

    public StorageConfig() {
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public int getBackupRedundancy() {
        return backupRedundancy;
    }

    public void setBackupRedundancy(int backupRedundancy) {
        this.backupRedundancy = backupRedundancy;
    }

    @Override
    public String toString() {
        return "StorageConfig{" +
                "cacheEnabled=" + cacheEnabled +
                ", backupRedundancy=" + backupRedundancy +
                '}';
    }

}
