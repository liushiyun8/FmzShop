package top.yundesign.fmz.Manager;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;
import java.util.logging.XMLFormatter;

import top.yundesign.fmz.App.App;
import top.yundesign.fmz.bean.User;
import top.yundesign.fmz.utils.LogUtils;

public class HttpManager {
    public static final String  HOST="http://i.fengmaozhai.com",
                                TESTHOST="http://i.fengmaozhai.com",
                                LOGINURL=HOST+"/api/user/login",
                                REGISTERURL=HOST+"/api/user/register",
                                SENDEMSG=HOST+"/api/user/sendMessage",
                                UPDATE_PWD=HOST+"/api/user/updatePassword",
                                GET_USERINFO =HOST+"/api/user/getUserInfo",
                                UPDATE_USERINFO=HOST+"/api/user/updateUserInfo",
                                MYFAV=HOST+"/api/user/myFav",
                                MY_ADDRLIST=HOST+"/api/addr/myAddrList",
                                ADD_ADDR=HOST+"/api/addr/addAddr",
                                GET_ADDRSET=HOST+"/api/addr/getAddrSet",
                                SET_DEFAULT_ADDR=HOST+"api/addr/setDefaultAddr",
                                UPDATE_ADDR=HOST+"/api/addr/updateAddr",
                                REMOVE_ADDR=HOST+"/api/addr/removeAddr",

                                INDEX=HOST+"/api/index",
                                CLASS_LIST=HOST+"/api/index/classList",
                                CLASS_PRODUCT=HOST+"/api/index/classProduct",
                                COMMENTLIST=HOST+"/api/product/commentList",
                                COMMENT_PUBLISH=HOST+"/api/product/commentPublish",
                                MY_VIDEO=HOST+"/api/video/myVideo",
                                UPLOAD_VIDEO=HOST+"/api/video/uploadVideo",
                                VIDEO_COMMENTLIST=HOST+"/api/video/commentList",
                                VIDEO_COMMENT_PUBLISH=HOST+"/api/video/publishComment",

                                SHOPPING_CART=HOST+"/api/order/shoppingCart",
                                PRODUCT_SKU=HOST+"/api/product/sku",
                                CANCLE_ORDER=HOST+"/api/order/cancelOrder",
                                SHOP=HOST+"/api/shop",
                                COUPONCENTER=HOST+"/api/coupon/couponCenter",
                                MYCOUPON=HOST+"/api/myCoupon",
                                GET_COUPON=HOST+"/api/coupon/getCoupon",
                                SCORE_PRODUCT=HOST+"/api/product/scoreproduct",
                                SCORE_CHANGE=HOST+"/api/product/exchange",
                                TYPE_AND_BRAND=HOST+"/api/index/getTypeAndBrand",
                                PRODUCT_DETAIL=HOST+"/api/product/detail",
                                SEC_KILL=HOST+"/api/index/seckill",
                                PRODUCT_BYTAG=HOST+"/api/index/productByTag",
                                VALID_COUPON=HOST+"/api/product/validCoupon",
                                CREAT_ORDER=HOST+"/api/order/createOrder",
                                ORDER_DETAIL=HOST+"/api/order/orderDetail",
                                MY_ORDER=HOST+"/api/order/myOrder",
                                VIDEO_LIST=HOST+"/api/video/videoList",
                                VIDEO_TYPELIST=HOST+"/api/video/videoTypeList",
                                LIKE=HOST+"/api/user/likeThis",
                                VIDEO_DETAIL=HOST+"/api/video/videoDetail",
                                MDOU_DETAI=HOST+"/api/product/mDouDetail",
                                BC_PRODUCT=HOST+"/api/order/bcProduct",
                                BC_BUY=HOST+"/api/order/bcBuy",
                                IS_BCNUMBER=HOST+"/api/order/isBcMember",
                                ORDER_COUPON=HOST+"/api/order/getOrderCoupon",
                                MDOULIST=HOST+"/api/product/mDouList",
                                MDOU_CHANGE=HOST+"/api/product/mDouExchange";







    @NonNull
    private static RequestParams getPostRequestParams(String url) {
        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);
        params.setAsJsonContent(true);
        return params;
    }

    private static void setHeader(RequestParams params) {
        params.setHeader("fmz-app-version","V1.0");
        params.setHeader("fmz-system-version","android:8.1.0");
        params.setHeader("fmz-phone-info","");
        params.setHeader("fmz-token", User.token);
        params.setHeader("fmz-userid",User.userId+"");
    }

    /**
     *
     * @param type 登陆类型 1：密码登陆、2：验证码登陆、3：微信登陆
     * @param phone 登录账户
     * @param pwd   密码、type = 1 时传入;短信验证码、type = 2 时传入;微信ID、type = 3 时传入
     * @param myCallback  结果回调
     */
    public static void Login(int type,String phone,String pwd,MyCallback myCallback){
        RequestParams params= getPostRequestParams(LOGINURL);
        setHeader(params);
        params.addParameter("phone",phone);
        params.addParameter("type",type);
        params.addBodyParameter("password",pwd);
        x.http().post(params,myCallback);
    }


    /**
     *
     * @param type 注册类型 1：手机验证码注册、2：微信注册
     * @param phone 注册账户
     * @param code 短信验证码
     * @param pwd   密码、type = 1 时传入  微信ID、type = 2 时传入
     * @param myCallback  结果回调
     */

    public static void Register(int type,String phone,String code,String pwd,MyCallback myCallback){
        RequestParams params = getPostRequestParams(REGISTERURL);
        setHeader(params);
        params.addParameter("phone",phone);
        params.addParameter("type",type);
        params.addParameter("code",code);
        params.addBodyParameter("password",pwd);
        x.http().post(params,myCallback);
    }

    public static void SendMessage(String phone,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SENDEMSG);
        setHeader(params);
        params.addParameter("phone",phone);
        x.http().post(params,myCallback);
    }

    public static void updatePwd(String oldPwd,String newPwd,MyCallback myCallback){
        RequestParams params = getPostRequestParams(UPDATE_PWD);
        setHeader(params);
        params.addParameter("oldPassword",oldPwd);
        params.addParameter("newPassword",newPwd);
        x.http().post(params,myCallback);
    }

   public static void GetUserinfo(MyCallback myCallback){
       RequestParams params = getPostRequestParams(GET_USERINFO);
       setHeader(params);
       x.http().post(params,myCallback);
   }

   public static void UpdateUserinfo(Map<String,String> map, MyCallback myCallback){
       RequestParams params = getPostRequestParams(UPDATE_USERINFO);
       setHeader(params);
       params.setBodyContent(new Gson().toJson(map));
       x.http().post(params,myCallback);
   }

   public static void getMyFav(String type,int page,int pageNum,MyCallback myCallback){
       RequestParams params = getPostRequestParams(MYFAV);
       setHeader(params);
       params.addParameter("type",type);
       params.addParameter("page",page);
       params.addParameter("pageNumber",pageNum);
       x.http().post(params,myCallback);
   }

    public static void GetMyAddrList(MyCallback myCallback){
        RequestParams params = getPostRequestParams(MY_ADDRLIST);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void AddAddr(String cnname,String mobile,String province,String city_level1,String city_level2,String address,int isdefault,MyCallback myCallback){
        RequestParams params = getPostRequestParams(ADD_ADDR);
        setHeader(params);
        params.addParameter("cnname",cnname);
        params.addParameter("mobile",mobile);
        params.addParameter("province",province);
        params.addParameter("city_level1",city_level1);
        params.addParameter("city_level2",city_level2);
        params.addParameter("address",address);
        params.addParameter("isdefault",isdefault);
        x.http().post(params,myCallback);
    }
    public static void setGetAddrset(int parent_id,int region_type,MyCallback myCallback){
        RequestParams params = getPostRequestParams(GET_ADDRSET);
        setHeader(params);
        params.addParameter("parent_id",parent_id);
        params.addParameter("region_type",region_type);
        x.http().post(params,myCallback);
    }

    public static void setDefaultAddr(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SET_DEFAULT_ADDR);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void UpdateAddr(int id,String cnname,String mobile,String province,String city_level1,String city_level2,String address,int isdefault,MyCallback myCallback){
        RequestParams params = getPostRequestParams(UPDATE_ADDR);
        setHeader(params);
        params.addParameter("id",id);
        params.addParameter("cnname",cnname);
        params.addParameter("mobile",mobile);
        params.addParameter("province",province);
        params.addParameter("city_level1",city_level1);
        params.addParameter("city_level2",city_level2);
        params.addParameter("address",address);
        params.addParameter("isdefault",isdefault);
        x.http().post(params,myCallback);
    }

    public static void RemoveAddr(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(REMOVE_ADDR);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void getIndex(MyCallback myCallback){
        RequestParams params = getPostRequestParams(INDEX);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void getClassList(int parent_id,int topnav,int all, MyCallback myCallback){
        RequestParams params = getPostRequestParams(CLASS_LIST);
        setHeader(params);
        params.addParameter("parent_id",parent_id);
        params.addParameter("topnav",topnav);
        params.addParameter("all",all);
        x.http().post(params,myCallback);
    }

    public static void getClassProduct(int type_id,int order,int page,Map<String,String> map, MyCallback myCallback){
        RequestParams params = getPostRequestParams(CLASS_PRODUCT);
        setHeader(params);
        params.addParameter("type_id",type_id);
        params.addParameter("order",order);
        params.addParameter("page",page);
        for (Map.Entry<String, String> entry:map.entrySet()){
            params.addParameter(entry.getKey(),entry.getValue());
        }
        x.http().post(params,myCallback);
    }

    public static void getCommentList(int product_id,int page,int pageNumber,int tagid, MyCallback myCallback){
        RequestParams params = getPostRequestParams(COMMENTLIST);
        setHeader(params);
        params.addParameter("product_id",product_id);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        params.addParameter("tagid",tagid);
        x.http().post(params,myCallback);
    }

    public static void PublishComment(int product_id,int orderid,String content,String sku_str,String[] pictures,String tagid, MyCallback myCallback){
        RequestParams params = getPostRequestParams(COMMENT_PUBLISH);
        setHeader(params);
        params.addParameter("product_id",product_id);
        params.addParameter("orderid",orderid);
        params.addParameter("content",content);
        params.addParameter("sku_str",sku_str);
        params.addParameter("pictures",pictures);
        params.addParameter("tagid",tagid);
        x.http().post(params,myCallback);
    }

     public static void GetMyVideo(int type,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(MY_VIDEO);
        setHeader(params);
        params.addParameter("type",type);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

    public static void UploadVideo(String uid,String username,String typeid,String title,String pic,String content,String gourl,MyCallback myCallback){
        RequestParams params = getPostRequestParams(UPLOAD_VIDEO);
        setHeader(params);
        params.addParameter("uid",uid);
        params.addParameter("username",username);
        params.addParameter("typeid",typeid);
        params.addParameter("title",title);
        params.addParameter("pic",pic);
        params.addParameter("content",content);
        params.addParameter("gourl",gourl);
        x.http().post(params,myCallback);
    }

    public static void getVideoComment(int id, int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(VIDEO_COMMENTLIST);
        setHeader(params);
        params.addParameter("id",id);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

   public static void PublishVideoComment(int action,Map<String,String> map,MyCallback myCallback){
        RequestParams params = getPostRequestParams(VIDEO_COMMENT_PUBLISH);
        setHeader(params);
        params.addParameter("action",action);
       for (Map.Entry<String,String> entry :map.entrySet()) {
           params.addParameter(entry.getKey(),entry.getValue());
       }
        x.http().post(params,myCallback);
    }

    public static void getShoppingCar(MyCallback myCallback){
        RequestParams params = getPostRequestParams(SHOPPING_CART);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void getProductSku(int product_id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(PRODUCT_SKU);
        setHeader(params);
        params.addParameter("product_id",product_id);
        x.http().post(params,myCallback);
    }

    public static void cancleOrder(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(CANCLE_ORDER);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void getShop(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SHOP);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void getCouponCenter(int type_id,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(COUPONCENTER);
        setHeader(params);
        params.addParameter("type_id",type_id);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }
    public static void getMyCoupon(int status,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(MYCOUPON);
        setHeader(params);
        params.addParameter("status",status);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

    public static void getCoupon(int coupon_id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(GET_COUPON);
        setHeader(params);
        params.addParameter("coupon_id",coupon_id);
        x.http().post(params,myCallback);
    }

    public static void getScoreProduct(int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SCORE_PRODUCT);
        setHeader(params);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

    public static void ScoreChange(String uid,String productid,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SCORE_CHANGE);
        setHeader(params);
        params.addParameter("uid",uid);
        params.addParameter("productid",productid);
        x.http().post(params,myCallback);
    }

    public static void getTypeAndBrand(int parent_id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(TYPE_AND_BRAND);
        setHeader(params);
        params.addParameter("parent_id",parent_id);
        x.http().post(params,myCallback);
    }

    public static void getSecKill(int type,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SEC_KILL);
        setHeader(params);
        params.addParameter("type",type);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

    public static void getProductByTag(int tag,int type,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(PRODUCT_BYTAG);
        setHeader(params);
        params.addParameter("tag",tag);
        params.addParameter("type",type);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }
    public static void getProduct_detail(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(PRODUCT_DETAIL);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void getValidCoupon(int shop_id,int product_id,int type_id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(VALID_COUPON);
        setHeader(params);
        params.addParameter("shop_id",shop_id);
        params.addParameter("product_id",product_id);
        params.addParameter("type_id",type_id);
        x.http().post(params,myCallback);
    }

    public static void creatOrder(Map<String,Object> map,MyCallback myCallback){
        RequestParams params = getPostRequestParams(CREAT_ORDER);
        setHeader(params);
        params.setBodyContent(new Gson().toJson(map));
        x.http().post(params,myCallback);
    }

    public static void orderDetail(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(ORDER_DETAIL);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    /**
     *
     * @param type 订单状态：0：待支付、1：待发货、2：待收货、3：待评价
     * @param page  默认为1
     * @param pageNumber 默认为10
     * @param myCallback   结果回调
     */
    public static void getMyorder(int type,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(MY_ORDER);
        setHeader(params);
        params.addParameter("type",type);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }
  public static void getVideoList(int type,int page,int pageNumber,MyCallback myCallback){
        RequestParams params = getPostRequestParams(VIDEO_LIST);
        setHeader(params);
        params.addParameter("typeid",type);
        params.addParameter("page",page);
        params.addParameter("pageNumber",pageNumber);
        x.http().post(params,myCallback);
    }

    public static void getVideoTypeList(MyCallback myCallback){
        RequestParams params = getPostRequestParams(VIDEO_TYPELIST);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void getMyLike(int id,int action,MyCallback myCallback){
        RequestParams params = getPostRequestParams(LIKE);
        setHeader(params);
        params.addParameter("id",id);
        params.addParameter("action",action);
        x.http().post(params,myCallback);
    }

    public static void getMyVideoDetail(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(VIDEO_DETAIL);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }

    public static void getMyDouDetail(MyCallback myCallback){
        RequestParams params = getPostRequestParams(MDOU_DETAI);
        setHeader(params);
        x.http().post(params,myCallback);
    }

   public static void getBcProduct(MyCallback myCallback){
        RequestParams params = getPostRequestParams(BC_PRODUCT);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void buyBcProduct(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(BC_BUY);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }
    public static void isBcNumber(MyCallback myCallback){
        RequestParams params = getPostRequestParams(IS_BCNUMBER);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void getOrderCoupon(String[] goods,int goods_id,int goods_num,String store_id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(ORDER_COUPON);
        setHeader(params);
        params.addParameter("goods",goods);
        params.addParameter("goods_id",goods_id);
        params.addParameter("goods_num",goods_num);
        params.addParameter("store_id",store_id);
        x.http().post(params,myCallback);
    }

    public static void getMdouList(MyCallback myCallback){
        RequestParams params = getPostRequestParams(MDOULIST);
        setHeader(params);
        x.http().post(params,myCallback);
    }

    public static void getMdouChange(int id,MyCallback myCallback){
        RequestParams params = getPostRequestParams(MDOU_CHANGE);
        setHeader(params);
        params.addParameter("id",id);
        x.http().post(params,myCallback);
    }



}
