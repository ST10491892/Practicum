package vcmsa.ci.musicplaylistmanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {

    private var songs = arrayListOf<String>()
    private var artist = arrayListOf<String>()
    private var ratings = arrayListOf<Int>()
    private var comments = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val listButton = findViewById<TextView>(R.id.listButton)
        val averageButton = findViewById<Button>(R.id.averageButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val displayText = findViewById<TextView>(R.id.displayText)

        // Safely retrieve arrays
        intent.getStringArrayListExtra("songs")?.let { songs = it }
        intent.getStringArrayListExtra("artist")?.let { artist = it }
        intent.getIntegerArrayListExtra("ratings")?.let { ratings = it }
        intent.getStringArrayListExtra("comments")?.let { comments = it }

        if (songs.isEmpty() || artist.isEmpty() ||ratings.isEmpty()) {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show()
        }

        averageButton.setOnClickListener {
            val listText = StringBuilder("Full Playlist:\n\n")
            for (i in songs.indices) {
                listText.append("Song: ${songs[i]}\n")
                listText.append("Artist: ${artist[i]}\n")
                listText.append("Rating: ${ratings[i]}\n")
                listText.append("Comments: ${comments[i]}\n\n")
            }
            listButton.text = listText.toString()
        }

        averageButton.setOnClickListener {
            averageButton.text = filterHighQuantityItems()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    // Show songs with ratings >= 5
    private fun filterHighQuantityItems(): String {
        val result = StringBuilder("Songs with Rating >= 5:\n\n")
        for (i in songs.indices) {
            if (ratings[i] >= 5) {
                result.append("${songs[i]} (Rating: ${ratings[i]})\n")
            }
        }
        return result.toString()
    }
}
