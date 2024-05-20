package com.example.payskytask.appFeature.presentation.updatePost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.payskytask.R
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.presentation.postsList.PostsViewModel
import com.example.payskytask.databinding.FragmentListBinding
import com.example.payskytask.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpdatePostsViewModel by viewModels()
    var model: DataModelItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: UpdateFragmentArgs by navArgs()
        val receivedInt = args.id
        if (receivedInt != -1) {
            viewModel.getPost(receivedInt)
            viewModel.data.observe(viewLifecycleOwner, Observer {
                binding.title.setText(it.title)
                binding.desc.setText(it.body)
                model = DataModelItem(it.body, it.id, it.title, it.userId)
            })
        }

        binding.btnDoAction.setOnClickListener {
            if (binding.title.text.toString().trim().isNotEmpty() && binding.desc.text.toString()
                    .trim().isNotEmpty()
            ) {
                if (model != null) {
                    viewModel.updatePost(
                        model!!.copy(
                            body = binding.desc.text.toString(),
                            title = binding.title.text.toString()
                        )
                    )
                    Toast.makeText(requireContext(), "Update Done", Toast.LENGTH_SHORT).show()

                } else {
                    model = DataModelItem(
                        body = binding.desc.text.toString(),
                        title = binding.title.text.toString(),
                        userId = 1
                    )
                    viewModel.insertPost(model!!)
                    Toast.makeText(requireContext(), "Insertion Done", Toast.LENGTH_SHORT).show()

                }
                findNavController().popBackStack()

            }
        }
    }
}