package com.salma.searchlocalandremoterx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
Boolean remoteSearch;
JsonResponse contactsResponse;
List<Contact> contactsList;
RecyclerView contactsRecyclerView;
LinearLayoutManager linearLayoutManager;
ContactsAdapter contactsAdapter;
List<Contact> stagedArea;
EditText userInput;
Retrofit retrofit;
ContactsApi request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        userInput=findViewById(R.id.searchInputTF);
        contactsList=new ArrayList<>();
        stagedArea=new ArrayList<>();
        contactsRecyclerView=findViewById(R.id.contactsRecyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        contactsAdapter=new ContactsAdapter(this,stagedArea);
        contactsRecyclerView.setLayoutManager(linearLayoutManager);
        contactsRecyclerView.setAdapter(contactsAdapter);

        Intent intent = getIntent();
        remoteSearch=intent.getBooleanExtra("searchType",false);
        retrofit = new Retrofit.Builder().baseUrl("https://api.androidhive.info/").addConverterFactory(GsonConverterFactory.create()).build();
        request=retrofit.create(ContactsApi.class);
        Call<List<Contact>> call=request.getContacts(null);

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contactsList.addAll(response.body());
                stagedArea.addAll(contactsList);
                contactsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.i("failed",t.getMessage());
            }
        });

        if (!remoteSearch){

            RxTextView.textChangeEvents(userInput)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TextViewTextChangeEvent>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                            if (textViewTextChangeEvent.text().length() == 0) {
                                stagedArea.clear();
                                stagedArea.addAll(contactsList);
                                contactsAdapter.notifyDataSetChanged();

                            } else {
                                stagedArea.clear();
                                for (Contact contact : contactsList) {
                                    if (contact.getName().toLowerCase().startsWith(textViewTextChangeEvent.text().toString().toLowerCase())) {
                                        stagedArea.add(contact);
                                        contactsAdapter.notifyDataSetChanged();

                                    }
                                    else if (contact.getPhone().contains(textViewTextChangeEvent.text().toString())) {
                                        stagedArea.add(contact);
                                        contactsAdapter.notifyDataSetChanged();

                                    }
                                }
                            }
                        }
                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{

            RxTextView.textChangeEvents(userInput)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TextViewTextChangeEvent>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                            if (textViewTextChangeEvent.text().length() == 0) {
                                stagedArea.clear();
                                stagedArea.addAll(contactsList);
                                contactsAdapter.notifyDataSetChanged();

                            } else {
                                stagedArea.clear();
                                Call<List<Contact>> call=request.getContacts(textViewTextChangeEvent.text().toString());
                                Log.d("URL", call.toString());
                                call.enqueue(new Callback<List<Contact>>() {
                                    @Override
                                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                                        stagedArea.addAll(response.body());
                                        contactsAdapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onFailure(Call<List<Contact>> call, Throwable t) {
                                        Log.i("failed",t.getMessage());
                                    }
                                });

                            }
                        }
                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
