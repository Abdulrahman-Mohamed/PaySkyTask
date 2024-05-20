package com.example.payskytask.appFeature.presentation.postsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payskytask.R
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.domain.UTILS.OnItemClickListener
import com.example.payskytask.appFeature.domain.adapters.PostsAdapter
import com.example.payskytask.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

import com.example.payskytask.appFeature.domain.UTILS.Result

@AndroidEntryPoint
class ListFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostsViewModel by viewModels()
    private lateinit var adapter: PostsAdapter
    private lateinit var postsList: MutableList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostsAdapter(this)
        binding.recyclerPosts.adapter = adapter
        binding.recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
        swipeData(binding.recyclerPosts)

        viewModel.Posts.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                postsList = it.toMutableList()
                adapter.submitList(postsList)
            }
        })
        binding.bafAdd.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(id = -1)
            findNavController().navigate(action)
        }
    }

    fun swipeData(recycler: RecyclerView) {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteItem(postsList[position])
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }

    override fun onClick(data: DataModelItem) {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(id = data.id)
        findNavController().navigate(action)
        //  Toast.makeText(requireContext(),data.title,Toast.LENGTH_SHORT).show()
    }
}