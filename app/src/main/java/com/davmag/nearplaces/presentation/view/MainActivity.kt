package com.davmag.nearplaces.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davmag.nearplaces.R
import com.davmag.nearplaces.databinding.ActivityMainBinding
import com.davmag.nearplaces.presentation.adapter.PlaceAdapter
import com.davmag.nearplaces.presentation.common.getErrorMessage
import com.davmag.nearplaces.presentation.common.initViewModel
import com.davmag.nearplaces.presentation.common.longToast
import com.davmag.nearplaces.presentation.di.presentationComponent
import com.davmag.nearplaces.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModel: MainViewModel

	private val viewBinder by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	private val placeAdapter by lazy {
		PlaceAdapter()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(viewBinder.root)
		setSupportActionBar(findViewById(R.id.toolbar))

		presentationComponent.inject(this)

		viewModel = initViewModel { viewModel }

		viewBinder.mainRecycler.adapter = placeAdapter

		viewBinder.swipper.setOnRefreshListener {
			viewModel.reload()
		}

		viewModel.error.observe(this){
			longToast(getErrorMessage(it))
		}

		viewModel.places.observe(this) {
			viewBinder.swipper.isRefreshing = false
			placeAdapter.submitData(this.lifecycle, it)
		}
	}
}