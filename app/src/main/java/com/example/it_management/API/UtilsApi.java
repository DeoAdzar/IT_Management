package com.example.it_management.API;

public class UtilsApi {
//   public static final String BASE_URL = "http://vsga-deo.000webhostapp.com/it_management_api/";
   public static final String BASE_URL = "http://pkl.madiunkab.go.id/deo/IT_Management_api/";
    public static final String FILE_URL = "https://pkl.madiunkab.go.id/deo/it_management/admin/?qa=download&id=";
    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }
}
