package com.example.gamerepeat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamerepeat.databinding.FragmentGameBinding
import kotlinx.coroutines.*
import java.util.concurrent.Delayed


class GameFragment : Fragment() {
   lateinit var binding : FragmentGameBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val list = listOf("+--+", "-++--", "+++--+", "--+-+-+", "++---+--", "++-+--++-",
        "-+-++---+-", "---+--++--+", "+--++-++----", "--+++-+-+---+")
    var index = 0
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btCheck.isEnabled = false
        binding.tvLevelCount.text = "0"
        binding.btStart.setOnClickListener {
            start()
            binding.etEnterResult.text.clear()
        }
        binding.btCheck.setOnClickListener {
            check()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun start(){
        binding.tvFailed.visibility = View.GONE
        binding.etEnterResult.isEnabled = false
        binding.btStart.text = "Start"
        binding.tvShow.visibility = View.VISIBLE
        coroutineScope.launch {
            if(index < 10){
                binding.tvShow.text = list[index]
                delay(2000)
                binding.tvShow.visibility = View.INVISIBLE
                binding.etEnterResult.isEnabled = true
                binding.btCheck.isEnabled = true
            }else{
                binding.tvShow.visibility = View.INVISIBLE
                binding.tvGameOver.visibility = View.VISIBLE
                binding.tvFailed.visibility = View.GONE
                binding.tvCongrats.visibility = View.GONE
                binding.btStart.isEnabled = true
                binding.btStart.text = "Start again ?"
                index = 0
                count = 0
                binding.tvLevelCount.text = "0"
                binding.tvGameOver.visibility = View.GONE
            }
        }
        binding.btStart.isEnabled = false
    }
    private fun check(){
        binding.tvFailed.visibility = View.GONE
        binding.tvCongrats.visibility = View.GONE
        if(binding.etEnterResult.text.toString().equals(list[index], true)){
            binding.tvCongrats.visibility = View.GONE
            binding.etEnterResult.text.clear()
            binding.tvCongrats.visibility = View.VISIBLE
            count++
            binding.tvLevelCount.text = count.toString()
            index++
            binding.btStart.isEnabled = true
            binding.btCheck.isEnabled = false
        } else {
            binding.tvFailed.visibility = View.GONE
            binding.tvLevelCount.text = "0"
            binding.tvFailed.visibility = View.VISIBLE
            binding.etEnterResult.text.clear()
            index = 0
            count = 0
            binding.btStart.isEnabled = true
            binding.btCheck.isEnabled = false
        }
    }
}