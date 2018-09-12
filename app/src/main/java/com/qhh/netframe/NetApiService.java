package com.qhh.netframe;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/9/12 10:55
 * @des 数据接口管理服务
 * @packgename com.qhh.netframe
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface NetApiService {

    @GET("/article/list/{page}/json")
    Observable<ResponseBody> getArticals(@Path("page") int page);

}
