package com.sxwzxc.androidglass

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sxwzxc.androidglass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val contacts = listOf(
        Contact("陈晓明", "UI 设计师", "在线"),
        Contact("张伟", "Android 开发", "在线"),
        Contact("李芳", "产品负责人", "离开"),
        Contact("王强", "后端开发", "在线"),
        Contact("赵丽", "测试工程师", "离线"),
        Contact("刘洋", "运维工程师", "在线"),
        Contact("孙雪", "数据分析师", "离开"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSpinner()
        setupButtons()
    }

    private fun setupRecyclerView() {
        binding.recyclerContacts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContactAdapter(contacts)
        }
    }

    private fun setupSpinner() {
        val options = resources.getStringArray(R.array.dropdown_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTheme.adapter = adapter
    }

    private fun setupButtons() {
        binding.btnPrimary.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_primary), Toast.LENGTH_SHORT).show()
        }
        binding.btnSecondary.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_secondary), Toast.LENGTH_SHORT).show()
        }
        binding.btnOutlined.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_outlined), Toast.LENGTH_SHORT).show()
        }
        binding.btnShowDialog.setOnClickListener { showAlertDialog() }
        binding.btnShowBottomSheet.setOnClickListener { showBottomSheet() }
    }

    private fun showAlertDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_liquid_glass, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.let { GlassBlurHelper.applyWindowBlur(it) }
        dialog.show()

        dialogView.findViewById<TextView>(R.id.dialogTitle).text = getString(R.string.dialog_title)
        dialogView.findViewById<TextView>(R.id.dialogMessage).text = getString(R.string.dialog_message)
        dialogView.findViewById<Button>(R.id.btnDialogConfirm).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_confirmed), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.btnDialogDismiss).setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showBottomSheet() {
        val bottomSheet = BottomSheetDialog(this)
        val sheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_glass, null)
        bottomSheet.setContentView(sheetView)
        bottomSheet.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheet.window?.let { GlassBlurHelper.applyWindowBlur(it) }

        sheetView.findViewById<View>(R.id.optionShare).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_share), Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
        sheetView.findViewById<View>(R.id.optionCopy).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_copy), Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
        sheetView.findViewById<View>(R.id.optionDownload).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_download), Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
        sheetView.findViewById<View>(R.id.optionDelete).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_delete), Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
        sheetView.findViewById<View>(R.id.btnClose).setOnClickListener {
            bottomSheet.dismiss()
        }

        bottomSheet.show()
    }
}
