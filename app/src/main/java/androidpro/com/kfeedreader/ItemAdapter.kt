package androidpro.com.kfeedreader

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(val list: ArrayList<Item>, val context: Context): RecyclerView.Adapter<ItemAdapter.ItemVielHolder>() {

    class ItemVielHolder(view: View): RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById(R.id.titulo) as TextView
        val autor = view.findViewById(R.id.autor) as TextView
        val data = view.findViewById(R.id.data) as TextView
        val imagem: ImageView = view.findViewById(R.id.imagem)
        val botao = view.findViewById<Button>(R.id.btnVerMais)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemVielHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_list, p0, false)
        return ItemVielHolder(v)
    }

    override fun onBindViewHolder(holder: ItemVielHolder, p1: Int) {
        holder?.titulo?.text = list[p1].titulo
        holder?.autor?.text = list[p1].autor
        holder?.data?.text = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date(list[p1].data))

        holder?.botao?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[p1].link)
            context.startActivity(intent)
        }

        DownaloadImageTask(holder?.imagem!!).execute(list[p1].imagem)
    }

    override fun getItemCount(): Int = list.size

}