package ni.edu.uca.listadoprod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.listadoprod.dataadapter.ProductoAdapter
import ni.edu.uca.listadoprod.databinding.ActivityMainBinding
import ni.edu.uca.listadoprod.dataclass.Producto

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productoAdapter: ProductoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciar()
    }

    fun limpiar() {
        with(binding) {
            etID.text.clear()
            etNombreProd.text.clear()
            etPrecio.text.clear()
            etID.requestFocus()
        }
    }

    private fun agregarProd() {
        with(binding) {
            try {
                val id: Int = etID.text.toString().toInt()
                val nombre: String = etNombreProd.text.toString()
                val precio: Double = etPrecio.text.toString().toDouble()
                val prod = Producto(id, nombre, precio)
                productoAdapter.addProducto(prod)
            } catch (ex: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${ex.toString()} ",
                    Toast.LENGTH_LONG).show()
            }
            limpiar()
        }
    }

    private fun iniciar() {
        productoAdapter = ProductoAdapter(mutableListOf(), binding)
        binding.rcvLista.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rcvLista.adapter = productoAdapter
        binding.btnAgregar.setOnClickListener {
            agregarProd()
        }

        binding.btnLimpiar.setOnClickListener {
            limpiar()
        }
    }
}