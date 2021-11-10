package com.fero.skripsi.core

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.fero.skripsi.utils.ViewModelFactory
import com.google.gson.Gson

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    abstract fun setupViewBinding() : VB

    abstract fun setupViewModel()

    abstract fun setupUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setupViewBinding()
        setContentView(binding.root)
        setupViewModel()
        setupUI(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected inline fun <reified VM : ViewModel> obtainViewModel(): VM {
        return ViewModelProvider(this, ViewModelFactory.getInstance(this))[VM::class.java]
    }

    protected fun setupDetailActivity(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected fun setupChildFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun setupEventEmptyView(view: View, isEmpty: Boolean) {
        if (isEmpty) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    protected fun setupEventProgressView(view: View, progress: Boolean) {
        if (progress) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    protected fun checkExtra(extraKey: String): Boolean {
        return intent?.hasExtra(extraKey)!!
    }

    protected fun <VB : ViewBinding, Model> baseFragmentNewInstance(
        fragment: BaseFragment<VB>,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.baseNewInstance(argumentKey, extraDataResult)
    }

    protected inline fun <reified ClassActivity> baseStartActivity() {
        this.startActivity(Intent(this, ClassActivity::class.java))
    }

    protected inline fun <reified ClassActivity, Model> baseStartActivity(
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(this, ClassActivity::class.java)
        val extraData = Gson().toJson(data)
        intent.putExtra(extraKey, extraData)
        this.startActivity(intent)
    }

    protected inline fun <reified Model> baseGetExtraData(extraKey: String): Model {
        val extraIntent = intent.getStringExtra(extraKey)
        val extraData = Gson().fromJson(extraIntent, Model::class.java)
        return extraData
    }

    protected fun baseLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(this)
    }

}