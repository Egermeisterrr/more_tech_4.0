package com.example.moretech40android.presentation.fragments.insides

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.NewsModel
import com.example.moretech40android.R
import com.example.moretech40android.databinding.FragmentInsightsBinding
import com.example.moretech40android.presentation.fragments.main.trends.TrendsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class InsightsFragment : Fragment() {

    private val viewModel: InsightsViewModel by viewModels()
    private lateinit var binding: FragmentInsightsBinding
    private lateinit var insightsAdapter: InsightsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInsightsBinding.inflate(inflater, container, false)

        viewModel.selectedNews.observe(viewLifecycleOwner) {
            val bundle = Bundle()
            sendBundle(bundle)
            findNavController().navigate(R.id.selectedNewsFragment, bundle)
        }

        insightsAdapter = InsightsAdapter(requireContext(), viewModel)
        insightsAdapter.submitList(
            mutableListOf(
                NewsModel(
                    "title title title title title title title title title ",
                    "Sdas",
                    mutableListOf("1231", "12314", "123124"),
                    "asdaasdasdas",
                    "Решение о переносе начала осеннего призыва на военную службу с 1 октября на 1 ноября связано с загрузкой военкоматов, рассказал «РИА Новости» пресс-секретарь президента Дмитрий Песков. Ранее президент Владимир Путин подписал указ об осеннем призыве.\n" +
                            "\n" +
                            "«Сейчас военкоматы сильно перегружены в связи с частичной мобилизацией. Чтобы не усугублять эту перегрузку, было принято такое решение. Оно позволит развести потоки мобилизованных и призывников срочников», — сообщил Песков.\n" +
                            "\n" +
                            "Указ об осеннем призыве граждан России на военную службу был опубликован в пятницу.\n" +
                            "Заместитель начальника Главного..."
                ),
                NewsModel(
                    "title1 title1 title1 title1 title1 title1 title1 title1 title1 ",
                    "Sdas",
                    mutableListOf("asad", "sadasda", "asdaas"),
                    "1231412111",
                    "Решение о переносе начала осеннего призыва на военную службу с 1 октября на 1 ноября связано с загрузкой военкоматов, рассказал «РИА Новости» пресс-секретарь президента Дмитрий Песков. Ранее президент Владимир Путин подписал указ об осеннем призыве.\n" +
                            "\n" +
                            "«Сейчас военкоматы сильно перегружены в связи с частичной мобилизацией. Чтобы не усугублять эту перегрузку, было принято такое решение. Оно позволит развести потоки мобилизованных и призывников срочников», — сообщил Песков.\n" +
                            "\n" +
                            "Указ об осеннем призыве граждан России на военную службу был опубликован в пятницу.\n" +
                            "Заместитель начальника Главного..."
                )
            )
        )
        binding.insightsRecyclerView.adapter = insightsAdapter
        binding.insightsRecyclerView.addItemDecoration(
            TrendsItemDecoration(
                dpToPixel(
                    requireActivity().resources.getDimension(
                        R.dimen.margin_recycler_view_vertical
                    )
                ),
                dpToPixel(
                    requireActivity().resources.getDimension(
                        R.dimen.margin_recycler_view_no_margin
                    )
                )
            )
        )

        return binding.root
    }

    private fun sendBundle(bundle: Bundle) {
        bundle.putString(NEWS_TITLE, viewModel.selectedNews.value!!.title)
        bundle.putString(NEWS_IMAGE, viewModel.selectedNews.value!!.image)
        bundle.putStringArrayList(NEWS_TAGS, viewModel.selectedNews.value!!.tags as ArrayList<String>)
        bundle.putString(NEWS_TIME, viewModel.selectedNews.value!!.time)
        bundle.putString(NEWS_CONTENT, viewModel.selectedNews.value!!.content)
        bundle.putString(CURRENT_FRAGMENT, INSIGHTS_FRAGMENT)
    }

    private fun dpToPixel(dp: Float): Float {
        return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    companion object {
        const val NEWS_TITLE = "NEWS_TITLE"
        const val NEWS_IMAGE = "NEWS_IMAGE"
        const val NEWS_TAGS = "NEWS_TAGS"
        const val NEWS_TIME = "NEWS_TIME"
        const val NEWS_CONTENT = "NEWS_CONTENT"
        const val CURRENT_FRAGMENT = "CURRENT_FRAGMENT"
        const val INSIGHTS_FRAGMENT = "INSIGHTS_FRAGMENT"
    }
}