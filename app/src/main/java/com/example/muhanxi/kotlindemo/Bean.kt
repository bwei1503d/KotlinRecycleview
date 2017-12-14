package com.example.muhanxi.kotlindemo

/**
 * Created by muhanxi on 17/12/14.
 */


data class Bean(
		val error_code: Int, //200
		val reason: String, //请求成功！
		val result: Result
)

data class Result(
		val total: Int, //15767
		val limit: Int, //20
		val bookList: List<Book>
)

data class Book(
		val name: String, //灵神考试
		val type: String, //少年漫画
		val area: String, //国漫
		val des: String,
		val finish: Boolean, //false
		val lastUpdate: Int, //20150603
		val coverImg: String //http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad613.jpg
)

