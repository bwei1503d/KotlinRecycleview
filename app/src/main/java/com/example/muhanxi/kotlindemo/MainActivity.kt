package com.example.muhanxi.kotlindemo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.internal.util.BackpressureHelper.add
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : Activity() {


    lateinit var adapter : IAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter =  IAdapter(this)
        recyclerview.adapter =adapter




        getData()
        

    }


    fun getData(){



        var retrofit = Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        var iservice : IService =  retrofit.create(IService::class.java)

        iservice.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    next ->
                    var result =  next.string()
                    println(result)

                    var gson = Gson()
                    var bean = gson.fromJson(result,Bean::class.java)

                    adapter.addData(bean)


                } , {
                    t ->

                } )


//        iservice.getData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    next ->
//
//                    // onnext
//
//                },{
//                    t ->
////                    error
//                },{
////                    oncomplete
//                },{
//                    d ->
////                    d.dispose()
//                })


    }




}
