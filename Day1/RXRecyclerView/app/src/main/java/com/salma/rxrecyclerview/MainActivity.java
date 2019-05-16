package com.salma.rxrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    MainActivityAdapter mainActivityAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> capital;
    ArrayList<String> small;
    Observable<String> colorObservable;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        capital = new ArrayList<>();
        small = new ArrayList<>();
        mainActivityAdapter = new MainActivityAdapter(this, capital, small);
        recyclerView = findViewById(R.id.listRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainActivityAdapter);
        colorObservable = Observable.just("Red", "Green", "Blue", "Orange");
        DisposableObserver<String> capitalObserver = getCapitalObserver();
        DisposableObserver<String> smallObserver = getSmallObserver();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(colorObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(smallObserver));
        compositeDisposable.add(colorObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(capitalObserver));


    }

    private DisposableObserver<String> getCapitalObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                capital.add(s.toUpperCase());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainActivityAdapter.notifyDataSetChanged();
            }
        };
    }

    private DisposableObserver<String> getSmallObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                small.add(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainActivityAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
