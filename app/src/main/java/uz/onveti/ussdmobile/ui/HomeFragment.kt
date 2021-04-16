package uz.onveti.ussdmobile.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import uz.onveti.ussdmobile.MainActivity
import uz.onveti.ussdmobile.R
import uz.onveti.ussdmobile.adapters.ImageViewPagerAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private lateinit var imageList: ArrayList<Int>
    private var page: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        val viewPager = root.view_pager
        page = 0

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            (activity as MainActivity).window.statusBarColor = Color.parseColor("#228BD6")
            (activity as MainActivity).window.navigationBarColor = Color.parseColor("#228BD6")
        }

        LoadData()
        imageViewPagerAdapter = ImageViewPagerAdapter(childFragmentManager, imageList)
        viewPager.adapter = imageViewPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                page = position
                if (page == 0) {
                    LoadData_Uzmobile()
                } else {
                    LoadData_Mobiuz()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        root.left_image.setOnClickListener {
            page = 0
            if (page == 0) {
                viewPager.currentItem = page!!
                LoadData_Uzmobile()
            }
        }
        root.right_image.setOnClickListener {
            page = 1
            if (page == 1) {
                viewPager.currentItem = page!!
                LoadData_Mobiuz()
            }
        }


        root.ussd_cod.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            Navigation.findNavController(root).navigate(R.id.ussdFragment,bundle)
        }
        root.tariff.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            Navigation.findNavController(root).navigate(R.id.tarifFragment,bundle)
        }
        root.internet_cod.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            bundle.putString("category","net")
            Navigation.findNavController(root).navigate(R.id.packageFragment,bundle)
        }
        root.minute.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            bundle.putString("category","min")
            Navigation.findNavController(root).navigate(R.id.packageFragment,bundle)
        }
        root.service.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            bundle.putString("category","ser")
            Navigation.findNavController(root).navigate(R.id.packageFragment,bundle)
        }
        root.sms.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("operator",page!!)
            bundle.putString("category","sms")
            Navigation.findNavController(root).navigate(R.id.packageFragment,bundle)
        }

        (activity as MainActivity).balance_ussd.setOnClickListener {
            if (page == 0) {
                val call_balanc = "tel:$*107" + Uri.encode("#")
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(call_balanc)))
            } else if (page == 1) {
                val call_balanc = "tel:$*100" + Uri.encode("#")
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(call_balanc)))
            }
        }

        (activity as MainActivity).traffik_ussd.setOnClickListener {
            if (page == 0) {
                val call_trafik = "tel:$*100*2" + Uri.encode("#")
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(call_trafik)))
            }else if (page == 1){
                val call_trafik = "tel:$*102" + Uri.encode("#")
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(call_trafik)))
            }
        }



        return root
    }

    private fun LoadData() {
        imageList = ArrayList()
        imageList.add(R.drawable.ic_uzmobile)
        imageList.add(R.drawable.logo)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun LoadData_Uzmobile() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            (activity as MainActivity).window.statusBarColor = Color.parseColor("#228BD6")
            (activity as MainActivity).window.navigationBarColor = Color.parseColor("#228BD6")
        }
        val linear_bottom =
            (activity as MainActivity).findViewById<LinearLayout>(R.id.linear_bottom)
        linear_bottom.background = resources.getDrawable(R.drawable.bottom_back)
        root.toolbar_cons.background = resources.getDrawable(R.drawable.toolbar_back)
        root.linear_ussd.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_internet.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_minute.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_service.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_tariff.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_service.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_sms.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_share.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_telegram.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_news.background = resources.getDrawable(R.color.uzmobile_color)
        root.linear_info.background = resources.getDrawable(R.color.uzmobile_color)
        root.left_image.setImageResource(R.drawable.ic_baseline_arrow_back_uzmobile)
        root.right_image.setImageResource(R.drawable.ic_baseline_arrow_forward_uzmobile)

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun LoadData_Mobiuz() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            (activity as MainActivity).window.statusBarColor = Color.parseColor("#FF1919")
            (activity as MainActivity).window.navigationBarColor = Color.parseColor("#FF1919")
        }
        val linear_bottom =
            (activity as MainActivity).findViewById<LinearLayout>(R.id.linear_bottom)
        linear_bottom.background = resources.getDrawable(R.drawable.bottom_back1)
        root.toolbar_cons.background = resources.getDrawable(R.drawable.toolbar_back_1)
        root.linear_ussd.background = resources.getDrawable(R.color.mobiuz)
        root.linear_internet.background = resources.getDrawable(R.color.mobiuz)
        root.linear_minute.background = resources.getDrawable(R.color.mobiuz)
        root.linear_service.background = resources.getDrawable(R.color.mobiuz)
        root.linear_tariff.background = resources.getDrawable(R.color.mobiuz)
        root.linear_service.background = resources.getDrawable(R.color.mobiuz)
        root.linear_sms.background = resources.getDrawable(R.color.mobiuz)
        root.linear_share.background = resources.getDrawable(R.color.mobiuz)
        root.linear_telegram.background = resources.getDrawable(R.color.mobiuz)
        root.linear_news.background = resources.getDrawable(R.color.mobiuz)
        root.linear_info.background = resources.getDrawable(R.color.mobiuz)
        root.left_image.setImageResource(R.drawable.ic_baseline_arrow_back_mobiuz)
        root.right_image.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}