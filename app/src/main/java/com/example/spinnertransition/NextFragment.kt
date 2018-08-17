package com.example.spinnertransition


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_next.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        spinner.adapter = (0..5).toList().run {
            ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_dropdown_item, this)
        }
        button1.setOnClickListener {
            val scene = Scene.getSceneForLayout(sceneContainer, R.layout.scene_one, requireContext())
            TransitionManager.go(scene, Fade())
        }
        button2.setOnClickListener {
            val scene = Scene.getSceneForLayout(sceneContainer, R.layout.scene_two, requireContext())
            TransitionManager.go(scene, Fade())
        }
    }
}
