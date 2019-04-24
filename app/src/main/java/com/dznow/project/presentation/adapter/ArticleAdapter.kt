package com.dznow.project.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dznow.project.R
import com.dznow.project.presentation.model.Article

class ArticleAdapter(articleList: List<Article>,
                     private val context : Context,
                     private val rowRessource: Int,
                     private val listener: ArticleAdapterListner):
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private var mArticles: List<Article> = articleList

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
        val article = mArticles[p1]
        p0.title.text = article.title
        p0.sourceName.text = article.source.toString()
        p0.editionTime.text = article.elapsedTime
        Glide.with(context).load(article.image).into(p0.articleImage)
        p0.itemView.setOnClickListener {
            //p0.title.isSelected = !p0.title.isSelected
            //p0.image.isSelected = !p0.image.isSelected
            listener.onArticleClicked(mArticles[p1])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.article_title)
        var sourceName : TextView = itemView.findViewById(R.id.source_name)
        var sourceImage : ImageView = itemView.findViewById(R.id.source_imageView)
        var editionTime : TextView = itemView.findViewById(R.id.article_editionTime)
        var articleImage : ImageView = itemView.findViewById(R.id.thumbnail)
    }

    interface ArticleAdapterListner {
        fun onArticleClicked(article : Article)
    }
}
