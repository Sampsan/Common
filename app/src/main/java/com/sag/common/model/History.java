package com.sag.common.model;

import android.view.View;

import com.sag.common.R;
import com.sag.library.model.BaseBindModel;
import com.sag.library.model.impl.SimpleRecyclerViewModel;

/**
 * Created by SAG on 2017/6/14 0014.
 */

public class History extends SimpleRecyclerViewModel<History.Item> {

    public static final String TYPE = "History";

    public String url = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2847494274,442712643&fm=26&gp=0.jpg";

    public static class Item implements BaseBindModel {

        /**
         * goods_id : 100099
         * goods_name : 韩熙贞白领裸妆3件套清新裸妆 气垫BB霜+妆前乳+口红808玫紫红彩
         * goods_price : 49.00
         * goods_image : http://www.sykjshop.com/rdhd/data/upload/mall/store/goods/1/1_000000000135852575_1_800x800_240.jpg
         * goods_salenum : 3
         * area_info :
         */

        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_image;
        private String goods_salenum;
        private String area_info;

        @Override
        public int getLayoutID() {
            return R.layout.item_txt;
        }

        @Override
        public void onDo(View v) {

        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(String goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }
    }

}
