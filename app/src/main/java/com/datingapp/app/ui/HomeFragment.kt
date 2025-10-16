package com.datingapp.app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.datingapp.app.MainActivity
import com.datingapp.app.R
import com.datingapp.app.data.Match
import com.datingapp.app.databinding.FragmentHomeBinding
import com.datingapp.app.ui.adapter.BannerAdapter
import com.datingapp.app.ui.adapter.MatchAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val banners = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    private val slideHandler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    private lateinit var allMatchesAdapter: MatchAdapter
    private lateinit var newMatchesAdapter: MatchAdapter

    // Banner sliding runnable (safe with _binding check)
    private val slideRunnable = object : Runnable {
        override fun run() {
            if (!isAdded || _binding == null) return // safety check
            if (banners.isNotEmpty()) {
                currentPage = (currentPage + 1) % banners.size
                binding.bannerViewPager.setCurrentItem(currentPage, true)
                slideHandler.postDelayed(this, 3000)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBanner()
        setupMatches()
        setupScrollListener()
    }

    private fun setupBanner() {
        val bannerAdapter = BannerAdapter(banners)
        binding.bannerViewPager.adapter = bannerAdapter
        TabLayoutMediator(binding.bannerIndicator, binding.bannerViewPager) { _, _ -> }.attach()

        slideHandler.postDelayed(slideRunnable, 3000)

        binding.bannerViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    private fun setupMatches() {
        val dummyMatches = List(10) { index ->
            Match(
                name = "User $index",
                age = 25,
                height = 170 + index,
                profileImage = R.drawable.ic_profile
            )
        }

        allMatchesAdapter = MatchAdapter(dummyMatches)
        binding.rvAllMatches.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = allMatchesAdapter
        }

        newMatchesAdapter = MatchAdapter(dummyMatches.take(5))
        binding.rvNewMatches.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = newMatchesAdapter
        }

        binding.btnViewAllMatches.setOnClickListener {
            (activity as? MainActivity)?.openFragment(MatchesFragment())
        }
    }

    private fun setupScrollListener() {
        // Horizontal RecyclerViews
        listOf(binding.rvAllMatches, binding.rvNewMatches).forEach { recycler ->
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(rv, dx, dy)
                    if (dy > 0) hideBars()  // scroll up
                    else if (dy < 0) showBars() // scroll down
                }
            })
        }

        // Vertical scrolling for full page
        if (binding.root is NestedScrollView) {
            (binding.root as NestedScrollView).setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) hideBars() // scrolling down
                else if (scrollY < oldScrollY) showBars() // scrolling up
            }
        }
    }

    private fun hideBars() {
        // Animate top bar
        binding.customActionBar.animate()
            .translationY(-binding.customActionBar.height.toFloat())
            .setDuration(300)
            .start()

        // Animate bottom navigation
        (activity as? MainActivity)?.hideBottomNavigation()
    }

    private fun showBars() {
        // Animate top bar
        binding.customActionBar.animate()
            .translationY(0f)
            .setDuration(300)
            .start()

        // Animate bottom navigation
        (activity as? MainActivity)?.showBottomNavigation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        slideHandler.removeCallbacks(slideRunnable) // remove any pending slides
        _binding = null
    }
}
