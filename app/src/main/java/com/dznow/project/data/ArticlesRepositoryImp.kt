package com.dznow.project.data

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.*
import io.reactivex.Observable

class ArticlesRepositoryImp : ArticlesRepository {

    private var themeList = arrayListOf(
    Pair(true,Theme(
    "1",
    "Technologie",
    162,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm2xUPy8LRQJ8iGLDq62hSUYizH2VsWrVGK5He_w6Xp1X3E2oscg"
    )), Pair(true,Theme(
    "2",
    "Sport",
    147,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNwwjqD2HV9SHqo6yEKABFU1qO6zahcuBBjMGVsePEoZro8upS"
    )), Pair(true,Theme(
    "3",
    "Culture",
    32,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCFCeeHqV7C5Ow-Moe2K6uwg_BwRrcYO3iCQSPW2eBxgz1iah6"
    )), Pair(true,Theme(
    "4",
    "Social",
    372,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSam2MX6gDVlsAKtFdCLrjiD8cVTojNu8z5vF5RuvDs4rx7LxRn"
    )), Pair(true,Theme(
    "5",
    "Santé",
    51,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSaLH8USYC12k8YAfb_1rfPzQM_6tvQ6qUW_VY4093IqV5YVCeA"
    )), Pair(true,Theme(
    "6",
    "Politique",
    317,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEIl331ktIlHNWvJREinuAIsbPa1tfFGg51DtMHnTPVAaGJjCF"
    )), Pair(true,Theme(
    "7",
    "Economie",
    381,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlczZ9kxtgMaCB0leKgxhl5_uB5luvt13JynY_93tSlLvMK5ho"
    )))



    private val article1 = Article(
        "1",
        "Trump lève l’exemption sur l’achat du pétrole iranien : Washington et Riyad veulent briser Téhéran",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "4 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/04/trump--e1556071431509.jpg",
        ""
    )

    private val article2 = Article(
        "2",
        "Gaid Salah ouvert à d’autres propositions, garantit la protection de la justice",
        "TSA Algérie",
        "https://yt3.ggpht.com/a-/AAuE7mCGfWGr1-ktJtlPz9nniU9Jmoisj2GiAfUUDw=s900-mo-c-c0xffffffff-rj-k-no",
        "1 heure",
        "https://www.algeriepatriotique.com/wp-content/uploads/2018/09/B-GS.jpg",
        "")

    private val article3 = Article(
        "3",
        "Cour suprême / Un dossier de plainte contre Chakib Khelil réceptionné",
        "Algérie Focus",
        "https://algerieonline.info/wp-content/uploads/2019/04/Chakib-Khelil.jpg",
        "3 heures",
        "https://www.algerie-focus.com/wp-content/uploads/2019/04/Khelil.jpg",
        ""
    )

    private val latestArticlesList = arrayListOf(article1,article2,article3,article3,article2)
    private val topArticleList = arrayListOf(article1,article2,article3)
    private val localArticleList = arrayListOf(article3,article2,article1)
    private val allArticles = arrayListOf(article2,article3,article1)
    private val savedArticles = arrayListOf<Article>()

    override fun getArticle(articleId: String): Observable<Article> {
        return Observable.create { e ->
            when(articleId){
                "1" -> e.onNext(article1)
                "2" -> e.onNext(article2)
                "3" -> e.onNext(article3)
            }
        }
    }

    override fun getArticlesList(selectionParam: List<Pair<String, String>>): Observable<List<Article>> {
        return Observable.create { e ->
            var list = ArrayList<Article>()
            for(param in selectionParam)
                when(param.first){
                    "filter" -> {
                        when(param.second){
                            "top" -> list = topArticleList
                            "latest" -> list = latestArticlesList
                            "local" -> list = localArticleList
                            "saved" -> list = savedArticles
                        }
                    }
                    "theme" ->{
                        list = allArticles
                    }
                }
            e.onNext(list)
        }
    }

    override fun saveArticle(article: Article): Observable<Boolean> {
        return Observable.create { e ->
            savedArticles.add(article)
            e.onNext(true)
        }
    }

    override fun getThemesList(): Observable<List<Pair<Boolean, Theme>>> {
        return Observable.create { e ->
            e.onNext(themeList)
        }
    }

    override fun saveSelectedThemes(themesList: List<String>): Observable<Boolean> {
        return Observable.create { e ->

            val themeList2 = ArrayList<Pair<Boolean, Theme>>()
            for(p in themeList){
                themeList2.add(p.copy(themesList.contains(p.second.id)))
            }
            themeList = themeList2
            e.onNext(true)
        }
    }

    companion object {
        val instance by lazy { ArticlesRepositoryImp() }
    }

}