package com.ion.xgeeks_android_challenge.Activities

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class getResultsByTag {
    public lateinit var co : Context
    public var getPostUrl1 = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=af6def8ce345082232351cee269a44f2&tags="
    public var getPostUrl2 = "&format=json&nojsoncallback=1"
    public lateinit var url: String;
    public fun get(rec: RecyclerView, tags: ArrayList<String>){
        val requestQueue = Volley.newRequestQueue(co)
        var catList = mutableListOf<Category>()
        for(i in 0 until tags.size) {
            url = getPostUrl1+tags[i]+getPostUrl2
            var postList = catList[i].postList
            val request = JsonObjectRequest(Request.Method.GET, getPostUrl1, null, {
                    response ->try {
                val jsonArray = response.getJSONArray("photo")
                for (i in 0 until jsonArray.length()) {
                    val distr = jsonArray.getJSONObject(i)
                    postList.add(
                        Post(
                            distr.getInt("id"),
                            distr.getInt("secret"),
                            distr.getInt("server"),
                            distr.getInt("farm"),
                            distr.getString("title"),
                        )
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            }, { error -> error.printStackTrace() })
            requestQueue?.add(request)
        }
        rec.layoutManager = LinearLayoutManager(co)
        val adapter = PostAdapter(catList)
        rec.adapter = adapter
    }
}