package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("user_click")
public class UserShangPing {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long shangpinid;

    private Long userid;

    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getShangpinid() {
        return shangpinid;
    }

    public void setShangpinid(Long shangpinid) {
        this.shangpinid = shangpinid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
