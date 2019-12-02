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
@TableName("t_duibitu")
public class Duibitu extends Model<Duibitu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 正面照
     */
    private String zhengMzhao;
    private String ceMzhao;
    private String date;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZhengMzhao() {
        return zhengMzhao;
    }

    public void setZhengMzhao(String zhengMzhao) {
        this.zhengMzhao = zhengMzhao;
    }

    public String getCeMzhao() {
        return ceMzhao;
    }

    public void setCeMzhao(String ceMzhao) {
        this.ceMzhao = ceMzhao;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "Duibitu{" +
        ", id=" + id +
        ", zhengMzhao=" + zhengMzhao +
        ", ceMzhao=" + ceMzhao +
        ", date=" + date +
        ", userID=" + userID +
        "}";
    }
}
