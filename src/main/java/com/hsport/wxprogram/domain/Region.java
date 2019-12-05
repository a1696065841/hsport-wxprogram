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
 * @since 2019-12-05
 */
@TableName("t_region")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String regionName;
    private Integer parentID;
    /**
     * 地区的级别 1级就是市,2市里的区
     */
    private Integer regionLev;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getRegionLev() {
        return regionLev;
    }

    public void setRegionLev(Integer regionLev) {
        this.regionLev = regionLev;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Region{" +
        ", id=" + id +
        ", regionName=" + regionName +
        ", parentID=" + parentID +
        ", regionLev=" + regionLev +
        "}";
    }
}
