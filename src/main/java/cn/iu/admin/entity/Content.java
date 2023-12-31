package cn.iu.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: aliangcode
 * Date: 2023/7/15 2:00
 */
@Data
public class Content implements Serializable {
    private String id;
    private String name;
    private String groupId;
}
