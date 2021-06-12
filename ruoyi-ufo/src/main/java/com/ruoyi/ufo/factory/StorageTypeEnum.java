package com.ruoyi.ufo.factory;


public enum StorageTypeEnum {
    LOCAL(0, "本地存储"),
    ALIYUN_OSS(1, "阿里云OSS对象存储"),
    FAST_DFS(2, "fastDFS集群存储");
    private int storageType;
    private String storageName;

    StorageTypeEnum(int storageType, String storageName) {
        this.storageType = storageType;
        this.storageName = storageName;
    }

    public int getStorageType() {
        return storageType;
    }

    public void setStorageType(int storageType) {
        this.storageType = storageType;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }
}
