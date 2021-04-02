package androidpro.com.kfeedreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemVielHolder>
    val listItem = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)
        listView = findViewById(R.id.recyclerview)
        listView.layoutManager = layout
        adapter = ItemAdapter(listItem, this)
        listView.adapter = adapter

        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItem.clear()

        newArticles?.mapTo(listItem) {
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

        //adapter = ItemAdapter(listItem, this)
        adapter.notifyDataSetChanged()
    }

    override fun onPreload() {
    }

    override fun onLoadFailed() {
    }

}
