package com.example.api.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.api.R

class LibraryFragment : Fragment(){

    companion object{
        const val TAG: String = "로그"

        fun newInstance():LibraryFragment{
            return LibraryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d(TAG,"LibraryFragment - onCreate() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Log.d(TAG,"LibraryFragment - onAttach() called")
    }

    // 화면 보여주기위해 xml과 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d(TAG,"LibraryFragment - onCreateView() called")
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_library,container,false)
        return view
    }
}