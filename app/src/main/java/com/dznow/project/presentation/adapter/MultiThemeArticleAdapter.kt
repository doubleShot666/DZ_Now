package com.dznow.project.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dznow.project.R
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.utils.MyRecyclerView
import android.support.v7.widget.LinearLayoutManager



class MultiThemeArticleAdapter(articleList: HashMap<String,List<Article>>,
                               private val context : Context,
                               private val rowRessource: Int,
                               private val listener: AdapterListner):
    RecyclerView.Adapter<MultiThemeArticleAdapter.ViewHolder>(), ArticleAdapter.ArticleAdapterListner {


    private var mArticles: HashMap<String,List<Article>> = articleList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val optionView = inflater.inflate(rowRessource, p0, false)
        return ViewHolder(optionView)
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.itemView.background = context.resources.getDrawable(R.color.white)
        p0.itemView.elevation = 2.0f
        p0.itemView.bottom = 8

        val entries = mArticles.entries.toTypedArray()
        p0.more_btn.text = "Tout"
        p0.title.text = entries[p1].key
        val adapter = ArticleAdapter(entries[p1].value,context,R.layout.article_horizental_row_item,this)
        p0.recyclerView.adapter = adapter
        p0.recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        p0.recyclerView.layoutManager = layoutManager
        p0.more_btn.setOnClickListener {
            listener.onThemeClicked(entries[p1].key)
        }

    }

    override fun onArticleClicked(article: Article) {
        listener.onArticleClicked(article)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.fragment_title)
        var more_btn : TextView = itemView.findViewById(R.id.more_btn)
        var recyclerView : MyRecyclerView = itemView.findViewById(R.id.recyclerView)
    }

    interface AdapterListner {
        fun onThemeClicked(theme: String)
        fun onArticleClicked(article: Article)
    }
}
