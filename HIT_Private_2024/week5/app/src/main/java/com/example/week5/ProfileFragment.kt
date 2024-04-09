package com.example.week5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week5.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

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

    private fun addEvent() {
        makeRoundAvatar()
        addEventStory()
        addYourPost()
    }

    private fun addYourPost() {
        val postList = mutableListOf<YourPost>(
            YourPost(image = "https://i.pinimg.com/474x/92/d4/83/92d483a6c0a231d7453064208653b16e.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/94/5b/00/945b004acce765037c9f457701c68890.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/9f/b9/f9/9fb9f934e2972ec8e28ea81da5656782.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/4b/f9/85/4bf985f8cc8bd235330c94d2bde9a224.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/c9/45/93/c9459395879ee04238765c626c833d79.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/62/b3/27/62b32707a3a475ba05738516480c0e1f.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/4c/b5/3c/4cb53c6fc7f336048f99af0e74957809.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/5a/cf/b3/5acfb3ad77d989c39977ce467984ad48.jpg"),
            YourPost(image = "https://i.pinimg.com/474x/21/e9/7b/21e97b69b42b96d271a8edee66dd3197.jpg"),
        )

        binding.yourPosts.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.yourPosts.adapter = YourPostAdapter(postList)
    }

    private fun makeRoundAvatar() {
        Glide.with(requireContext())
            .load("https://i.pinimg.com/564x/a1/33/ae/a133aeeaa312ab13f9e3f77d50b4f162.jpg")
            .circleCrop()
            .into(binding.imgProfile)
    }

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

}