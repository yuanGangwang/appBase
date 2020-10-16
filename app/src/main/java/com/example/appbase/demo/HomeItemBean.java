package com.example.appbase.demo;


import java.math.BigDecimal;
import java.util.List;

public class HomeItemBean   {

    final public static int Item_Top_img_one = 1;
    final public static int Item_Top_img_two = 2;
    final public static int Item_Top_img_three = 3;
    final public static int Item_This_phone = 4;
    final public static int Item_Other_phone = 5;
    final public static int Item_Shop_Layout = 6;

    /**
     * brands : [{"id":"9","name":"苹果"},{"id":"8","name":"华为"},{"id":"7","name":"vivo"},{"id":"6","name":"oppo"},{"id":"5","name":"小米"},{"id":"4","name":"荣耀"},{"id":"3","name":"平板"},{"id":"2","name":"数码"},{"id":"1","name":"配件"}]
     * function : [{"id":"8","img":"http://hjb.onmsbx.com/uploads/20190901/81cff47f6199422960a0dd7879f6beee.png","imgLink":"#","title":"以旧换新"},{"id":"7","img":"http://hjb.onmsbx.com/uploads/20190901/7d7a268cdc8dcaf18ac06d217dfbf12c.png","imgLink":"#","title":"人气推荐"},{"id":"6","img":"http://hjb.onmsbx.com/uploads/20190901/bd72e015ac9a80fe0ae63fe8c8d3b088.png","imgLink":"#","title":"玩家必备"},{"id":"5","img":"http://hjb.onmsbx.com/uploads/20190901/53a834b2940e63c6af9f89eb6acde4f7.png","imgLink":"#","title":"超值配件"},{"id":"4","img":"http://hjb.onmsbx.com/uploads/20190901/b15292297f0ff986206e1c4163efb314.png","imgLink":"#","title":"更多精彩"}]
     * recyclePhone : {"brandId":"40","createTime":"2019-08-22 18:27:23","defaultPrice":1970,"deleted":0,"id":"673","isRetrieve":1,"model":"iPad 2018","name":"iPad 2018","phoneType":2,"showPic":"http://huanjib.oss-cn-beijing.aliyuncs.com/hjb/1577503699344.jpg?Expires=1892863698&OSSAccessKeyId=LTAI4FwQpVoCqhZCCYfqtbt4&Signature=NcHARgdN3GzYh62rS0jrNmWYXtE%3D","sort":20,"updateTime":"2019-12-28 11:28:22"}
     * banner : {"id":"658631313221353472","img":"http://hjb.onmsbx.com/uploads/20190730/710ad1c5c3fc9cb8727b69f2dceca724.png","imgLink":"product-detail.html?id=103&buytype=1&brandSel=75&productname=诺基亚105","title":"首页的轮播图下方的展示图"}
     * banners : [{"id":"3","img":"http://hjb.onmsbx.com/uploads/20190901/478c2dab35d266a9798afc215d49af0b.png","imgLink":"#","title":"111"},{"id":"2","img":"http://hjb.onmsbx.com/uploads/20190901/444aa8802a440b537401dca5870d9e41.png","imgLink":"#","title":"333"},{"id":"1","img":"http://huanjib.oss-cn-beijing.aliyuncs.com/hjb/1576487225039.png?Expires=1891847216&OSSAccessKeyId=LTAI4FwQpVoCqhZCCYfqtbt4&Signature=bAQpKTKQDH6keMjATv7CBeSbZu4%3D","imgLink":"#","title":"1234"}]
     * products : [{"img":"http://hjb.onmsbx.com/uploads/20190912/28b25121c9c2f5c3c708b39ee2da3773.png","imgLink":"#","id":"9","title":"人气推荐","products":[{"bannerId":"9","id":"660123522898722816","img":"http://hjb.onmsbx.com/uploads/20190818/37a237a0696b26d8740da542fbe6983a.jpg","isSale":null,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":11,"productName":"Beats Solo3 Wireless"},{"bannerId":"9","id":"660123523049717760","img":"http://hjb.onmsbx.com/uploads/20190830/802ffa68eb094945b983a743ca71b690.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":139,"productName":"诺基亚105"},{"bannerId":"9","id":"660123523129409536","img":"http://hjb.onmsbx.com/uploads/20190830/91fcfdaf4b6820bf9f1c81e94661eed9.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3999,"productName":"一加7Pro"},{"bannerId":"9","id":"660123523200712704","img":"http://hjb.onmsbx.com/uploads/20190830/b7e72ce137e4a2f4b9907edef73e8611.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":6999,"productName":"三星S10+"}]},{"img":"http://hjb.onmsbx.com/uploads/20190912/cf21a35c392d186752d07e8f685c9421.png","imgLink":"#","id":"10","title":"玩家必备","products":[{"bannerId":"10","id":"660123524198957056","img":"http://hjb.onmsbx.com/uploads/20190723/8f353ae198c666405af14096a07a17da.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1399,"productName":"荣耀20i"},{"bannerId":"10","id":"660123524274454528","img":"http://hjb.onmsbx.com/uploads/20190723/69faab4a9a9fe597d2bb83b4fcf374b5.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3199,"productName":"荣耀20 Pro"},{"bannerId":"10","id":"660123524341563392","img":"http://hjb.onmsbx.com/uploads/20190723/e3017e2a36217bf473c63e6e8b4c74a5.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1799,"productName":"荣耀10"},{"bannerId":"10","id":"660123524412866560","img":"http://hjb.onmsbx.com/uploads/20190723/0d332bc3ca04a3a6db0ca7fa4a90bbff.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":2699,"productName":"荣耀20"},{"bannerId":"10","id":"660123524484169728","img":"http://hjb.onmsbx.com/uploads/20190723/f002b33dcf18632aa159c79e6c84dc98.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":2499,"productName":"荣耀Magic2"}]},{"img":"http://hjb.onmsbx.com/uploads/20190912/c3468da2e285abeb2abe87c43f64b429.png","imgLink":"#","id":"11","title":"超值配件","products":[{"bannerId":"11","id":"660123525377556480","img":"http://hjb.onmsbx.com/uploads/20190912/e5d4e4af700bd482792f13c1be219ff4.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":2999,"productName":"Reno 2"},{"bannerId":"11","id":"660123525453053952","img":"http://hjb.onmsbx.com/uploads/20190723/4a36212e875dbdf5f8cb35f98fe74cfc.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1199,"productName":"A5"},{"bannerId":"11","id":"660123525541134336","img":"http://hjb.onmsbx.com/uploads/20190723/eb904aaf881ba535fd368a5c850a2c6c.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1599,"productName":"A7x"},{"bannerId":"11","id":"660123525612437504","img":"http://hjb.onmsbx.com/uploads/20190723/3b63d3197e50c25418164187ff2caa11.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1599,"productName":"K3"},{"bannerId":"11","id":"660123525687934976","img":"http://hjb.onmsbx.com/uploads/20190723/9c7f783cfc0bc72b6f321b44f821fd8e.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1399,"productName":"A9"}]},{"img":"http://hjb.onmsbx.com/uploads/20190912/841dfcb98f672a6b56d425e38a2f31db.png","imgLink":"#","id":"12","title":"更多精彩","products":[{"bannerId":"12","id":"660123526572933120","img":"http://hjb.onmsbx.com/uploads/20190723/56074692c4b7bda230c8c345eb1a6d3d.png","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1300,"productName":"Y93s"},{"bannerId":"12","id":"660123526644236288","img":"http://hjb.onmsbx.com/uploads/20190723/3dd361c3774028d14eefa2e50a2855f2.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1498,"productName":"Y3"},{"bannerId":"12","id":"660123526719733760","img":"http://hjb.onmsbx.com/uploads/20190723/ce9c291019f0c8a04809e784148a6c21.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":1799,"productName":"X21s"},{"bannerId":"12","id":"660123526791036928","img":"http://hjb.onmsbx.com/uploads/20190723/5e7a535c7e04ebe244b60be2c97694cb.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":2798,"productName":"IQOO"}]},{"img":"http://hjb.onmsbx.com/uploads/20190912/54b3043a85e0ac5475584dc15246c1a5.png","imgLink":"#","id":"13","title":"精品推荐","products":[{"bannerId":"13","id":"660123527755726848","img":"http://hjb.onmsbx.com/uploads/20190723/67d707af306751de0d165701515cdb16.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":4499,"productName":"华为Mate 20 Pro"},{"bannerId":"13","id":"660123527831224320","img":"http://hjb.onmsbx.com/uploads/20190723/08746c14dee31774c8d2e639a4cc6460.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3199,"productName":"华为Mate 20"},{"bannerId":"13","id":"660123527906721792","img":"http://hjb.onmsbx.com/uploads/20190723/868506b6bcacb16e752a5dc68e0806b2.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":2999,"productName":"华为nova5 Pro"},{"bannerId":"13","id":"660123527978024960","img":"http://hjb.onmsbx.com/uploads/20190723/399f705afd6e0bb2ddc8ea21635adb0e.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3088,"productName":"华为 P20"},{"bannerId":"13","id":"660123528049328128","img":"http://hjb.onmsbx.com/uploads/20190723/db74dcce2fd759677c6f5af5f1462902.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3398,"productName":"华为 P20 Pro"},{"bannerId":"13","id":"660123528124825600","img":"http://hjb.onmsbx.com/uploads/20190723/66ddea517ba55ee7a253183f617d0594.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3988,"productName":"华为 P30"}]}]
     */

    private RecyclePhoneBean recyclePhone;
    private BannerBean banner;
    private List<BrandsBean> brands;
    private List<FunctionBean> function;
    private List<BannersBean> banners;
    private List<ProductsBeanX> products;
    private ProductsBeanX productsBeanX;
    private int signIn;//是否已签到 1:已签到 2:未签到

    public int getSignIn() {
        return signIn;
    }

    public void setSignIn(int signIn) {
        this.signIn = signIn;
    }

    public ProductsBeanX getProductsBeanX() {
        return productsBeanX;
    }

    public void setProductsBeanX(ProductsBeanX productsBeanX) {
        this.productsBeanX = productsBeanX;
    }



    public RecyclePhoneBean getRecyclePhone() {
        return recyclePhone;
    }

    public void setRecyclePhone(RecyclePhoneBean recyclePhone) {
        this.recyclePhone = recyclePhone;
    }

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public List<BrandsBean> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandsBean> brands) {
        this.brands = brands;
    }

    public List<FunctionBean> getFunction() {
        return function;
    }

    public void setFunction(List<FunctionBean> function) {
        this.function = function;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<ProductsBeanX> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBeanX> products) {
        this.products = products;
    }

    public static class RecyclePhoneBean {
        /**
         * brandId : 40
         * createTime : 2019-08-22 18:27:23
         * defaultPrice : 1970
         * deleted : 0
         * id : 673
         * isRetrieve : 1
         * model : iPad 2018
         * name : iPad 2018
         * phoneType : 2
         * showPic : http://huanjib.oss-cn-beijing.aliyuncs.com/hjb/1577503699344.jpg?Expires=1892863698&OSSAccessKeyId=LTAI4FwQpVoCqhZCCYfqtbt4&Signature=NcHARgdN3GzYh62rS0jrNmWYXtE%3D
         * sort : 20
         * updateTime : 2019-12-28 11:28:22
         */

        private String brandId;
        private String createTime;
        private BigDecimal defaultPrice;
        private int deleted;
        private String id;
        private int isRetrieve;
        private String model;
        private String name;
        private int phoneType;
        private String showPic;
        private int sort;
        private String updateTime;
        private boolean finishApprise;

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public BigDecimal getDefaultPrice() {
            return defaultPrice;
        }

        public void setDefaultPrice(BigDecimal defaultPrice) {
            this.defaultPrice = defaultPrice;
        }
        public void setFinishApprise(boolean isfinish) {
            this.finishApprise = isfinish;
        }

        public boolean isFinishApprise() {
            return finishApprise;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsRetrieve() {
            return isRetrieve;
        }

        public void setIsRetrieve(int isRetrieve) {
            this.isRetrieve = isRetrieve;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPhoneType() {
            return phoneType;
        }

        public void setPhoneType(int phoneType) {
            this.phoneType = phoneType;
        }

        public String getShowPic() {
            return showPic;
        }

        public void setShowPic(String showPic) {
            this.showPic = showPic;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class BannerBean {
        /**
         * id : 658631313221353472
         * img : http://hjb.onmsbx.com/uploads/20190730/710ad1c5c3fc9cb8727b69f2dceca724.png
         * imgLink : product-detail.html?id=103&buytype=1&brandSel=75&productname=诺基亚105
         * title : 首页的轮播图下方的展示图
         */

        private String id;
        private String img;
        private String imgLink;
        private String title;
        private int  linkType;
        private int buyType;
        private String brandId;

        public int getBuyType() {
            return buyType;
        }

        public void setBuyType(int buyType) {
            this.buyType = buyType;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class BrandsBean {
        /**
         * id : 9
         * name : 苹果
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class FunctionBean {
        /**
         * id : 8
         * img : http://hjb.onmsbx.com/uploads/20190901/81cff47f6199422960a0dd7879f6beee.png
         * imgLink : #
         * title : 以旧换新
         */

        private String id;
        private String img;
        private String imgLink;
        private String title;
        private String productId;
        private int linkType;
        private int buyType;
        private String brandId;

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public int getBuyType() {
            return buyType;
        }

        public void setBuyType(int buyType) {
            this.buyType = buyType;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class BannersBean {
        /**
         * id : 3
         * img : http://hjb.onmsbx.com/uploads/20190901/478c2dab35d266a9798afc215d49af0b.png
         * imgLink : #
         * title : 111
         */

        private String id;
        private String img;
        private String imgLink;
        private String title;
        private String productId;
        private int linkType;

        private int buyType;
        private String brandId;

        public int getBuyType() {
            return buyType;
        }

        public void setBuyType(int buyType) {
            this.buyType = buyType;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ProductsBeanX {
        /**
         * img : http://hjb.onmsbx.com/uploads/20190912/28b25121c9c2f5c3c708b39ee2da3773.png
         * imgLink : #
         * id : 9
         * title : 人气推荐
         * products : [{"bannerId":"9","id":"660123522898722816","img":"http://hjb.onmsbx.com/uploads/20190818/37a237a0696b26d8740da542fbe6983a.jpg","isSale":null,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":11,"productName":"Beats Solo3 Wireless"},{"bannerId":"9","id":"660123523049717760","img":"http://hjb.onmsbx.com/uploads/20190830/802ffa68eb094945b983a743ca71b690.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":139,"productName":"诺基亚105"},{"bannerId":"9","id":"660123523129409536","img":"http://hjb.onmsbx.com/uploads/20190830/91fcfdaf4b6820bf9f1c81e94661eed9.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":3999,"productName":"一加7Pro"},{"bannerId":"9","id":"660123523200712704","img":"http://hjb.onmsbx.com/uploads/20190830/b7e72ce137e4a2f4b9907edef73e8611.jpg","isSale":2,"maxBuyRed":0,"maxChangeRed":0,"originalPrice":6999,"productName":"三星S10+"}]
         */

        private String img;
        private String imgLink;
        private String id;
        private String title;
        private List<ProductsBean> products;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * bannerId : 9
             * id : 660123522898722816
             * img : http://hjb.onmsbx.com/uploads/20190818/37a237a0696b26d8740da542fbe6983a.jpg
             * isSale : null
             * maxBuyRed : 0
             * maxChangeRed : 0
             * originalPrice : 11
             * productName : Beats Solo3 Wireless
             */

            private String bannerId;
            private String id;
            private String img;
            private Object isSale;
            private String maxBuyRed;
            private String maxChangeRed;
            private String originalPrice;
            private String productName;

            public String getBannerId() {
                return bannerId;
            }

            public void setBannerId(String bannerId) {
                this.bannerId = bannerId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getIsSale() {
                return isSale;
            }

            public void setIsSale(Object isSale) {
                this.isSale = isSale;
            }

            public String getMaxBuyRed() {
                return maxBuyRed;
            }

            public void setMaxBuyRed(String maxBuyRed) {
                this.maxBuyRed = maxBuyRed;
            }

            public String getMaxChangeRed() {
                return maxChangeRed;
            }

            public void setMaxChangeRed(String maxChangeRed) {
                this.maxChangeRed = maxChangeRed;
            }

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
        }
    }
}
