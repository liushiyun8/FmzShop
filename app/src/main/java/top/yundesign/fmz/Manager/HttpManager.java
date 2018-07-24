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
    public static final String HOST="http://i.fengmaozhai.com";
    public static final String  LOGINURL=HOST+"/api/user/login",
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
                                MYCOUPON=HOST+"/api/myCoupon";











    @NonNull
    private static RequestParams getPostRequestParams(String url) {
        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);
        params.setAsJsonContent(true);
        return params;
    }

    private static void setHeader(RequestParams params) {
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
        params.addParameter("username",phone);
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
        params.addParameter("phone",phone);
        params.addParameter("type",type);
        params.addParameter("code",code);
        params.addBodyParameter("password",pwd);
        x.http().post(params,myCallback);
    }

    public static void SendMessage(String phone,MyCallback myCallback){
        RequestParams params = getPostRequestParams(SENDEMSG);
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

    public static void getCoupon(int type_id,int page,int pageNumber,MyCallback myCallback){
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










}
