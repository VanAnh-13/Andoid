package com.example.week5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week5.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEvent()
    }

    /**
     * All event in this activity:
     * 1. Click on logo Instagram
     * 2. View story
     * 3. Add news feed
     * 4. Hide story when news feed scroll
     * 5. View profile
     *
     * @see addEventClickLogo
     * @see addEventStory
     * @see addNewsFeed
     * @see hideStory
     * @author Le Van Anh
     */

    private fun addEvent() {
        addEventClickLogo()
        addEventStory()
        addNewsFeed()
        hideStory()
    }

    /**
     * Click on logo Instagram, show or hide following and favorites
     *
     * @author  Le Van Anh
     */
    private fun addEventClickLogo() {
        var clickCount = 1

        binding.instagram.setOnClickListener {
            if (clickCount % 2 == 0) {
                binding.linearFollowingFavorites.visibility = View.GONE
            } else if (clickCount % 2 != 0) {
                binding.linearFollowingFavorites.visibility = View.VISIBLE
                binding.linearInstagramMore.top = 100
            }
            clickCount++
        }
    }

    /**
     *  Show story
     *
     *  @author Le Van Anh
     */
    private fun addEventStory() {
        val storyList = mutableListOf<StoryData>(
            StoryData(
                image = "https://i.pinimg.com/564x/93/d3/a6/93d3a656469d5513f8624ebebb3f4004.jpg",
                name = "Your story"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/db/50/cf/db50cf112f82792f4d18a03b0830decb.jpg",
                name = "thieubaotram"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/b5/dd/2d/b5dd2d28c84de4c523287505d0e09879.jpg",
                name = "lebong"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/cf/1c/94/cf1c94c27f5108cada30e196150dcefa.jpg",
                name = "djmie"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/6c/29/a9/6c29a99107ff07449766f30a4db40830.jpg",
                name = "sofi_hiii"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/a5/c4/17/a5c41723255195f8b3b528e69e260e5d.jpg",
                name = "Russia"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/a8/9e/cd/a89ecdc2939a68eb430c5e6922fbe349.jpg",
                name = "Europe"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/a7/54/58/a7545839b4733760e69e276a7e774376.jpg",
                name = "China"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/cf/da/d1/cfdad1c2075abcd4e6b53b3f7d877fb9.jpg",
                name = "VietNam"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/cf/dd/d1/cfddd1f8f033b94218bfd368858fbc35.jpg",
                name = "Korea"
            ),
            StoryData(
                image = "https://i.pinimg.com/564x/3a/10/de/3a10deca82d0c6940503a388a618b718.jpg",
                name = "Japan"
            ),
        )

        val storyAdapter = StoryAdapter(storyList)
        binding.storyRecyclerView.visibility = View.VISIBLE
        binding.storyRecyclerView.adapter = storyAdapter
        binding.storyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }



    /**
     * Add news feed into app
     *
     * @author Le Van Anh
     */
    private fun addNewsFeed() {
        val newFeedsList = mutableListOf<NewFeedsData>(
            NewFeedsData(
                avt = "https://i.pinimg.com/564x/ae/12/e0/ae12e0d0ea6b383699cba82fe0344d80.jpg",
                nameProfile = "jenny.huynh",
                content = "https://i.pinimg.com/474x/b1/42/6e/b1426ed9e4f6d39ac9cfca038be79bf0.jpg",
                sumOfLike = "62,894 likes",
                comment = "huge",
                sumOfComment = "View all 117 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/f7/69/13/f76913764114998e608578f2bd0c40d5.jpg",
                nameProfile = "sithanh",
                content = "https://i.pinimg.com/474x/aa/1e/68/aa1e68585c680725a473a23bd9acecaf.jpg",
                sumOfLike = "164,688 likes",
                comment = "Viet Nam",
                sumOfComment ="View all 200 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/4a/70/9f/4a709f3accea9912cfcf965a27718817.jpg",
                nameProfile = "tuilatranthanhday",
                content = "https://i.pinimg.com/474x/9a/91/cd/9a91cd7d1d0aa1943fd7301320b12532.jpg",
                sumOfLike = "20,688 likes",
                comment = "Las Vegas!!!!",
                sumOfComment = "View all 500 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/5b/38/77/5b3877f2318469802e6b7c89d1c300f5.jpg",
                nameProfile = "djkhaled",
                content = "https://i.pinimg.com/474x/91/8a/ab/918aabd3dada6df78be0d48c6b0ddc13.jpg",
                sumOfLike = "18,688 likes",
                comment = "Blessed and Grateful, THANK YOU GOD",
                sumOfComment = "View all 1000 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/9a/91/cd/9a91cd7d1d0aa1943fd7301320b12532.jpg",
                nameProfile = "junvu95",
                content = "https://i.pinimg.com/564x/10/b6/c2/10b6c26015a5c3a8f00392ba3fcf73ba.jpg",
                sumOfLike = "22,688 likes",
                comment = "Sunshine state of mind",
                sumOfComment = "View all 700 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/ea/ed/42/eaed42c52b75071028243c8caaed1f6e.jpg",
                nameProfile = "siroandkelly",
                content = "https://i.pinimg.com/474x/d5/20/40/d5204078ba07805369957bb8ffea4e1a.jpg",
                sumOfLike = "10,000 likes",
                comment = "Viet Nam",
                sumOfComment = "View all 300 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/21/40/a8/2140a8aaa4ac4e2aa49d241bd8f5b8ec.jpg",
                nameProfile = "sofi_hiii",
                content = "https://i.pinimg.com/474x/21/cd/03/21cd0362611aa4d14d4394e5debe30d5.jpg",
                sumOfLike = "300 likes",
                comment = "No problem",
                sumOfComment = "View all 100 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/e1/61/b9/e161b90d9521193f44ed6509f3069263.jpg",
                nameProfile = "masewproducer",
                content = "https://i.pinimg.com/474x/5b/a3/39/5ba3399d89a8e83297085f7e01c5b5bb.jpg",
                sumOfLike = "448 likes",
                comment = "",
                sumOfComment = ""
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/36/a1/68/36a168e80d801b078ea49b5158afa9c0.jpg",
                nameProfile = "binzpoet",
                content = "https://i.pinimg.com/474x/a9/b6/37/a9b637b07cbef141552b0e58184d9bab.jpg",
                sumOfLike = "24,365 likes",
                comment = "🎶😎",
                sumOfComment = "View all 33 comments"
            ),
            NewFeedsData(
                avt = "https://i.pinimg.com/474x/2f/6b/df/2f6bdf3292c2241582cdcf399a84184d.jpg",
                nameProfile = "den.vau",
                content = "https://i.pinimg.com/474x/7f/b9/b3/7fb9b3f243880b6ca23fd72ae7197bf6.jpg",
                sumOfLike = "30,688 likes",
                comment = "Kỉ niệm Cống Hiến 2024",
                sumOfComment = "View all 300 comments"
            ),
        )

        val newFeedsDataAdapter = NewFeedsDataAdapter(newFeedsList)
        binding.newFeeds.adapter = newFeedsDataAdapter
        binding.newFeeds.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


    }

    /**
     * Hide story when news feed scroll
     *
     * @author Le Van Anh
     */
    private fun hideStory() {
        binding.newFeeds.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val view = recyclerView.getChildAt(0)
                val firstItemPosition = recyclerView.getChildAdapterPosition(view)

                if (firstItemPosition == 0) {
                    binding.storyRecyclerView.visibility = View.VISIBLE
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    binding.storyRecyclerView.visibility = View.GONE
                }
            }
        })
    }
}