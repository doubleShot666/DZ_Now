package com.dznow.project.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dznow.project.R
import com.dznow.project.presentation.model.*
import android.net.Uri
import android.widget.*
import com.bumptech.glide.Glide


class ArticleElementsAdapter(context: Context,resource: Int, elementList: ArrayList<ArticleElement>) :
    ArrayAdapter<ArticleElement>(context,resource,elementList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val mConvertView : View
        // Get the data item for this position
        val element = getItem(position)

        when(element){
            is TextElement -> {
                mConvertView = LayoutInflater.from(context).inflate(R.layout.article_text_container, parent, false)
                val text = mConvertView!!.findViewById(R.id.textView) as TextView
                text.text = element.text
                return mConvertView
            }

            is VideoElement -> {
                mConvertView = LayoutInflater.from(context).inflate(R.layout.article_video_container, parent, false)
                val mc = MediaController(context)
                val video = mConvertView!!.findViewById(R.id.videoView) as VideoView
                video.setMediaController(mc)
                val u = Uri.parse(element.videoUrl)
                video.setVideoURI(u)
                video.start()
                return mConvertView
            }

            is AnnotationElement -> {
                mConvertView = LayoutInflater.from(context).inflate(R.layout.article_annotation_container, parent, false)
                val annotation = mConvertView!!.findViewById(R.id.annotaion_textView) as TextView
                annotation.text = element.annotation
                return mConvertView
            }

            is ImageElement -> {
                mConvertView = LayoutInflater.from(context).inflate(R.layout.article_image_container, parent, false)
                Glide.with(context).load(element.imgUrl).into(mConvertView!!.findViewById(R.id.image_article) as ImageView)
                return mConvertView
            }
            else -> return convertView!!
        }


    }
}