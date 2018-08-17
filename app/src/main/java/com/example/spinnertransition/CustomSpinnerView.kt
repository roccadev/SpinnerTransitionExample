package com.example.spinnertransition

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_birthday_selector.view.*
import kotlinx.android.synthetic.main.view_spinner.view.*
import kotlinx.android.synthetic.main.view_spinner_triple.view.*
import java.text.DateFormatSymbols
import java.util.*

class CustomSpinnerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val spinner = Spinner(context)
        val lp = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        lp.startToStart = LayoutParams.PARENT_ID
        lp.endToEnd = LayoutParams.PARENT_ID
        lp.topToTop = LayoutParams.PARENT_ID
        spinner.layoutParams = lp
        addView(spinner)
        spinner.adapter = createDefaultAdapter(context, (0..5).toList())
    }
}

class CustomSpinnerViewInflated @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_spinner, this, true)
        inflatedSpinner.adapter = createDefaultAdapter(context, (0..5).toList())
    }
}

class CustomTripleSpinnerViewInflated @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_spinner_triple, this, true)
        inflatedSpinner1.adapter = createAdapterWithCustomView(context, (0..5).toList())
        inflatedSpinner2.adapter = createAdapterWithCustomView(context, (5..10).toList())
        inflatedSpinner3.adapter = createAdapterWithCustomView(context, (10..15).toList())
    }
}

class BirthdaySelectorView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_birthday_selector, this, true)
        val bdDays = (1..31).toList()
        spBdDay.adapter = createDefaultAdapter(context, bdDays)
        val bdMonths = DateFormatSymbols.getInstance().months
                .filter { it.isNotBlank() }
                .map { if (it.length > 3) it.substring(0..2) else it }
        spBdMonth.adapter = createDefaultAdapter(context, bdMonths)
        val calendar = Calendar.getInstance()
        val maxYear = calendar[Calendar.YEAR] - 18
        val minYear = maxYear - 80
        val bdYears = (minYear..maxYear).toList()
        spBdYear.adapter = createDefaultAdapter(context, bdYears)
    }

    fun getBirthday(): Calendar {
        return GregorianCalendar(
                spBdYear.selectedItem as Int,
                spBdMonth.selectedItemPosition,
                spBdDay.selectedItem as Int
        )
    }

}

fun <T> createDefaultAdapter(context: Context, items: List<T>): ArrayAdapter<T> {
    return ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, items)
}

fun <T> createAdapterWithCustomView(context: Context, items: List<T>): ArrayAdapter<T> {
    return ArrayAdapter(context, R.layout.custom_spinner_dropdown_item, items)
}