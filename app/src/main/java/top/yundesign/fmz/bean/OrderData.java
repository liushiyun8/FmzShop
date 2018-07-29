package top.yundesign.fmz.bean;

import java.util.List;

public class OrderData {

    /**
     * id : 1
     * shop_id : 1
     * shop_title : xxxxxxx 1
     * shop_logo : /uploads/heads/test.jpg
     * pay_amount : 123
     * goods : [{"goods_id":1,"goods_image":"/uploads/goods/test.jpg","goods_name":"abc","goods_num":1,"goods_price":12,"product_sku":"xxx,xxx,xxx"},{"goods_id":1,"goods_image":"/uploads/goods/test.jpg","goods_name":"abc","goods_num":1,"goods_price":12,"product_sku":"xxx,xxx,xxx"}]
     */

    private int id;
    private int shop_id;
    private String shop_title;
    private String shop_logo;
    private int pay_amount;
    private List<GoodsBean> goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_title() {
        return shop_title;
    }

    public void setShop_title(String shop_title) {
        this.shop_title = shop_title;
    }

    public String getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(String shop_logo) {
        this.shop_logo = shop_logo;
    }

    public int getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(int pay_amount) {
        this.pay_amount = pay_amount;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * goods_id : 1
         * goods_image : /uploads/goods/test.jpg
         * goods_name : abc
         * goods_num : 1
         * goods_price : 12
         * product_sku : xxx,xxx,xxx
         */

        private int goods_id;
        private String goods_image;
        private String goods_name;
        private int goods_num;
        private int goods_price;
        private String product_sku;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(int goods_price) {
            this.goods_price = goods_price;
        }

        public String getProduct_sku() {
            return product_sku;
        }

        public void setProduct_sku(String product_sku) {
            this.product_sku = product_sku;
        }
    }
}
