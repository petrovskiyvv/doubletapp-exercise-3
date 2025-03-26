package com.example.doubletappexercise3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doubletappexercise3.adapter.HabitAdapter
import com.example.doubletappexercise3.model.Habit

class MainActivity : AppCompatActivity(R.layout.activity_main) {
//    private val habitList = mutableListOf<Habit>()
//    private lateinit var habitAdapter: HabitAdapter
//
//    private val editHabitLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val habit = result.data?.getSerializableExtra("habit") as Habit
//            val position = result.data?.getIntExtra("position", -1) ?: -1
//
//            if (position != -1) {
//                habitList[position] = habit
//            } else {
//                habitList.add(habit)
//            }
//            habitAdapter.notifyDataSetChanged()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.habit_list_fragment, HabitListFragment())
                .commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fab_fragment, FabFragment())
                .commit()
        }

//        setContentView(R.layout.activity_main)
//
//        val recyclerView: RecyclerView = findViewById(R.id.habitsList)
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        habitAdapter = HabitAdapter(habitList, this) { position ->
//            val habit = habitList[position]
//            val intent = Intent(this, EditHabitActivity::class.java).apply {
//                putExtra("habit", habit)
//                putExtra("position", position)
//            }
//            editHabitLauncher.launch(intent)
//        }
//        recyclerView.adapter = habitAdapter
//
//        val fab: View = findViewById(R.id.floatingActionButtonAddHabit)
//        fab.setOnClickListener {
//            val intent = Intent(this, EditHabitActivity::class.java)
//            editHabitLauncher.launch(intent)
        }
//    }
}