package com.noweaj.android.bloodsugartracker.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentChartBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.util.adapter.ChartListAdapter
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.viewmodel.ChartViewModel

class ChartFragment : Fragment() {
    private val TAG = ChartFragment::class.java.simpleName

    private val viewModel: ChartViewModel by viewModels { 
        InjectionUtil.provideChartViewModelFactory(
            InjectionUtil.provideChartRepository(AppDatabase.getInstance(requireContext()).chartDao()),
            InjectionUtil.provideEventRepository(AppDatabase.getInstance(requireContext()).eventDao())
        )
    }
    
    private lateinit var binding: FragmentChartBinding
    private val rvAdapter = ChartListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        
        setView()
        observe()

        return binding.root
    }
    
    private fun setView(){
        val linearLayoutManager = object: LinearLayoutManager(requireActivity().applicationContext){
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp!!.height = height/3
                return true
            }
        }
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvChart.layoutManager = linearLayoutManager
        binding.rvChart.adapter = rvAdapter
    }
    
    private fun observe(){
        // observe:
        // 1 sample chart if no chart entity exist
        binding.viewModel!!.chartEntityCount.observe(viewLifecycleOwner){
            if(it < 1)
                binding.viewModel!!.addSampleChart()
        }
        // 2 updateChart result
        binding.viewModel!!.chartEntities.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d(TAG, "chartEntities -> LOADING")
                    // progressBar active
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "chartEntities -> SUCCESS")
                    // progressBar inactive
                    // getEventEntities based on chartEntities
                    it.data?.let { eventList ->
                        binding.viewModel!!.getEventEntities(eventList)
                    }
                }
                Resource.Status.ERROR -> {
                    // progressBar inactive
                    Log.e(TAG, "chartEntities -> ERROR: ${it.message}")
                }
            }
        }
        binding.viewModel!!.chartData.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d(TAG, "eventEntities -> LOADING")
                    // progressBar active
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "eventEntities -> SUCCESS")
                    // progressBar inactive
                    // update recyclerview
                    rvAdapter.setData(it.data!!)
                }
                Resource.Status.ERROR -> {
                    // progressBar inactive
                    Log.d(TAG, "eventEntities -> ERROR: ${it.message}")
                }
            }
        }
        // 3 pinned card
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel!!.updateChart()
    }

    /*
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
     */
}