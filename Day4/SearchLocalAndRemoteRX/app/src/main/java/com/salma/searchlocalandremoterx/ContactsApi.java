package com.salma.searchlocalandremoterx;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContactsApi {
@GET("json/contacts.php")
Call<List<Contact>> getContacts(@Query("search") String user);
    // it could be written as call<List<worldPopulation>> get worldCountries;
    // but we made the class JsonResponse that represents list of world countries
    // because the json file is composed of array of world population objects
    // not composed of world population objects directly

}
