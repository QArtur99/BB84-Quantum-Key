package com.artf.bb84;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    List<Integer> aliceListA, aliceListB;
    List<Integer> bobListA, bobListB;
    List<String> aliceQubitState;
    List<String> bobQubitState;

    List<Integer> secretKeyList;

    @BindView(R.id.recyclerViewAliceA) RecyclerView recyclerViewAliceA;
    @BindView(R.id.recyclerViewAliceB) RecyclerView recyclerViewAliceB;
    @BindView(R.id.recyclerViewAliceQubit) RecyclerView recyclerViewAliceQubit;

    @BindView(R.id.recyclerViewBobA) RecyclerView recyclerViewBobA;
    @BindView(R.id.recyclerViewBobB) RecyclerView recyclerViewBobB;
    @BindView(R.id.recyclerViewBobQubit) RecyclerView recyclerViewBobQubit;

    @BindView(R.id.recyclerViewSecretKey) RecyclerView recyclerViewSecretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        launch();
    }

    private void launch() {
        generateSecretKey();
        if (checkSecretKey() >= 4) {
            setAdapters();
        } else {
            launch();
        }
    }

    private void generateSecretKey() {
        createLists();
        generateValues();
        getSecretKey(aliceQubitState, bobQubitState);
    }

    private void createLists() {
        aliceListA = new ArrayList<>();
        aliceListB = new ArrayList<>();
        aliceQubitState = new ArrayList<>();

        bobListA = new ArrayList<>();
        bobListB = new ArrayList<>();
        bobQubitState = new ArrayList<>();

        secretKeyList = new ArrayList<>();
    }

    private void generateValues() {
        getRandomList(aliceListA);
        getRandomList(aliceListB);

        getRandomList(bobListA);
        getRandomList(bobListB);

        getQubitState(aliceQubitState, aliceListA, aliceListB);
        getQubitState(bobQubitState, bobListA, bobListB);
    }

    private int checkSecretKey() {
        int numberOfSigns = 0;
        for (Integer integer : secretKeyList) {
            if (integer != null) {
                numberOfSigns++;
            }
        }
        return numberOfSigns;
    }

    private void setAdapters() {
        setIntegerAdapter(recyclerViewAliceA, aliceListA, 0);
        setIntegerAdapter(recyclerViewAliceB, aliceListB, 1);
        setStringAdapter(recyclerViewAliceQubit, aliceQubitState);

        setIntegerAdapter(recyclerViewBobA, bobListA, 0);
        setIntegerAdapter(recyclerViewBobB, bobListB, 1);
        setStringAdapter(recyclerViewBobQubit, bobQubitState);

        setIntegerAdapter(recyclerViewSecretKey, secretKeyList, 0);
    }

    private void setIntegerAdapter(RecyclerView recyclerView, List<Integer> list, int id) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        IntegerAdapter postsAdapter = new IntegerAdapter(list, id);
        recyclerView.setAdapter(postsAdapter);
    }

    private void setStringAdapter(RecyclerView recyclerView, List<String> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        StringAdapter postsAdapter = new StringAdapter(list);
        recyclerView.setAdapter(postsAdapter);
    }

    public void getSecretKey(List<String> aliceQubitState, List<String> bobQubitState) {
        for (int i = 0; aliceQubitState.size() > i; i++) {
            String aliceQubitStateValue = aliceQubitState.get(i);
            String bobQubitStateValue = bobQubitState.get(i);
            if (aliceQubitStateValue.equals(bobQubitStateValue)) {
                secretKeyList.add(bobListA.get(i));
            } else {
                secretKeyList.add(null);
            }
        }
    }

    public void getRandomList(List<Integer> listOfRandomBits) {
        int i = 0;
        while (10 > i) {
            listOfRandomBits.add(new Random().nextInt(2));
            i++;
        }
    }

    public void getQubitState(List<String> qubitState, List<Integer> listA, List<Integer> listB) {
        for (int i = 0; aliceListA.size() > i; i++) {
            String qubitStateString = String.valueOf(listA.get(i)) + String.valueOf(listB.get(i));
            qubitState.add(qubitStateString);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogin:
                launch();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}
