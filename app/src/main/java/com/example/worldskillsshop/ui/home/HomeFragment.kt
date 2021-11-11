package com.example.worldskillsshop.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldskillsshop.MainActivity
import com.example.worldskillsshop.R
import com.example.worldskillsshop.adapter_RV.adapter_cards
import com.example.worldskillsshop.data_history_RV.bank_cards
import com.example.worldskillsshop.databinding.EntranceDesignDialogBinding
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.databinding.RegistrationDialogBinding
import com.example.worldskillsshop.db.AdsDBManager
import com.example.worldskillsshop.db.UserDBManager


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    val PICK_FROM_GALLERY = 1

    var thiscontext: Context? = null

    val imageReguestCode = 10

    var title = ""
    var price = 0

    var rv = 0
    var image = ""

    var search:MenuItem? = null

    var Id = ArrayList<String>()
    var Phone = ArrayList<String>()
    var PriceADS = ArrayList<String>()
    var Description = ArrayList<String>()
    var COLUMN_titleADS = ArrayList<String>()
    var AddImage = ArrayList<String>()
    var AddImage_1 = ArrayList<String>()
    var AddImage_2 = ArrayList<String>()
    var AddImage_3 = ArrayList<String>()
    var Time = ArrayList<String>()
    var Viewing = ArrayList<String>()

    var BOL = ArrayList<String>()
    var IdUser = ArrayList<String>()
    var PASSWORD = ArrayList<String>()
    var EMAIL = ArrayList<String>()
    var NAME = ArrayList<String>()

    private val binding get() = _binding!!

    var adapter:adapter_cards? = null

    var myDbManager:AdsDBManager? = null
    var DbManagerUser:UserDBManager? = null

    var prefs:SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Главная"
        (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))
        setHasOptionsMenu(true)

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        prefs = this.requireActivity()
            .getSharedPreferences("Table", Context.MODE_PRIVATE)

        thiscontext = container?.context
        adapter = adapter_cards(thiscontext!!)

        myDbManager = AdsDBManager(thiscontext!!)
        DbManagerUser = UserDBManager(thiscontext!!)

        search()


        binding.floatingActionButton.setOnClickListener {
            com.example.worldskillsshop.Intent().open(thiscontext!!)
        }
        return (root)
    }

    @SuppressLint("ApplySharedPref", "CommitPrefEdits", "UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("adada",prefs?.getInt("Table",0).toString())

        if (prefs?.getInt("Table",0) == 0) {
            Log.d("adada",prefs?.getInt("Table",0).toString())
            DB()
            db_Entrance()
        }else if (prefs?.getInt("Table",0) == 1){
            Log.d("adada",prefs?.getInt("Table",0).toString())
            DB()
        }
    }

    fun db_Entrance(){
        DbManagerUser?.openDb()
        BOL = DbManagerUser!!.readDbDate("BOL")
        IdUser = DbManagerUser!!.readDbDate("Id")
        NAME = DbManagerUser!!.readDbDate("NAME")
        EMAIL = DbManagerUser!!.readDbDate("EMAIL")
        PASSWORD = DbManagerUser!!.readDbDate("PASSWORD")

        when(IdUser.size){
            0 -> DbManagerUser?.insertToDb("0","0","0")
        }
        val dialogBuilder = AlertDialog.Builder(this.context)
        val bindingChange = EntranceDesignDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(bindingChange.root)

        val dialog: AlertDialog = dialogBuilder.show()

        val Btn_entrance = dialog.findViewById<Button>(R.id.entrance)
        val Btn_registration = dialog.findViewById<Button>(R.id.registration_but)
        val login = dialog.findViewById<EditText>(R.id.login)
        val password = dialog.findViewById<EditText>(R.id.password)

        Btn_registration.setOnClickListener {
            DB_USER_REGISTRATION()
            dialog.dismiss()
        }
        Btn_entrance.setOnClickListener {
            var UserId_W = IdUser.size-1
            while (UserId_W >= 0 ){

                if (NAME[UserId_W] == login.text.toString()){
                    if (PASSWORD[UserId_W] == password.text.toString()){
                        prefs_fun(1)
                        dialog.dismiss()
                    }
                }
                UserId_W--
            }
        }
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(R.drawable.krujok)
    }

    @SuppressLint("SetTextI18n")
    fun DB_USER_REGISTRATION(){

        val dialogBuilder = AlertDialog.Builder(this.context)
        val bindingChange = RegistrationDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(bindingChange.root)

        val dialog: AlertDialog = dialogBuilder.show()

        val Btn_entrance = dialog.findViewById<Button>(R.id.registration_but_1)
        val login = dialog.findViewById<EditText>(R.id.name)
        val password = dialog.findViewById<EditText>(R.id.password)
        val password_1 = dialog.findViewById<EditText>(R.id.password_1)
        val email = dialog.findViewById<EditText>(R.id.email)
        val text = dialog.findViewById<TextView>(R.id.textView6)
        val cancellation = dialog.findViewById<Button>(R.id.cancellation)

        Btn_entrance.setOnClickListener {
            if(
                login.text.toString().isEmpty()
                && password.text.toString().isEmpty()
                && password_1.text.toString().isEmpty()
                && email.text.toString().isEmpty()
            ){
                text.text = "Зваполните все поля"
            }else {
                if (login.text.toString().count() < 6) {
                    text.text = "Логин должен состоять из 6 или больше символов"
                } else if (password.text.toString().count() < 8) {
                    text.text = "Пароль слишком короткий"
                } else if (password.text.toString() != password_1.text.toString()) {
                    text.text = "Пароль не совпадает"
                } else if (email.text.toString().count() < 6) {
                    text.text = "Email слишком короткий"
                } else if (!email.text.toString().any("."::contains) && !email.text.toString()
                        .any("@"::contains)
                ) {
                    text.text = "Email введён не коректно"
                }else{
                    prefs_fun(1)
                    DbManagerUser?.insertToDb(login.text.toString(),email.text.toString(),password.text.toString())
                    dialog.dismiss()
                }
            }
        }
        cancellation.setOnClickListener {
            db_Entrance()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(R.drawable.krujok)
    }

    fun prefs_fun(res:Int){
        val editor = prefs?.edit()
        editor?.putInt("Table",res)
        editor?.apply()
    }

    fun DB(){

        myDbManager?.openDb()
        Id = myDbManager!!.readDbDate("Id","0")
        Phone = myDbManager!!.readDbDate("Phone","0")
        PriceADS = myDbManager!!.readDbDate("PriceADS","0")
        Description = myDbManager!!.readDbDate("Description","0")
        COLUMN_titleADS = myDbManager!!.readDbDate("COLUMN_titleADS","0")
        AddImage = myDbManager!!.readDbDate("AddImage","0")
        AddImage_1 = myDbManager!!.readDbDate("AddImage_1","0")
        AddImage_2 = myDbManager!!.readDbDate("AddImage_2","0")
        AddImage_3 = myDbManager!!.readDbDate("AddImage_3","0")
        Time = myDbManager!!.readDbDate("Time","0")
        Viewing = myDbManager!!.readDbDate("Viewing","0")

        when(Id.size){
            0 ->myDbManager?.insertToDb("0","0","0","0","0","0","0","0", "0",
                "0")
        }

        var i = 100
        val ran = Id.size-1

        while (i != 0){

            binding.RVHOME.layoutManager = GridLayoutManager(thiscontext, 2)
            binding.RVHOME.adapter = adapter

            val ranD = (0..ran).random()
            i--

            if(AddImage[ranD] != "0"&& AddImage_1[ranD] == "0"&& AddImage_2[ranD] == "0"&&AddImage_3[ranD] =="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_1[ranD] != "0"&&AddImage[ranD] =="0"&&AddImage_2[ranD]=="0"&&AddImage_3[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            } else if (AddImage_2[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_3[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_2[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            } else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_3[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_2[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD], Time[ranD], Phone[ranD], Id[ranD],Viewing[ranD],AddImage[ranD],AddImage_1[ranD],AddImage_2[ranD],AddImage_3[ranD])
                adapter?.addCard(card)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu,inflater)
    }

    fun search(){

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {

                val list = myDbManager?.readDbDate("", p0.toString())
                Log.d("sadasdadsada",p0.toString())
                //adapter?.updateAdapter(list)
                return true
            }
        })

    }

    @SuppressLint("ResourceType")
    override fun onPrepareOptionsMenu(menu: Menu) {
        search = menu.findItem(R.id.app_bar_search)
        search
        super.onPrepareOptionsMenu(menu)
    }

    override fun onDestroy() {
        myDbManager?.closeDb()
        DbManagerUser?.closeDb()
        super.onDestroy()
    }
}