package com.example.it_management.API;

import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientAssetResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientCredential.ClientCredentialResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientLicense.ClientsLicenseResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientProjects.ClientsProjectsResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogResponseModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientUser.ClientsUserResponseModel;
import com.example.it_management.ui.Dashboard.DashboardModel;
import com.example.it_management.ui.Inventory.Assets.AssetsResponseModel;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets.AssetsFilesResponseModel;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets.AssetIssuesResponseModel;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.TicketsAssets.AssetTicketsResponseModel;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.TimeLogAssets.AssetTimeLogResponseModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryResponseModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsModels.AssetsModelResponseModel;
import com.example.it_management.ui.Inventory.Attributes.LicenseCategories.LicenseCategoryResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Locations.LocationsModel;
import com.example.it_management.ui.Inventory.Attributes.Locations.LocationsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Manufacturers.ManufacturersModel;
import com.example.it_management.ui.Inventory.Attributes.Manufacturers.ManufacturersResponseModel;
import com.example.it_management.ui.Inventory.Attributes.StatusLabels.StatusLabelsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersResponseModel;
import com.example.it_management.ui.Inventory.Credentials.CredentialsResponseModel;
import com.example.it_management.ui.Inventory.Licences.LicensesResponseModel;
import com.example.it_management.ui.Issues.ActiveIssues.ActiveIssuesResponseModel;
import com.example.it_management.ui.Issues.AllIssues.AllIssuesResponseModel;
import com.example.it_management.ui.KnowledgeBase.KnowledgeBaseArticlesResponseModel;
import com.example.it_management.ui.KnowledgeBase.KnowledgeBaseCategoryResponseModel;
import com.example.it_management.ui.People.Contacs.ContacsResponseModel;
import com.example.it_management.ui.People.Roles.RolesResponseModel;
import com.example.it_management.ui.People.Staff.StaffResponseModel;
import com.example.it_management.ui.People.Users.UsersResponseModel;
import com.example.it_management.ui.Projects.ProjectsResponseModel;
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsResponseModel;
import com.example.it_management.ui.Tickets.AllTickets.AllTicketsResponseModel;
import com.example.it_management.ui.Tickets.AwaitingReply.AwaitingReplyResponseModel;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Streaming;

public interface BaseApiService {
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Login~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @FormUrlEncoded
    @POST("Ceklogin/insertLogin")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Login~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~clients~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Clients/getClients")
    Call<ClientsResponseModel> basClientData();

    @FormUrlEncoded
    @POST("Clients/getClientsId")
    Call<ResponseBody> basClientGetId(
            @Field("name") String name
    );

    @GET("Clients/countClient")
    Call<ResponseBody> basCountClient();

    @FormUrlEncoded
    @PUT("Clients/updateClients")
    Call<ClientsResponseModel> basUpdateClients(
            @Field("id") int idClients,
            @Field("name") String name,
            @Field("asset_tag_prefix") String asset_tag_prefix,
            @Field("license_tag_prefix") String license_tag_prefix,
            @Field("notes") String notes
    );

    @FormUrlEncoded
    @POST("Clients/insertClients")
    Call<ClientsResponseModel> basInsertClients(
            @Field("name") String name,
            @Field("asset_tag_prefix") String asset_tag_prefix,
            @Field("license_tag_prefix") String license_tag_prefix,
            @Field("notes") String notes
    );

    @FormUrlEncoded
    @POST("Clients/deleteClients")
    Call<ClientsResponseModel> basDeleteClients(
            @Field("id") int idClients
    );

    //==========detail asset client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetAssets")
    Call<ClientAssetResponseModel> basGetAssetsClient(
            @Field("idClient") int idClients
    );
    //==========detail credential client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetCredentials")
    Call<ClientCredentialResponseModel> basGetCredentialClient(
            @Field("idClient") int idClients
    );
    //==========detail file client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetFiles")
    Call<ClientsFilesResponseModel> basGetFilesClient(
            @Field("idClient") int idClients
    );
    //==========detail Issues client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetIssues")
    Call<ClientsIssuesResponseModel> basGetIssuesClient(
            @Field("idClient") int idClients
    );
    //==========detail License client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetLicenses")
    Call<ClientsLicenseResponseModel> basGetLicenseClient(
            @Field("idClient") int idClients
    );
    //==========detail Project client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetProject")
    Call<ClientsProjectsResponseModel> basGetProjectClient(
            @Field("idClient") int idClients
    );
    //==========detail Ticket client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetTicket")
    Call<ClientsTicketsResponseModel> basGetTicketClient(
            @Field("idClient") int idClients
    );
    //==========detail TimeLog client=================//
    @FormUrlEncoded
    @POST("Clients/clientGetTimeLog")
    Call<ClientsTimeLogResponseModel> basGetTimeLogClient(
            @Field("idClient") int idClients
    );
    //==========detail User client=================//

    @FormUrlEncoded
    @POST("Clients/clientGetUser")
    Call<ClientsUserResponseModel> basGetUserClient(
            @Field("idClient") int idClients
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End clients~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Projects ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Projects/getProjectJoin")
    Call<ProjectsResponseModel> basGetProjects();
    @GET("Projects/countProject")
    Call<ResponseBody> basCountProject();
    @FormUrlEncoded
    @POST("Projects/getProjectsId")
    Call<ResponseBody> basProjectsGetId(
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("Projects/insertProjects")
    Call<ResponseBody> basInputProject(
            @Field("idClient") int idClient,
            @Field("tag") String tag,
            @Field("name") String name,
            @Field("notes") String notes,
            @Field("progress") int progress,
            @Field("start") String start,
            @Field("end") String end
    );
    @FormUrlEncoded
    @PUT("Projects/updateProjects")
    Call<ResponseBody> basUpdateProject(
            @Field("id") int id,
            @Field("idClient") int idClient,
            @Field("tag") String tag,
            @Field("name") String name,
            @Field("notes") String notes,
            @Field("progress") int progress,
            @Field("start") String start,
            @Field("end") String end
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END Projects ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ KnowledgeBase ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("KnowledgeBase/getKbCategories")
    Call<KnowledgeBaseCategoryResponseModel> basGetKbCategory();
    @FormUrlEncoded
    @POST("KnowledgeBase/getKbArticles")
    Call<KnowledgeBaseArticlesResponseModel> basGetKbArticles(
            @Field("categoryid") int categoryid
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End knowledgebase~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Assets ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Assets/getAssets")
    Call<AssetsResponseModel> basGetAssets();
    @FormUrlEncoded
    @POST("Assets/insertAssets")
    Call<ResponseBody> basInputAssets(
            @Field("idCategory") int idCategory,
            @Field("idAdmin") int idAdmin,
            @Field("idClient") int idClient,
            @Field("idUser") int idUser,
            @Field("idManu") int idManu,
            @Field("idModel") int idModel,
            @Field("idSupp") int idSupp,
            @Field("idStatus") int idStatus,
            @Field("purchase") String purchase,
            @Field("warranty") int warranty,
            @Field("tag") String tag,
            @Field("name") String name,
            @Field("serial") String serial,
            @Field("notes") String notes,
            @Field("location") int location
    );
    @FormUrlEncoded
    @PUT("Assets/updateAssets")
    Call<ResponseBody> basUpdateAssets(
            @Field("id") int idAsset,
            @Field("idCategory") int idCategory,
            @Field("idAdmin") int idAdmin,
            @Field("idClient") int idClient,
            @Field("idUser") int idUser,
            @Field("idManu") int idManu,
            @Field("idModel") int idModel,
            @Field("idSupp") int idSupp,
            @Field("idStatus") int idStatus,
            @Field("purchase") String purchase,
            @Field("warranty") int warranty,
            @Field("tag") String tag,
            @Field("name") String name,
            @Field("serial") String serial,
            @Field("notes") String notes,
            @Field("location") int location
    );
    @FormUrlEncoded
    @POST("Assets/getAssetsId")
    Call<ResponseBody> basAssetsGetId(
            @Field("name") String name
    );
    @GET("Assets/countAssets")
    Call<ResponseBody> basCountAssets();
    //==========detail file asset=================//
    @FormUrlEncoded
    @POST("Assets/assetGetFiles")
    Call<AssetsFilesResponseModel> basGetFilesAssets(
            @Field("idAsset") int idAsset
    );
    //==========End detail file asset=================//
    //==========detail issues asset=================//
    @FormUrlEncoded
    @POST("Assets/assetGetIssues")
    Call<AssetIssuesResponseModel> basGetIssuesAssets(
            @Field("idAsset") int idAsset
    );
    //==========end detail Issues client=================//
    //==========detail ticket asset=================//
    @FormUrlEncoded
    @POST("Assets/assetGetTicket")
    Call<AssetTicketsResponseModel> basGetTicketAssets(
            @Field("idAsset") int idAsset
    );
    //==========ens detail Ticket client=================//
    //==========detail Time Log asset=================//
    @FormUrlEncoded
    @POST("Assets/assetGetTimeLog")
    Call<AssetTimeLogResponseModel> basGetTimeLogAssets(
            @Field("idAsset") int idAsset
    );
    //==========detail Issues client=================//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Assets ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ License ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Licenses/getLicenses")
    Call<LicensesResponseModel> basGetLicense();
    @GET("Licenses/countLicense")
    Call<ResponseBody> basCountLicense();
    @FormUrlEncoded
    @POST("Licenses/getCategoryId")
    Call<ResponseBody> basLicenseCategoryGetId(
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("Licenses/insertLicense")
    Call<ResponseBody> basInputLicense(
            @Field("idCategory") int idCategory,
            @Field("idClient") int idClient,
            @Field("idSupp") int idSupp,
            @Field("idStatus") int idStatus,
            @Field("tag") String tag,
            @Field("name") String name,
            @Field("seat") String seat,
            @Field("serial") String serial,
            @Field("notes") String notes
    );

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End License ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Credential ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Credentials/getCredentials")
    Call<CredentialsResponseModel> basGetCredential();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Credential ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Attributes/getAssetCategory")
    Call<AssetsCategoryResponseModel> basGetAssetsCategory();
    @FormUrlEncoded
    @POST("Attributes/getCategoryId")
    Call<ResponseBody> basCategoryGetId(
            @Field("name") String name
    );
    @GET("Attributes/getModels")
    Call<AssetsModelResponseModel> basGetAssetsModel();
    @FormUrlEncoded
    @POST("Attributes/getModelId")
    Call<ResponseBody> basModelGetId(
            @Field("name") String name
    );
    @GET("Attributes/getLicenseCategory")
    Call<LicenseCategoryResponseModel> basGetLicenseCategory();

    @GET("Attributes/getLocations")
    Call<LocationsResponseModel> basGetLocations();
    @FormUrlEncoded
    @POST("Attributes/getLocationId")
    Call<ResponseBody> basLocationGetId(
            @Field("name") String name
    );
    @GET("Attributes/getManufacturers")
    Call<ManufacturersResponseModel> basGetManufacturers();
    @FormUrlEncoded
    @POST("Attributes/getManufacturerId")
    Call<ResponseBody> basManufacturerGetId(
            @Field("name") String name
    );
    @GET("Attributes/getLabels")
    Call<StatusLabelsResponseModel> basGetLabels();
    @GET("Attributes/getSuppliers")
    Call<SuppliersResponseModel> basGetSuppliers();
    @FormUrlEncoded
    @POST("Attributes/getSupplierId")
    Call<ResponseBody> basSupplierGetId(
            @Field("name") String name
    );

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Attributes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Tickets ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Tickets/getAwaiting")
    Call<AwaitingReplyResponseModel> basGetAwaitingReply();
    @GET("Tickets/getActive")
    Call<ActiveTicketsResponseModel> basGetActiveTickets();
    @GET("Tickets/getAll")
    Call<AllTicketsResponseModel> basGetAllTickets();
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Tickets ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Issues ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @FormUrlEncoded
    @POST("Issues/insertIssues")
    Call<ResponseBody> basInputIssues(
            @Field("idClient") int idClient,
            @Field("idAdmin") int idAdmin,
            @Field("idAsset") int idAsset,
            @Field("idProject") int idProject,
            @Field("type") String type,
            @Field("priority") String priority,
            @Field("status") String status,
            @Field("due") String due,
            @Field("descript") String descript,
            @Field("name") String name
    );
    @GET("Issues/getActiveIssues")
    Call<ActiveIssuesResponseModel> basGetActiveIssues();
    @GET("Issues/getAllIssues")
    Call<AllIssuesResponseModel> basGetAllIssues();
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Issues ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Contacts ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Akun/getAllContacts")
    Call<ContacsResponseModel> basGetContacs();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Contacts ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Roles ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Akun/getAllRoles")
    Call<RolesResponseModel> basGetRoles();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Roles ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Staff ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Akun/getAkunAdmin")
    Call<StaffResponseModel> basGetStaff();
    @FormUrlEncoded
    @POST("Akun/getAkunId")
    Call<ResponseBody> basAkunGetId(
            @Field("name") String name
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Staff ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Roles ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @GET("Akun/getAkunUser")
    Call<UsersResponseModel> basGetUser();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Roles ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GetNameById ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @FormUrlEncoded
    @POST("Clients/getClientsNameByid")
    Call<ResponseBody> basClientGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Attributes/getCategoryName")
    Call<ResponseBody> basCategoryGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Attributes/getModelName")
    Call<ResponseBody> basModelGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Attributes/getLocationName")
    Call<ResponseBody> basLocationGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Attributes/getManufacturerName")
    Call<ResponseBody> basManufacturerGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Attributes/getSupplierName")
    Call<ResponseBody> basSupplierGetName(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Akun/getAkunName")
    Call<ResponseBody> basAkunGetName(
            @Field("id") int id
    );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End GetNameById ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
}
