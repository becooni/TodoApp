package com.becooni.todoapp.ui

import androidx.lifecycle.*
import com.becooni.todoapp.R
import com.becooni.todoapp.model.Todo
import com.becooni.todoapp.provider.ResourceProvider
import com.becooni.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _currentList = MediatorLiveData<List<Todo>>()
    val currentList: LiveData<List<Todo>> = _currentList

    private val _originList = MutableLiveData<List<Todo>>()
    val originList: LiveData<List<Todo>> = _originList

    private val mutableListForOrigin: MutableList<Todo>
        get() = originList.value?.toMutableList() ?: mutableListOf()

    val hasCompleted: LiveData<Boolean> = Transformations.map(originList) { list ->
        list.any { it.completed }
    }

    val leftText: LiveData<String> = Transformations.map(originList) { list ->
        val activeCount = list.count { !it.completed }

        resourceProvider.getQuantityString(
            R.plurals.numberOfItemsLeft,
            activeCount
        )
    }

    val toggleAll = MutableLiveData<Boolean>()

    private val currentTabType = MutableLiveData(TabType.ALL)

    init {
        todoRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _originList.value = it
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)

        _currentList.addSource(originList) {
            _currentList.value = combineLatest(originList, currentTabType)
        }

        _currentList.addSource(currentTabType) {
            _currentList.value = combineLatest(originList, currentTabType)
        }

        toggleAll.observeForever { isChecked ->
            val updateItems = _originList.value?.map {
                it.copy(completed = isChecked)
            } ?: return@observeForever

            todoRepository.update(*updateItems.toTypedArray())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _originList.value = updateItems
                    },
                    Throwable::printStackTrace
                )
                .addTo(disposables)
        }
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }

    private fun combineLatest(
        originalLiveData: LiveData<List<Todo>>,
        tabTypeLiveData: LiveData<TabType>
    ): List<Todo>? {

        val original = originalLiveData.value
        val tabType = tabTypeLiveData.value

        if (original == null || tabType == null) {
            return null
        }

        return when (tabType) {
            TabType.ALL -> original
            TabType.ACTIVE -> original.filter { it.completed.not() }
            TabType.COMPLETED -> original.filter { it.completed }
        }
    }

    internal fun addTodo(text: CharSequence) {
        if (text.isEmpty()) return

        val newItem = Todo(text = text.toString())

        todoRepository.insert(newItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableListForOrigin.add(newItem)
                    _originList.value = mutableListForOrigin
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)
    }

    internal fun onTodoRemove(item: Todo) {
        todoRepository.delete(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableListForOrigin.remove(item)
                    _originList.value = mutableListForOrigin
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)
    }

    internal fun onTabSelected(position: Int) {
        currentTabType.value = TabType.values()[position]
    }

    internal fun onEditTodo(item: Todo, text: CharSequence) {
        if (text.isEmpty()) return

        val updateItem = item.copy(text = text.toString())

        todoRepository.update(updateItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val position = mutableListForOrigin.indexOf(item)
                    mutableListForOrigin[position] = updateItem
                    _originList.value = mutableListForOrigin
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)
    }

    internal fun onCheckClick(item: Todo) {
        val updateItem = item.copy(completed = item.completed.not())

        todoRepository.update(updateItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val position = mutableListForOrigin.indexOf(item)
                    mutableListForOrigin[position] = updateItem
                    _originList.value = mutableListForOrigin
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)
    }

    internal fun onClearCompletedClick() {
        val items = _originList.value?.filter { it.completed } ?: return

        todoRepository.delete(*items.toTypedArray())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableListForOrigin.removeAll { it.completed }
                    _originList.value = mutableListForOrigin
                },
                Throwable::printStackTrace
            )
            .addTo(disposables)
    }
}