package com.datingapp.app.ui.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.datingapp.app.data.HomeItem
import com.datingapp.app.data.Match
import com.datingapp.app.databinding.ItemBannerSliderBinding
import com.datingapp.app.databinding.ItemHorizontalListBinding
import com.datingapp.app.databinding.ItemLargeBannerBinding
import com.datingapp.app.databinding.ItemSectionHeaderBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeAdapter(
    private val items: List<HomeItem>,
    private val onViewAllClick: (HomeItem.SectionHeader) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_BANNER = 1
        private const val TYPE_SECTION_HEADER = 2
        private const val TYPE_MATCH_LIST = 3
        private const val TYPE_LARGE_BANNER = 4
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is HomeItem.Banner -> TYPE_BANNER
        is HomeItem.SectionHeader -> TYPE_SECTION_HEADER
        is HomeItem.MatchList -> TYPE_MATCH_LIST
        is HomeItem.LargeBanner -> TYPE_LARGE_BANNER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_BANNER -> BannerViewHolder(ItemBannerSliderBinding.inflate(inflater, parent, false))
            TYPE_SECTION_HEADER -> HeaderViewHolder(ItemSectionHeaderBinding.inflate(inflater, parent, false))
            TYPE_MATCH_LIST -> MatchListViewHolder(ItemHorizontalListBinding.inflate(inflater, parent, false))
            TYPE_LARGE_BANNER -> LargeBannerViewHolder(ItemLargeBannerBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is HomeItem.Banner -> (holder as BannerViewHolder).bind(item)
            is HomeItem.SectionHeader -> (holder as HeaderViewHolder).bind(item, onViewAllClick)
            is HomeItem.MatchList -> (holder as MatchListViewHolder).bind(item.matches)
            is HomeItem.LargeBanner -> (holder as LargeBannerViewHolder).bind(item.imageRes)
        }
    }

    override fun getItemCount(): Int = items.size

    // Banner ViewHolder
    class BannerViewHolder(private val binding: ItemBannerSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val slideHandler = Handler(Looper.getMainLooper())
        private var currentPage = 0

        fun bind(item: HomeItem.Banner) {
            val adapter = BannerAdapter(item.images)
            binding.bannerViewPager.adapter = adapter

            TabLayoutMediator(binding.bannerIndicator, binding.bannerViewPager) { _, _ -> }.attach()

            val slideRunnable = object : Runnable {
                override fun run() {
                    if (item.images.isNotEmpty()) {
                        currentPage = (currentPage + 1) % item.images.size
                        binding.bannerViewPager.setCurrentItem(currentPage, true)
                        slideHandler.postDelayed(this, 3000)
                    }
                }
            }
            slideHandler.postDelayed(slideRunnable, 3000)

            binding.bannerViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPage = position
                }
            })
        }
    }

    // Header ViewHolder
    class HeaderViewHolder(private val binding: ItemSectionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeItem.SectionHeader, onViewAllClick: (HomeItem.SectionHeader) -> Unit) {
            binding.tvSectionTitle.text = "${item.title} (${item.count})"
            binding.btnViewAll.setOnClickListener { onViewAllClick(item) }
        }
    }

    // Horizontal Matches ViewHolder
    class MatchListViewHolder(private val binding: ItemHorizontalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(matches: List<Match>) {
            binding.rvHorizontal.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvHorizontal.adapter = MatchAdapter(matches)
        }
    }

    // Large Banner ViewHolder
    class LargeBannerViewHolder(private val binding: ItemLargeBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageRes: Int) {
            binding.ivLargeBanner.setImageResource(imageRes)
        }
    }
}
