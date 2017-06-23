package com.sag.common.model;

import com.sag.library.model.impl.BaseModel;

/**
 * Created by SAG on 2017/6/12 0012.
 */

public class IndexModel extends BaseModel {

    private int state;
    private DataBean data;

    @Override
    public boolean isOk() {
        return state == 1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private Object daysEarnings;
        private String coutnEarnings;
        private String earningsNum;
        private Object daysIncome;
        private Object coutnIncome;
        private String incomeNum;

        public Object getDaysEarnings() {
            return daysEarnings;
        }

        public void setDaysEarnings(Object daysEarnings) {
            this.daysEarnings = daysEarnings;
        }

        public String getCoutnEarnings() {
            return coutnEarnings;
        }

        public void setCoutnEarnings(String coutnEarnings) {
            this.coutnEarnings = coutnEarnings;
        }

        public String getEarningsNum() {
            return earningsNum;
        }

        public void setEarningsNum(String earningsNum) {
            this.earningsNum = earningsNum;
        }

        public Object getDaysIncome() {
            return daysIncome;
        }

        public void setDaysIncome(Object daysIncome) {
            this.daysIncome = daysIncome;
        }

        public Object getCoutnIncome() {
            return coutnIncome;
        }

        public void setCoutnIncome(Object coutnIncome) {
            this.coutnIncome = coutnIncome;
        }

        public String getIncomeNum() {
            return incomeNum;
        }

        public void setIncomeNum(String incomeNum) {
            this.incomeNum = incomeNum;
        }
    }
}
