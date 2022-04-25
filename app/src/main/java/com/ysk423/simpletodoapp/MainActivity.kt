package com.ysk423.simpletodoapp

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewを取得
        val btnAdd:Button =findViewById(R.id.btnAdd)
        val lv:ListView =findViewById(R.id.lv)


        //アダプターに入れてListViewにセット
        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mutableListOf()//最初は空っぽ、allayListOf()でも動く
        )
        lv.adapter = adapter


        //ボタンでアラートダイアログ
        btnAdd.setOnClickListener {
            //EditTextをnewする（中身を入れる）
            val et = EditText(this)

            AlertDialog.Builder(this)
                    .setTitle("項目の追加")
                    .setMessage("何をする？")
                    .setView(et)
                    .setPositiveButton("追加",DialogInterface.OnClickListener { dialogInterface, i ->
                        //add()でアダプターに追加
                        val myTodo = et.text.toString()
                        adapter.add(myTodo)
                    })
                    .setNegativeButton("キャンセル", null)
                    .show()
        }

        //listviewをタッチしたらアラートダイアログ
        lv.setOnItemClickListener { adapterView, view, i, l ->
            AlertDialog.Builder(this)
                    .setTitle("削除しますか？")
                    //Yesおしたら、リムーブ
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                        adapter.remove(adapter.getItem(i))
                        adapter.notifyDataSetChanged()//画面を更新する呪文
                    })
                    .setNegativeButton("No", null)
                    .show()
        }
    }
}