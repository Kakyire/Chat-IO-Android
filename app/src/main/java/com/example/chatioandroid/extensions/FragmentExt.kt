package com.example.chatioandroid.extensions

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


fun Fragment.navigateToNextPage(direction: NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.createMenu(@MenuRes menuRes: Int, onItemSelected: (Int) -> Unit) {
    val menuHost: MenuHost = requireActivity()

    menuHost.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuRes, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            onItemSelected(menuItem.itemId)
            return true
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}



