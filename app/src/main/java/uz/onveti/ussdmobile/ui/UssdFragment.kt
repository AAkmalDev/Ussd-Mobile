package uz.onveti.ussdmobile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import uz.onveti.ussdmobile.R
import uz.onveti.ussdmobile.models.Ussd
import uz.onveti.ussdmobile.utils.MySharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class UssdFragment : Fragment() {
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
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_ussd, container, false)
        MySharedPreference.getInstance(root.context)
        val operator = arguments?.getInt("operator")
        val path = "${MySharedPreference.getLanguage()}/$operator/ussd/"
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(path)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val value = child.getValue(Ussd::class.java)
                        Log.d("Bootcamp",value.toString())
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("ErrorBootcamp",p0.message)
            }
        })



        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UssdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}