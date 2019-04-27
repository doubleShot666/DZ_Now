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

class ThemeSelectionAdapter(themeList: List<Pair<Boolean,Theme>>,
                            private val context : Context,
                            private val listener: ThemeAdapterListner):
    RecyclerView.Adapter<ThemeSelectionAdapter.ViewHolder>() {

    private var mThemes: List<Pair<Boolean,Theme>> = themeList

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
        p0.title.text = theme.second.name
        Glide.with(context).load(theme.second.image).into(p0.image)


        if(theme.first){
            p0.selected.isSelected = true
            p0.selected.setImageResource(R.color.colorAccent)
        }

        p0.itemView.setOnClickListener {
            p0.selected.isSelected = !p0.selected.isSelected
            if(p0.selected.isSelected)
                p0.selected.setImageResource(R.color.colorAccent)
            else
                p0.selected.setImageResource(R.color.trasnparent)
            listener.onThemeClicked(mThemes[p1].second)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.theme_textView)
        var image : ImageView = itemView.findViewById(R.id.thumbnail)
        var selected : ImageView = itemView.findViewById(R.id.selected_imageView)
    }

    interface ThemeAdapterListner {
        fun onThemeClicked(theme: Theme)
    }
}
