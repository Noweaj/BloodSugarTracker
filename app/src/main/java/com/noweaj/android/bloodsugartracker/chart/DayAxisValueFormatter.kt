package com.noweaj.android.bloodsugartracker.chart

import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import com.noweaj.android.bloodsugartracker.util.DateUtil

class DayAxisValueFormatter(val chart: CombinedChart): ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//        return super.getAxisLabel(value, axis)

        val days = value.toInt()
        val yearInt = DateUtil.determineYear(days)
        val monthInt = DateUtil.determineMonth(days)

        val monthName = DateUtil.months[monthInt % DateUtil.months.size]
        val yearName = yearInt.toString()

        if(chart.visibleXRange > (30 * 6))
            return "$monthName $yearName"
        else {
            val dayOfMonth = DateUtil.determineDayOfMonth(days, monthInt + 12 * (yearInt - 2016))

            var appendix = "th"
            when(dayOfMonth){
                1 -> appendix = "st"
                2 -> appendix = "nd"
                3 -> appendix = "rd"
                21 -> appendix = "st"
                22 -> appendix = "nd"
                23 -> appendix = "rd"
                31 -> appendix = "st"
            }
            return if(dayOfMonth == 0)
                ""
            else
                "$dayOfMonth$appendix $monthName"
        }
    }

}