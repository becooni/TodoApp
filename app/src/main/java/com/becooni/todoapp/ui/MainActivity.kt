package com.becooni.todoapp.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.becooni.todoapp.R
import com.becooni.todoapp.repository.TodoRepository
import com.becooni.todoapp.persistence.AppDatabase
import com.becooni.todoapp.databinding.ActivityMainBinding
import com.becooni.todoapp.provider.ResourceProviderImpl
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>) =
                MainViewModel(
                    TodoRepository(
                        TodoLocalDataSource(
                            Room.databaseBuilder(
                                applicationContext,
                                AppDatabase::class.java,
                                "todo.db"
                            ).build().todoDao()
                        )
                    )
                ) as T

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        initEditTextAction()
        initTabLayout()

        viewModel.resourceProvider = ResourceProviderImpl(this)
    }

    private fun initEditTextAction() {
        binding.inputEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.addTodo(v.text)
                v.text = null
                true
            } else {
                false
            }
        }
    }

    private fun initTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.onTabSelected(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}