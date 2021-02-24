package com.noweaj.android.bloodsugartracker.util.chart

class ChartDateUtil {

    companion object{
        val months = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )

        fun determineYear(days: Int): Int {
            return if(days <= 366)
                2016
            else if(days <= 730)
                2017
            else if(days <= 1094)
                2018
            else if(days <= 1458)
                2019
            else
                2020
        }

        fun determineMonth(dayOfYear: Int): Int {
            var month = -1
            var days = 0

            while(days < dayOfYear){
                month += 1
                if(month >= 12)
                    month = 0
                val year =
                    determineYear(
                        days
                    )
                days += getDaysForMonth(
                    month,
                    year
                );
            }
            return Math.max(month, 0)
        }

        fun determineDayOfMonth(days: Int, month: Int): Int {
            var count = 0
            var daysForMonth = 0

            while(count < month){
                val year =
                    determineYear(
                        daysForMonth
                    )
                daysForMonth += getDaysForMonth(
                    count % 12,
                    year
                )
                count++
            }
            return days - daysForMonth
        }

        private fun getDaysForMonth(month: Int, year: Int): Int {
            if(month == 1){
                var is29Feb = false
                if(year < 1582) {
                    var localYear = year
                    if(year < 1)
                        localYear++
                    is29Feb = localYear % 4 == 0
                }else if (year > 1582)
                    is29Feb = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

                return if(is29Feb)
                    29
                else
                    28
            }

            return if(month == 3 || month == 5 || month == 8 || month == 10)
                30
            else
                31
        }
    }
}