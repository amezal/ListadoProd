package ni.edu.uca.listadoprod.dataadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.listadoprod.databinding.ActivityMainBinding
import ni.edu.uca.listadoprod.databinding.ItemlistaBinding
import ni.edu.uca.listadoprod.dataclass.Producto

class ProductoAdapter(var listProd: MutableList<Producto>, val parent: ActivityMainBinding,) :
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>() {
    private var holders: MutableList<ProductoHolder> = mutableListOf()
        inner class ProductoHolder(val binding: ItemlistaBinding) :
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        val binding = ItemlistaBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        val holder = ProductoHolder(binding)
        holders.add(holder)
        return holder
    }

    fun addProducto(producto: Producto) {
        listProd.add(producto)
        notifyItemInserted(listProd.size - 1)
        Log.d("CREATION", listProd.toString())
    }

    fun cargarDatos(producto: Producto) {
        parent.etID.setText(producto.id.toString())
        parent.etNombreProd.setText(producto.nombre)
        parent.etPrecio.setText(producto.precio.toString())
    }

    fun eliminarProducto(position: Int) {
        listProd.removeAt(position)
        notifyItemRemoved(position)
    }

    fun guardarProducto(position: Int) {
        try {
            val id: Int = parent.etID.text.toString().toInt()
            val nombre: String = parent.etNombreProd.text.toString()
            val precio: Double = parent.etPrecio.text.toString().toDouble()
            val producto = Producto(id, nombre, precio)
            listProd[position] = producto
            notifyItemChanged(position)
            parent.etID.text.clear()
            parent.etNombreProd.text.clear()
            parent.etPrecio.text.clear()
        } catch (ex: Exception) {
            Toast.makeText(parent.rcvLista.context, "Revise los campos a ingresar",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        val producto = listProd[position]
        with(holder.binding) {
            tvCodProd.text = producto.id.toString()
            tvNombreProd.text = producto.nombre
            tvPrecioProd.text = producto.precio.toString()

            cvProducto.setOnClickListener() {
                for(holder in holders) {
                    if (holder.adapterPosition != position) {
                        holder.binding.btnGuardar.visibility = View.INVISIBLE
                    }
                }
                cargarDatos(producto)
                btnGuardar.visibility = View.VISIBLE
            }

            btnEliminar.setOnClickListener() {
                eliminarProducto(position)
            }

            btnGuardar.setOnClickListener() {
                guardarProducto(position)
                btnGuardar.visibility = View.INVISIBLE
                btnEliminar.visibility = View.VISIBLE
            }
        }
    }


    override fun getItemCount(): Int = listProd.size
}