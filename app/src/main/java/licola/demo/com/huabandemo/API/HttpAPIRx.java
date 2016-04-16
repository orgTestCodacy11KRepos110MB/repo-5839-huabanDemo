package licola.demo.com.huabandemo.API;

import java.util.List;

import licola.demo.com.huabandemo.Bean.ListPinsBean;
import licola.demo.com.huabandemo.Bean.PinsAndUserEntity;
import licola.demo.com.huabandemo.BoardDetail.BoardDetailBean;
import licola.demo.com.huabandemo.ImageDetail.PinsDetailBean;
import licola.demo.com.huabandemo.Login.TokenBean;
import licola.demo.com.huabandemo.Login.UserMeAndOtherBean;
import licola.demo.com.huabandemo.MyFollowing.FollowingBoardListBean;
import licola.demo.com.huabandemo.MyFollowing.FollowingPinsBean;
import licola.demo.com.huabandemo.UserInfo.UserBoardListBean;
import licola.demo.com.huabandemo.Search.SearchHintBean;
import licola.demo.com.huabandemo.SearchResult.SearchBoardListBean;
import licola.demo.com.huabandemo.SearchResult.SearchImageBean;
import licola.demo.com.huabandemo.SearchResult.SearchPeopleListBean;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by LiCola on  2016/03/17  19:24
 * Retrofit的RxJava接口 网络返回结果为Observable 可以直接观察
 */
public interface HttpAPIRx {

    //https//api.huaban.com/favorite/food_drink?limit=20
    // 模板类型
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpTypeLimitRx(@Header("Authorization") String authorization, @Path("type") String type, @Query("limit") int limit);

    //https//api.huaban.com/favorite/food_drink?max=5445324325&limit=20
    //模板类型 的后续联网 max
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpTypeMaxLimitRx(@Header("Authorization") String authorization, @Path("type") String type, @Query("max") int max, @Query("limit") int limit);


    //https//api.huaban.com/search/hint?q=%E4%BA%BA
    //搜索关键字 提示
    @GET("search/hint")
    Observable<SearchHintBean> httpSearHintBean(@Header("Authorization") String authorization, @Query("q") String key);

    //https//api.huaban.com/search/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=2
    //图片搜索 返回结果跟模板类型差不多
    @GET("search/")
    Observable<SearchImageBean> httpImageSearchRx(@Header("Authorization") String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);

    //https//api.huaban.com/search/boards/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=1
    //画板搜索
    @GET("search/boards/")
    Observable<SearchBoardListBean> httpBoardSearchRx(@Header("Authorization") String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);

    //https//api.huaban.com/search/people/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=2
    //用户搜索
    @GET("search/people/")
    Observable<SearchPeopleListBean> httpPeopleSearchRx(@Header("Authorization") String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);

    //https://api.huaban.com/pins/663478425
    //根据图片id获取详情
    @GET("pins/{pinsId}")
    Observable<PinsDetailBean> httpPinsDetailRx(@Header("Authorization") String authorization, @Path("pinsId") String pinsId);

    //https//api.huaban.com/boards/3514299
    //获取画板的详情
    @GET("boards/{boardId}")
    Observable<BoardDetailBean> httpBoardDetailRx(@Header("Authorization") String authorization, @Path("boardId") String boardId);

    //https//api.huaban.com/boards/19196160/pins?limit=40
    //获取画板的图片集合
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpBoardPinsRx(@Header("Authorization") String authorization, @Path("boardId") String boardId, @Query("limit") int limit);

    //https//api.huaban.com/boards/19196160/pins?limit=40&max=508414882
    //获取画板的图片集合 根据上一个图片的id继续加载
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpBoardPinsMaxRx(@Header("Authorization") String authorization, @Path("boardId") String boardId, @Query("max") int max, @Query("limit") int limit);

    //https//api.huaban.com/pins/654197326/recommend/?page=1&limit=40
    //获取某个图片的推荐图片列表
    @GET("pins/{pinsId}/recommend/")
    Observable<List<PinsAndUserEntity>> httpPinsRecommendRx(@Header("Authorization") String authorization, @Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit);

    //https 用户登录  的第一步
    // Authorization 报头一个固定的值 内容 grant_type=password&password=密码&username=账号
    //传入用户名和密码
    @FormUrlEncoded
    @POST("https://huaban.com/oauth/access_token/")
    Observable<TokenBean> httpsTokenRx(@Header("Authorization") String authorization, @Field("grant_type") String grant,
                                       @Field("username") String username, @Field("password") String password);

    //登录第二步 用上一步结果联网
    @GET("users/me")
    Observable<UserMeAndOtherBean> httpUserRx(@Header("Authorization") String authorization);

    //https://api.huaban.com/following?limit=40
    //我的关注图片  需要 报头 bearer getAccess_token
    @GET("following")
    Observable<FollowingPinsBean> httpsMyFollowingPinsRx(@Header("Authorization") String authorization, @Query("limit") int limit);

    //https://api.huaban.com/following?limit=40&max=670619456
    //我的关注图片的 后续滑动联网
    @GET("following")
    Observable<FollowingPinsBean> httpsMyFollowingPinsMaxRx(@Header("Authorization") String authorization, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/boards/following?page=1&per_page=20
    //我的关注画板
    @GET("boards/following")
    Observable<FollowingBoardListBean> httpsMyFollowingBoardRx(@Header("Authorization") String authorization, @Query("page") int page, @Query("per_page") int per_page);

    //https://api.huaban.com/users/15246080
    //我的个人信息
    @GET("users/{userId}")
    Observable<UserMeAndOtherBean> httpsUserInfoRx(@Header("Authorization") String authorization, @Path("userId") String pinsId);

    //https://api.huaban.com/users/15246080/boards?limit=20
    //用户画板信息
    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> httpsUserBoardRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/16211815/boards?limit=20&max=18375118
    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> httpsUserBoardMaxRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40
    //用户的采集
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40&max=19532400
    //后续滑动联网
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsMaxRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/likes?limit=40
    //用户的喜欢
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/743988/likes?limit=40&max=4338219
    //用户喜欢的后续联网
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsMaxRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

}