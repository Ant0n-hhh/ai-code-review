package cn.ant0n.aicodereview.utils.interfaces;


import cn.ant0n.aicodereview.domain.model.request.PushCommentRequest;
import cn.ant0n.aicodereview.domain.model.response.PushCommentResponse;
import cn.ant0n.aicodereview.domain.model.response.SingleCommitResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface IGitRestService {
    @GET("repos/{owner}/{repository}/commits/{branch}")
    @Headers({
            "Accept: application/vnd.github+json",
            "X-GitHub-Api-Version: 2022-11-28"
    })
    Call<SingleCommitResponse> getDiff(@Path("owner")String owner,
                                       @Path("repository")String repository,
                                       @Path("branch")String branch,
                                       @Header("Authorization")String authorization);


    @POST("repos/{owner}/{repository}/commits/{sha}/comments")
    @Headers({
            "Accept: application/vnd.github+json",
            "X-GitHub-Api-Version: 2022-11-28"
    })
    Call<PushCommentResponse> pushComment(@Path("owner")String owner,
                                          @Path("repository")String repository,
                                          @Path("sha")String sha,
                                          @Body PushCommentRequest request,
                                          @Header("Authorization")String authorization);
}
