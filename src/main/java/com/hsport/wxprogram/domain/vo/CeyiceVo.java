package com.hsport.wxprogram.domain.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.hsport.wxprogram.domain.Ceyice;

import java.io.Serializable;
import java.util.HashMap;

public class CeyiceVo {

    private String name;
    private Integer sex;

    private Integer id;

    public Integer getYaowei() {
        return yaowei;
    }

    public void setYaowei(Integer yaowei) {
        this.yaowei = yaowei;
    }

    public Integer yaowei;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
         * 父母是否有人比较胖
         */
        private Integer fmsfyrbjp;
        /**
         * 体重何时明显增加
         */
        private Integer tzhsmxzj;
        /**
         * 日常饮食情况多选题!!
         */
        private HashMap rcysqkDX;
        /**
         * 饮食行为习惯
         */
        private HashMap ysxwxgDX;
        private Integer sfyydxg;
        /**
         * 每周参加运动频率
         */
        private Integer mzcjydpl;
        /**
         * 运动持续时间
         */
        private Integer ydcxsj;
        /**
         * 部位运动损伤多选
         */
        private HashMap buweiYdSunsDX;
        /**
         * 是否经常熬夜
         */
        private Integer sfjcay;
        /**
         * 平均睡眠时间
         */
        private Integer pjsmsj;
        private HashMap rcsmzlDX;
        private HashMap rcpbqkDX;
        /**
         * 以前有过的减肥经历多选
         */
        private HashMap yqJianfeiJLDX;
        /**
         * 日常压力分析
         */
        private Integer rcylfx;
        /**
         * 当时减肥效果和反弹情况
         */
        private Integer dsjfqkFantan;
        private Long userID;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFmsfyrbjp() {
            return fmsfyrbjp;
        }

        public void setFmsfyrbjp(Integer fmsfyrbjp) {
            this.fmsfyrbjp = fmsfyrbjp;
        }

        public Integer getTzhsmxzj() {
            return tzhsmxzj;
        }

        public void setTzhsmxzj(Integer tzhsmxzj) {
            this.tzhsmxzj = tzhsmxzj;
        }

    public HashMap getRcysqkDX() {
        return rcysqkDX;
    }

    public void setRcysqkDX(HashMap rcysqkDX) {
        this.rcysqkDX = rcysqkDX;
    }

    public HashMap getYsxwxgDX() {
        return ysxwxgDX;
    }

    public void setYsxwxgDX(HashMap ysxwxgDX) {
        this.ysxwxgDX = ysxwxgDX;
    }

    public void setBuweiYdSunsDX(HashMap buweiYdSunsDX) {
        this.buweiYdSunsDX = buweiYdSunsDX;
    }

    public Integer getSfyydxg() {
            return sfyydxg;
        }

        public void setSfyydxg(Integer sfyydxg) {
            this.sfyydxg = sfyydxg;
        }

        public Integer getMzcjydpl() {
            return mzcjydpl;
        }

        public void setMzcjydpl(Integer mzcjydpl) {
            this.mzcjydpl = mzcjydpl;
        }

        public Integer getYdcxsj() {
            return ydcxsj;
        }

        public void setYdcxsj(Integer ydcxsj) {
            this.ydcxsj = ydcxsj;
        }

    public HashMap getBuweiYdSunsDX() {
        return buweiYdSunsDX;
    }

    public Integer getSfjcay() {
            return sfjcay;
        }

        public void setSfjcay(Integer sfjcay) {
            this.sfjcay = sfjcay;
        }

        public Integer getPjsmsj() {
            return pjsmsj;
        }

        public void setPjsmsj(Integer pjsmsj) {
            this.pjsmsj = pjsmsj;
        }

    public HashMap getRcsmzlDX() {
        return rcsmzlDX;
    }

    public void setRcsmzlDX(HashMap rcsmzlDX) {
        this.rcsmzlDX = rcsmzlDX;
    }

    public HashMap getRcpbqkDX() {
        return rcpbqkDX;
    }

    public void setRcpbqkDX(HashMap rcpbqkDX) {
        this.rcpbqkDX = rcpbqkDX;
    }

    public HashMap getYqJianfeiJLDX() {
        return yqJianfeiJLDX;
    }

    public void setYqJianfeiJLDX(HashMap yqJianfeiJLDX) {
        this.yqJianfeiJLDX = yqJianfeiJLDX;
    }

    public Integer getRcylfx() {
            return rcylfx;
        }

        public void setRcylfx(Integer rcylfx) {
            this.rcylfx = rcylfx;
        }

        public Integer getDsjfqkFantan() {
            return dsjfqkFantan;
        }

        public void setDsjfqkFantan(Integer dsjfqkFantan) {
            this.dsjfqkFantan = dsjfqkFantan;
        }

        public Long getUserID() {
            return userID;
        }

        public void setUserID(Long userID) {
            this.userID = userID;
        }

     

        @Override
        public String toString() {
            return "Ceyice{" +
                    ", id=" + id +
                    ", fmsfyrbjp=" + fmsfyrbjp +
                    ", tzhsmxzj=" + tzhsmxzj +
                    ", rcysqkDX=" + rcysqkDX +
                    ", ysxwxgDX=" + ysxwxgDX +
                    ", sfyydxg=" + sfyydxg +
                    ", mzcjydpl=" + mzcjydpl +
                    ", ydcxsj=" + ydcxsj +
                    ", buweiYdSunsDX=" + buweiYdSunsDX +
                    ", sfjcay=" + sfjcay +
                    ", pjsmsj=" + pjsmsj +
                    ", rcsmzlDX=" + rcsmzlDX +
                    ", rcpbqkDX=" + rcpbqkDX +
                    ", yqJianfeiJLDX=" + yqJianfeiJLDX +
                    ", rcylfx=" + rcylfx +
                    ", dsjfqkFantan=" + dsjfqkFantan +
                    ", userID=" + userID +
                    "}";
        }
}
