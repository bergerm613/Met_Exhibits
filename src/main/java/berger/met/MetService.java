package berger.met;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService
{
    @GET("/public/collection/v1/departments")
    Call<DepartmentsFeed> getDepartments();

    @GET("/public/collection/v1/objects?")
    Call<DepartmentObjectsFeed> getDepartmentObjects(@Query("departmentIds") int departmentID);

    @GET("/public/collection/v1/objects/{objectId}")
    Call<ObjectFeed> getObject(@Path("objectId") int objectId);
}
