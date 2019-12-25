package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
@TableName("t_livetype")
public class Livetype extends Model<Livetype> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 日常入睡时间
     */
    private String rcRSsj;
    private Integer sfXiYan;

    public Integer getSfXiYan() {
        return sfXiYan;
    }

    public void setSfXiYan(Integer sfXiYan) {
        this.sfXiYan = sfXiYan;
    }

    private Integer mtSMsj;
    /**
     * 工作压力情况(选项)
     */
    private Integer gzYL;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRcRSsj() {
        return rcRSsj;
    }

    public void setRcRSsj(String rcRSsj) {
        this.rcRSsj = rcRSsj;
    }

    public Integer getMtSMsj() {
        return mtSMsj;
    }

    public void setMtSMsj(Integer mtSMsj) {
        this.mtSMsj = mtSMsj;
    }

    public Integer getGzYL() {
        return gzYL;
    }

    public void setGzYL(Integer gzYL) {
        this.gzYL = gzYL;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Livetype{" +
        ", id=" + id +
        ", rcRSsj=" + rcRSsj +
        ", mtSMsj=" + mtSMsj +
        ", gzYL=" + gzYL +
        ", userID=" + userID +
        "}";
    }
}
