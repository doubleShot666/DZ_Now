package com.dznow.project.presentation.presenter

import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ThemeSelectionView
import com.dznow.project.presentation.model.Theme

class ThemeSelectionPresenter(themeSelectionView: ThemeSelectionView) : BasePresenter<ThemeSelectionView>(themeSelectionView) {

    fun getSelectedThemes() {

        val themList = ArrayList<Pair<Boolean, Theme>>()
        themList.add(Pair(true,
            Theme(
                "1",
                "Technologie",
                162,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm2xUPy8LRQJ8iGLDq62hSUYizH2VsWrVGK5He_w6Xp1X3E2oscg"
            )
        ))
        themList.add(Pair(true,
            Theme(
                "2",
                "Sport",
                147,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNwwjqD2HV9SHqo6yEKABFU1qO6zahcuBBjMGVsePEoZro8upS"
            )
        ))
        themList.add(Pair(true,
            Theme(
                "3",
                "Culture",
                32,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCFCeeHqV7C5Ow-Moe2K6uwg_BwRrcYO3iCQSPW2eBxgz1iah6"
            )
        ))
        themList.add(Pair(true,
            Theme(
                "4",
                "Social",
                372,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSam2MX6gDVlsAKtFdCLrjiD8cVTojNu8z5vF5RuvDs4rx7LxRn"
            )
        ))
        themList.add(Pair(true,
            Theme(
                "5",
                "Santé",
                51,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSaLH8USYC12k8YAfb_1rfPzQM_6tvQ6qUW_VY4093IqV5YVCeA"
            )
        ))
        themList.add(Pair(false,
            Theme(
                "6",
                "Politique",
                317,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEIl331ktIlHNWvJREinuAIsbPa1tfFGg51DtMHnTPVAaGJjCF"
            )
        ))
        themList.add(Pair(true,
            Theme(
                "7",
                "Economie",
                381,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlczZ9kxtgMaCB0leKgxhl5_uB5luvt13JynY_93tSlLvMK5ho"
            )
        ))
        view.initThemes(themList)
    }

    fun setSelectedThemes(selectedThemes: ArrayList<String>) {
        view.finishView()
    }
}