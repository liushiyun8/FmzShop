package top.yundesign.fmz.bean;

import java.util.List;

public class ShopcarData {

    /**
     * store_id : 1
     * store_name : 商铺1
     * store_logo : /uploads/heads/test.jpg
     * products : [{"goods_id":1,"goods_image":"/uploads/goods/test.jpg","goods_name":"abc","goods_num":1,"goods_price":12,"product_sku":"xxx,xxx,xxx","sku_price_id":1}]
     */

    private int store_id;
    private String store_name;
    private String store_logo;
    private List<ProductsBean> products;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * goods_id : 1
         * goods_image : /uploads/goods/test.jpg
         * goods_name : abc
         * goods_num : 1
         * goods_price : 12
         * product_sku : xxx,xxx,xxx
         * sku_price_id : 1
         */

        private int goods_id;
        private String goods_image;
        private String goods_name;
        private int goods_num;
        private int goods_price;
        private String product_sku;
        private int sku_price_id;
        private boolean isCheked;

        public boolean isCheked() {
            return isCheked;
        }

        public void setCheked(boolean cheked) {
            isCheked = cheked;
        }
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

        public int getSku_price_id() {
            return sku_price_id;
        }

        public void setSku_price_id(int sku_price_id) {
            this.sku_price_id = sku_price_id;
        }
    }
}
