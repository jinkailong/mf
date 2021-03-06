package com.jzhu.study.datalayer.net.api;

import com.jzhu.study.datalayer.BaseGankIoResp;
import com.jzhu.study.datalayer.entities.GankFLEntities;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

/**
 * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 请求个数： 数字，大于0
 第几页：数字，大于0
 */
public interface GankIoApi {

    @GET("福利/{rows}/{pageNum}")
    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlList(@Path("rows") int rows, @Path("pageNum") int pageNum);

}
