package com.salma.evenindecesrx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView numbersView;
    Observable<int[]> numbersObservable;
    ArrayList<Integer> evenIndecesValues;
    ArrayList<Integer> listToSend;
    RecyclerView numbers;
    NumbersAdapter numbersAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = findViewById(R.id.list);
        listToSend=new ArrayList<>();
        numbersAdapter=new NumbersAdapter(this,listToSend);
        linearLayoutManager=new LinearLayoutManager(this);
        numbers.setLayoutManager(linearLayoutManager);
        numbers.setAdapter(numbersAdapter);
        evenIndecesValues=new ArrayList<>();
        int[] numArray= { 0, 5, 8, 10, 3, 6, 4, 7, 9, 10};
        Observer<ArrayList<Integer>> numbersObserver = getNumberObserver();
        numbersObservable = Observable.just(numArray);
        numbersObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<int[], ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> apply(int[] ints) throws Exception {
                for(int i =0 ;i<ints.length;i++)
                {
                    if(i%2==0){
                        evenIndecesValues.add(ints[i]);
                    }
                }
                return evenIndecesValues;
            }
        })
                .subscribe(numbersObserver);
    }

    private Observer<ArrayList<Integer>> getNumberObserver() {
        return new Observer<ArrayList<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<Integer> numbers) {
                for(int i =0;i<numbers.size();i++){
                    if(numbers.get(i)%2==0){
                        listToSend.add(numbers.get(i));
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                numbersAdapter.notifyDataSetChanged();
            }
        };
    }
}
