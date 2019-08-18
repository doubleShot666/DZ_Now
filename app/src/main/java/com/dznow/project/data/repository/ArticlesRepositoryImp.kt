package com.dznow.project.data.repository

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Cette classe implémente l'interface [ArticlesRepository] simulant en mémoire les données utilisées dans
 * l'application. Elle permettra de communiquer avec l'environnement extérieur une fois l'application connectée
 * à une API.
 * @see ArticlesRepository
 */
class ArticlesRepositoryImp : ArticlesRepository {

    private val article7 = Article(
        "7",
        "Transition économique : quel modèle suivre ?",
        "2 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "7 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/06/B-souhil-1-e1560474511519.jpg",
        "https://www.elwatan.com/edition/actualite/transition-economique-quel-modele-suivre-14-06-2019")

    private val article8 = Article(
        "8",
        "Tomáš Sedláček. Expert tchèque en économie",
        "5 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "10 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/06/Tomas-Sedlacek-il-a-dit-e1560472769450.jpg",
        "https://www.elwatan.com/edition/actualite/tomas-sedlacek-expert-tcheque-en-economie-14-06-2019")

    private val article9 = Article(
        "9",
        "Décathlon s’installe en Algérie prochainement",
        "2 minutes",
        "Algérie Focus",
        "https://algerieonline.info/wp-content/uploads/2019/04/Chakib-Khelil.jpg",
        "3 heures",
        "https://www.algerie-focus.com/wp-content/uploads/2019/03/original-1068x712.jpg",
        "https://www.algerie-focus.com/2019/03/decathlon-sinstalle-en-algerie-prochainement/"
    )


    private val article4 = Article(
        "4",
        "Football : La révolte des supporters en… marche",
        "3 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "4 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/06/Sans-titre-1-34.gif",
        "https://www.elwatan.com/edition/sports/football-la-revolte-des-supporters-en-marche-14-06-2019")

    private val article5 = Article(
        "5",
        "Copa America Le Brésil en reconquête sans Neymar",
        "7 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "2 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/06/Sans-titre-1-35.gif",
        "https://www.elwatan.com/edition/sports/copa-america-le-bresil-en-reconquete-sans-neymar-14-06-2019")

    private val article6 = Article(
        "6",
        "Belkebla : «Je vais tout faire pour reconquérir le coeur des Algériens»",
        "5 minutes",
        "Le Buteur",
        "https://www.lebuteur.com/themes/frontend/images/newlogo.jpg",
        "5 heures",
        "http://cdn.lebuteur.com/data/images/article/thumbs/large-belkebla-je-vais-tout-faire-pour-reconquerir-le-coeur-des-algeriens-1f6ba.jpg",
        "https://www.lebuteur.com/article/detail?titre=belkebla-je-vais-tout-faire-pour-reconquerir-le-coeur-des-algeriens&id=89585")

    private val article1 = Article(
        "1",
        "Trump lève l’exemption sur l’achat du pétrole iranien : Washington et Riyad veulent briser Téhéran",
        "6 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "4 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/04/trump--e1556071431509.jpg",
        "https://www.elwatan.com/edition/international/trump-leve-lexemption-sur-lachat-du-petrole-iranien-washington-et-riyad-veulent-briser-teheran-24-04-2019"
    )

    private val article2 = Article(
        "2",
        "Gaid Salah ouvert à d’autres propositions, garantit la protection de la justice",
        "8 minutes",
        "TSA Algérie",
        "https://yt3.ggpht.com/a-/AAuE7mCGfWGr1-ktJtlPz9nniU9Jmoisj2GiAfUUDw=s900-mo-c-c0xffffffff-rj-k-no",
        "1 heure",
        "https://www.algeriepatriotique.com/wp-content/uploads/2018/09/B-GS.jpg",
        "https://fibladi.com/news/fr/gaid-salah-ouvert-a-dautres-propositions-garantit-la-protection-de-la-justice/")

    private val article3 = Article(
        "3",
        "Cour suprême / Un dossier de plainte contre Chakib Khelil réceptionné",
        "2 minutes",
        "Algérie Focus",
        "https://algerieonline.info/wp-content/uploads/2019/04/Chakib-Khelil.jpg",
        "3 heures",
        "https://www.algerie-focus.com/wp-content/uploads/2019/04/Khelil.jpg",
        "https://www.algerie-focus.com/2019/04/cour-supreme-un-dossier-de-plainte-contre-chakib-khelil-receptionne/"
    )

    private val article10 = Article(
        "10",
        "Dessine-moi un hirak…",
        "3 minutes",
        "El Watan",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
        "4 heures",
        "https://www.elwatan.com/wp-content/uploads/2019/06/Sans-titre-1-25.jpg",
        "https://www.elwatan.com/edition/culture/dessine-moi-un-hirak-14-06-2019"
    )

    private val article11 = Article(
        "11",
        "صفقة تجهيز مدرسة شبة الطبي تورط مدير الصحة السابق و06 من شركائه بتيسمسيلت",
        "4 دقائق",
        "النهار",
        "https://www.ennaharonline.com/wp-content/themes/app-base/img/ennahar-tv.png",
        "4 ساعات",
        "https://static.ennaharonline.com/wp-content/uploads/fly-images/656762/WhatsApp-Image-2019-06-14-at-09.39.01-1-1500x9999-c.jpeg",
        "https://www.ennaharonline.com/صفقة-تجهيز-مدرسة-شبة-الطبي-تورط-مدير-ال/"
    )

    private val article12 = Article(
        "12",
        "مثول المشتبه فيه الرئيسي بقتل الدركي “نصر الدين صبيحي” أمام وكيل جمهورية محكمة تبسة",
        "5 دقائق",
        "النهار",
        "https://www.ennaharonline.com/wp-content/themes/app-base/img/ennahar-tv.png",
        "4 ساعات",
        "https://static.ennaharonline.com/wp-content/uploads/fly-images/656437/Capture-23-1500x9999-c.jpg",
        "https://www.ennaharonline.com/مثول-المشتبه-فيه-الرئيسي-بقتل-الدركي-ن/"
    )

    private var themeList = arrayListOf(
    Pair(true,Theme(
    "1",
    "technologie",
    arrayListOf(article3,article2,article4,article10),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm2xUPy8LRQJ8iGLDq62hSUYizH2VsWrVGK5He_w6Xp1X3E2oscg"
    )), Pair(true,Theme(
    "2",
    "sport",
    arrayListOf(article5,article1,article10,article2,article6),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNwwjqD2HV9SHqo6yEKABFU1qO6zahcuBBjMGVsePEoZro8upS"
    )), Pair(true,Theme(
    "3",
    "culture",
    arrayListOf(article8,article7,article9),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCFCeeHqV7C5Ow-Moe2K6uwg_BwRrcYO3iCQSPW2eBxgz1iah6"
    )), Pair(true,Theme(
    "4",
    "social",
    arrayListOf(article11,article12,article2,article3),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSam2MX6gDVlsAKtFdCLrjiD8cVTojNu8z5vF5RuvDs4rx7LxRn"
    )), Pair(true,Theme(
    "5",
    "sante",
    arrayListOf(article3,article12,article1,article8),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSaLH8USYC12k8YAfb_1rfPzQM_6tvQ6qUW_VY4093IqV5YVCeA"
    )), Pair(true,Theme(
    "6",
    "politique",
    arrayListOf(article1,article2,article3,article8,article5,article7,article6),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEIl331ktIlHNWvJREinuAIsbPa1tfFGg51DtMHnTPVAaGJjCF"
    )), Pair(true,Theme(
    "7",
    "economie",
    arrayListOf(article9,article10,article2,article6),
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlczZ9kxtgMaCB0leKgxhl5_uB5luvt13JynY_93tSlLvMK5ho"
    )))


    private val latestArticlesList = arrayListOf(article1,article2,article3,article4,article5)
    private val topArticleList = arrayListOf(article1,article2,article3)
    private val localArticleList = arrayListOf(article10,article11,article12)
    private val allArticles = arrayListOf(article1,article2,article3,article4,article5,article6,article7,article8,article9,article10,article11,article12)
    private val savedArticles = arrayListOf<Article>()

    override fun getArticle(articleId: String): Observable<Article> {
        return Observable.create { e ->
            for (article in allArticles)
                if (article.id == articleId)
                    e.onNext(article)
        }
    }

    override fun getArticlesList(selectionParam: List<Pair<String, String>>): Observable<List<Article>> {
        var observable : Observable<List<Article>> = Observable.create {}
        var list = ArrayList<Article>()
        for(param in selectionParam)
            when(param.first){
                "filter" -> {
                    when(param.second){
                        "top" -> observable = Observable.create { e -> e.onNext(topArticleList)}
                        "latest" -> observable = Observable.create { e -> e.onNext(latestArticlesList)}
                        "local" -> observable = Observable.create { e -> e.onNext(localArticleList) }
                        "saved" -> observable = UserDataStoreFactory().create(true).getSavedArticles()
                    }
                }
                "theme" ->{
                    for(theme in themeList)
                        if (theme.second.name == param.second)
                            list = theme.second.articles
                    observable = Observable.create { e -> e.onNext(list)}
                }
            }

        return observable
    }

    override fun saveArticle(article: Article): Observable<Boolean> {
        return Observable.combineLatest(
            UserDataStoreFactory().create(true).saveArticle(article),
            UserDataStoreFactory().create(false).saveArticle(article),
            BiFunction { res1, res2 ->
                res1 && res2
            })
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