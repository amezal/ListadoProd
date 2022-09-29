package ni.edu.uca.listadoprod.dataadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.listadoprod.databinding.ActivityMainBinding
import ni.edu.uca.listadoprod.databinding.ItemlistaBinding
import ni.edu.uca.listadoprod.dataclass.Producto

class ProductoAdapter(var listProd: MutableList<Producto>, val parent: ActivityMainBinding) :
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>() {
        inner class ProductoHolder(val binding: ItemlistaBinding) :
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        val binding = ItemlistaBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )

        return ProductoHolder(binding)
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

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        val producto = listProd[position]
        with(holder.binding) {
            tvCodProd.text = producto.id.toString()
            tvNombreProd.text = producto.nombre
            tvPrecioProd.text = producto.precio.toString()

            cvProducto.setOnClickListener() {
                cargarDatos(producto)
            }

            btnEliminar.setOnClickListener() {
                eliminarProducto(position)
            }
        }
    }

    override fun getItemCount(): Int = listProd.size
}