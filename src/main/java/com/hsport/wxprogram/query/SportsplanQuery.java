package com.hsport.wxprogram.query;


/**
 *
 * @author lhb
 * @since 2019-11-27
 */
import com.hsport.wxprogram.common.util.BaseQuery;

public class SportsplanQuery extends BaseQuery{
   private Integer userID;
   private String date;

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
    public String toString() {
        return "SportsplanQuery{" +
                "userID=" + userID +
                "rows"+this.getRows()+
                "page"+this.getPage()+
                '}';
    }
}