package top.yundesign.fmz.bean;

import java.util.List;

public class IndexData {

    private List<BannerBean> banner;
    private List<ProductsBean> products;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class BannerBean {
        /**
         * logo : /uploads/goods/test.jpg
         * parameter : http://www.baidu.com
         * type : 1
         */

        private String logo;
        private String parameter;
        private int type;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class ProductsBean {
        /**
         * id : 1
         * title : 商品标题1
         * logo : /uploads/goods/test.jpg
         * market_price : 123
         * salenum : 123
         */

        private int id;
        private String title;
        private String logo;
        private int market_price;
        private int salenum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }
    }
}
