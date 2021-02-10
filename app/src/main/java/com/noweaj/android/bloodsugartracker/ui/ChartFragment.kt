package com.noweaj.android.bloodsugartracker.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.chart.DayAxisValueFormatter
import com.noweaj.android.bloodsugartracker.viewmodel.ChartViewModel

class ChartFragment : Fragment() {

    private lateinit var chartViewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chartViewModel =
                ViewModelProvider(this).get(ChartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chart, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        chartViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        setChart(root)

        return root
    }

    private fun setChart(root: View){
        val chart: CombinedChart = root.findViewById(R.id.chart1)
        chart.setBackgroundColor(Color.WHITE)
        chart.drawOrder = arrayOf(CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE)
//        chart.setPinchZoom(false)

        val tfLight = Typeface.createFromAsset(
            requireContext().assets,
            "OpenSans-Light.ttf"
        )

        val tfRegular = Typeface.createFromAsset(
            requireContext().assets,
            "OpenSans-Regular.ttf"
        )

        val data = CombinedData()
        data.setData(getBarData())
        data.setData(getLineData())
        data.setValueTypeface(tfLight)

        val limitLine_140 = LimitLine(140f, "2hr")
        limitLine_140.lineWidth = 4f
        limitLine_140.enableDashedLine(10f, 10f, 0f)
        limitLine_140.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        limitLine_140.textSize = 10f
        limitLine_140.typeface = tfRegular

        val limitLine_180 = LimitLine(180f, "1hr")
        limitLine_180.lineWidth = 4f
        limitLine_180.enableDashedLine(10f, 10f, 0f)
        limitLine_180.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        limitLine_180.textSize = 10f
        limitLine_180.typeface = tfRegular

        val yAxis = chart.axisLeft
        yAxis.addLimitLine(limitLine_140)
        yAxis.addLimitLine(limitLine_180)

        yAxis.mAxisMaximum = 200f

        val xAxis = chart.xAxis
        xAxis.granularity = 1f
        xAxis.valueFormatter = DayAxisValueFormatter(chart)

        chart.data = data
//        chart.invalidate()
//        chart.animateX(1500)
    }

    private fun getBarData():BarData{

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1f, 85f))
        entries.add(BarEntry(2f, 112f))
        entries.add(BarEntry(3f, 198f))
        entries.add(BarEntry(4f, 165f))
        entries.add(BarEntry(5f, 130f))
        entries.add(BarEntry(6f, 184f))

        val set = BarDataSet(entries, "Bar")
        set.color = Color.rgb(60, 220, 78)
        set.valueTextColor = Color.rgb(60, 220, 78)
        set.valueTextSize = 10f
        set.axisDependency = YAxis.AxisDependency.LEFT

        val data = BarData(set)
        data.barWidth = 0.45f

        return data
    }

    private fun getLineData():LineData{
        val data = LineData()

        val entries = ArrayList<Entry>()
        entries.add(Entry(0.5f, 93f))
        entries.add(Entry(1.5f, 108f))
        entries.add(Entry(2.5f, 170f))
        entries.add(Entry(3.5f, 142f))
        entries.add(Entry(4.5f, 108f))
        entries.add(Entry(5.5f, 167f))

        val set = LineDataSet(entries, "Line DataSet")
        set.color = Color.rgb(240, 238, 70)
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)

        set.axisDependency = YAxis.AxisDependency.LEFT
        data.addDataSet(set)

        return data
    }
}