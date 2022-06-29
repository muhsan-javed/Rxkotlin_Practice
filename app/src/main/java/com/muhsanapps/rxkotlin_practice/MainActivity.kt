package com.muhsanapps.rxkotlin_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhsanapps.rxkotlin_practice.adapters.FoodAdapter
import com.muhsanapps.rxkotlin_practice.models.Food
import com.muhsanapps.rxkotlin_practice.network.Retrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class MainActivity : AppCompatActivity() {
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         itemRecyclerView = findViewById(R.id.foodRecyclerView);

        foodAdapter = FoodAdapter(this, ArrayList<Food>())

        itemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = foodAdapter
        }

        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(getObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response-> getObserver(response as ArrayList<Food>)},
                {t-> onFailure(t)})
        )

    }

    private fun getObservable(): Observable<List<Food>> {

        return Retrofit.api.getAllData()
    }

    private fun getObserver(fooList: ArrayList<Food>){

        if (fooList != null && fooList.size>0){
            
            foodAdapter.setData(fooList)
        }
    }
    
    private fun onFailure(t:Throwable){
        Toast.makeText(this, "$t", Toast.LENGTH_SHORT).show()
    }
}