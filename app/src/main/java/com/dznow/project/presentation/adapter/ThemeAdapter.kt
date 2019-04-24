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
import com.dznow.project.presentation.model.Theme

class ThemeAdapter(themeList: List<Theme>,
                   private val context : Context,
                   private val listener: ThemeAdapterListner):
    RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private var mThemes: List<Theme> = themeList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val optionView = inflater.inflate(R.layout.theme_row_item, p0, false)
        return ViewHolder(optionView)
    }

    override fun getItemCount(): Int {
        return mThemes.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val theme = mThemes[p1]
        p0.title.text = theme.name
        p0.articles.text = theme.articles.toString() + " articles"
        Glide.with(context).load(theme.image).into(p0.image)
        p0.itemView.setOnClickListener {
            //p0.title.isSelected = !p0.title.isSelected
            //p0.image.isSelected = !p0.image.isSelected
            listener.onThemeClicked(mThemes[p1])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.theme_textView)
        var articles : TextView = itemView.findViewById(R.id.articles_textView)
        var image : ImageView = itemView.findViewById(R.id.thumbnail)
    }

    interface ThemeAdapterListner {
        fun onThemeClicked(theme: Theme)
    }
}
