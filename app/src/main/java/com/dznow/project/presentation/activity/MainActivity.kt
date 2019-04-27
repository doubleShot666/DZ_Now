package com.dznow.project.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dznow.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.dznow.project.presentation.fragment.*
import com.dznow.project.presentation.utils.RxBus


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val subscribe = RxBus.listen(Pair::class.java).subscribe {
            if (it.first as String == "ARTICLE_SELECTED") {
                val intentArticleActivity = Intent(applicationContext, ArticleActivity::class.java)
                intentArticleActivity.putExtra("articleId",it.second as String)
                startActivity(intentArticleActivity)
            }
        }


        //init fragments
        supportFragmentManager.beginTransaction().
            replace(R.id.theme_container,ThemeFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }

        supportFragmentManager.beginTransaction().
            replace(R.id.top_posts_container,TopArticlesFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }

        supportFragmentManager.beginTransaction().
            replace(R.id.latest_posts_container,LatestArticlesFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }

        supportFragmentManager.beginTransaction().
            replace(R.id.local_posts_container, LocalArticlesFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }

        supportFragmentManager.beginTransaction().
            replace(R.id.saved_posts_container, SavedArticlesFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }

        supportFragmentManager.beginTransaction().
            replace(R.id.themes_posts_container,ThemesPostsFragment()).apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            commit()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_daily_articles -> {

            }
            R.id.nav_local_articles -> {

            }
            R.id.nav_saved_articles -> {

            }
            R.id.nav_themes -> {
                startActivity(Intent(applicationContext,ThemeSelectionActivity::class.java))
            }
            R.id.nav_options -> {

            }
            R.id.nav_exit -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}