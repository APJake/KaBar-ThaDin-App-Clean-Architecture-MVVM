package com.apjake.kabarthadin.presentation.mvvm.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apjake.kabarthadin.common.util.AppConstants.DEFAULT_ARTICLE_QUERY
import com.apjake.kabarthadin.common.util.AppConstants.DELAY_TIME_FOR_NEXT_SEARCH
import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.domain.usecase.LoadMoreArticlesUseCase
import com.apjake.kabarthadin.domain.usecase.SearchArticlesUseCase
import com.apjake.kabarthadin.presentation.mvvm.event.ArticleEvent
import com.apjake.kabarthadin.presentation.mvvm.state.ArticleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val searchArticlesUseCase: SearchArticlesUseCase,
    private val loadMoreArticlesUseCase: LoadMoreArticlesUseCase
): ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
        .apply {
            value = emptyList()
        }

    private val _state = mutableStateOf(ArticleListState())
    val state: State<ArticleListState> = _state

    private val _articlesEvent = MutableSharedFlow<ArticleEvent>()
    val articlesEvent = _articlesEvent.asSharedFlow()

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    init {
        search(DEFAULT_ARTICLE_QUERY)
    }

    private var searchJob: Job? = null

    fun reload(){
        search(_searchQuery.value)
    }

    fun loadMore(){
        val query = _searchQuery.value
        val page = _state.value.page + 1
        val currentArticlesSize = _state.value.articles.size
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(DELAY_TIME_FOR_NEXT_SEARCH)
            loadMoreArticlesUseCase(query, page)
                .onEach { result ->
                    when(result){
                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                articles = result.data.orEmpty(),
                                isLoading = false,
                                isLoadingMore = true,
                            )
                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                articles = result.data.orEmpty(),
                                isLoading = false,
                                isLoadingMore = false
                            )
                            _articlesEvent.emit(ArticleEvent.ShowSnackBar(
                                result.error?: "Unknown error"
                            ))
                        }
                        is Resource.Success->{
                            if(result.data.orEmpty().size == currentArticlesSize){
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    isLoadingMore = false,
                                    page = page,
                                    endReached = true
                                )
                            }else{
                                _state.value = state.value.copy(
                                    articles = result.data.orEmpty(),
                                    isLoading = false,
                                    isLoadingMore = false,
                                    page = page,
                                    endReached = false
                                )
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    fun search(query: String){
        _state.value = state.value.copy(
            isLoading = true,
            isLoadingMore = false,
            page = 1,
            endReached = false
        )
        _searchQuery.value = query
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(DELAY_TIME_FOR_NEXT_SEARCH)

            searchArticlesUseCase(query)
                .onEach { result ->
                    when(result){
                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                articles = result.data.orEmpty(),
                                isLoading = true
                            )
                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                articles = result.data.orEmpty(),
                                isLoading = false
                            )
                            _articlesEvent.emit(ArticleEvent.ShowSnackBar(
                                result.error?: "Unknown error"
                            ))
                        }
                        is Resource.Success->{
                            _state.value = state.value.copy(
                                articles = result.data.orEmpty(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

}