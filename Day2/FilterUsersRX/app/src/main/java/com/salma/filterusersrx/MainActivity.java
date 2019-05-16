package com.salma.filterusersrx;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Observable<User> usersObservable;
    ArrayList<String> females;
    RecyclerView users;
    UsersAdapter usersAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        females=new ArrayList<>();
        users=findViewById(R.id.usersRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        usersAdapter=new UsersAdapter(this,females);
        users.setAdapter(usersAdapter);
        users.setLayoutManager(linearLayoutManager);
        Observer<User> usersObserver = getUserObserver();
        usersObservable = Observable.fromArray(new User("salma","female"),new User("amr","male"),new User("sahar","female"),new User("kamal","male")).filter(new Predicate<User>() {
            @Override
            public boolean test(User user) throws Exception {

                return user.getGender().equals("female");
            }

        });
        usersObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersObserver);
    }
    private Observer<User> getUserObserver() {
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                Log.i("user",user.getName());
                females.add(user.getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                usersAdapter.notifyDataSetChanged();
            }
        };
    }
}
