package org.demo.onestorage.model.disk;

public class DiskInfo {

    int diskId;         // 从 1 开始的正整数。可以用数据库自增主键顺序分配。
    DiskType diskType;         // 如果数据库读写的转换有困难，也可以把枚举改成 String。
    int totalCapacityInGB;      // 总空间GB
    int currentUsedInGB;       // 已用空间GB


}
