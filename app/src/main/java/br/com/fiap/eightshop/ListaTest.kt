package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityCompanyBinding
import br.com.fiap.eightshop.databinding.ActivityListaTestBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyPresenter
import br.com.fiap.eightshop.ui.company.MyListAdapter
import kotlinx.android.synthetic.main.activity_lista_test.*

class ListaTest : AppCompatActivity(), CompanyContract.CompanView{

    private lateinit var binding: ActivityListaTestBinding
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    val language = arrayOf<String>(
        "C",
        "C++",
        "Java",
        ".Net",
        "Kotlin",
        "Ruby",
        "Rails",
        "Python",
        "Java Script",
        "Php",
        "Ajax",
        "Perl",
        "Hadoop"
    )
    val description = arrayOf<String>(
        "C programming is considered as the base for other programming languages",
        "C++ is an object-oriented programming language.",
        "Java is a programming language and a platform.",
        ".NET is a framework which is used to develop software applications.",
        "Kotlin is a open-source programming language, used to develop Android apps and much more.",
        "Ruby is an open-source and fully object-oriented programming language.",
        "Ruby on Rails is a server-side web application development framework written in Ruby language.",
        "Python is interpreted scripting  and object-oriented programming language.",
        "JavaScript is an object-based scripting language.",
        "PHP is an interpreted language, i.e., there is no need for compilation.",
        "AJAX allows you to send and receive data asynchronously without reloading the web page.",
        "Perl is a cross-platform environment used to create network and server-side applications.",
        "Hadoop is an open source framework from Apache written in Java."
    )

    val imageId = arrayOf<Int>(
        R.drawable.common_full_open_on_phone,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal,
        R.drawable.common_google_signin_btn_icon_dark_normal
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        companyPresenter = CompanyPresenter(this)
        companyPresenter.listCompanies()

        binding.listView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(
                this,
                "Click on item at $itemAtPos its item id $itemIdAtPos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun mostrarDados(companies: List<Company>?) {

        val myListAdapter = MyListAdapter(this, language, companies)
        binding.listView.adapter = myListAdapter

    }

    override fun mostrarErro(mensagem: String) {
        TODO("Not yet implemented")
    }
}
//}
//
//
//
//
//
//
//private lateinit var binding: ActivityCompanyBinding
//    private lateinit var companyPresenter: CompanyContract.CompanyPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityCompanyBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        companyPresenter = CompanyPresenter(this)
//        loadData()
//    }
//
//    fun loadData() {
//        companyPresenter.listCompanies()
//    }
//
//    override fun mostrarDados(company: List<Company>?) {
//        binding.txt1.text = company?.get(0)?.companyName
//        binding.txt2.text = company?.get(0)?.urlImage
//    }
//
//    override fun mostrarErro(mensagem: String) {
//        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
//    }
//}