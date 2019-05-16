package com.salma.searchrx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    List<String> listWords;
    List<String> stagedArea;
    RecyclerView recyclerView;
    WordAdapter wordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        inputText = findViewById(R.id.editText);
        listWords = new ArrayList<>();
        stagedArea=new ArrayList<>();
        listWords.add("salma");
        listWords.add("kamal");
        listWords.add("radya");
        listWords.add("nouran");
        listWords.add("amr");
        stagedArea.addAll(listWords);
        wordAdapter=new WordAdapter(this,stagedArea);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(wordAdapter);

        RxTextView.textChangeEvents(inputText)
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
                            stagedArea.addAll(listWords);
                            wordAdapter.notifyDataSetChanged();

                        } else {
                            stagedArea.clear();
                            for (String word : listWords) {
                                if (word.startsWith(textViewTextChangeEvent.text().toString())) {
                                    stagedArea.add(word);
                                    wordAdapter.notifyDataSetChanged();

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

    }
}
