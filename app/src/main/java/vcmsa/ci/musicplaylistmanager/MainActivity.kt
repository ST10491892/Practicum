package vcmsa.ci.musicplaylistmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Parallel arrays
    val song = arrayListOf<String>()
    val artists = arrayListOf<String>()
    val ratings = arrayListOf<Int>()
    val comments = arrayListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songInput = findViewById<EditText>(R.id.songInput)
        val artistInput = findViewById<EditText>(R.id.artistsInput)
        val ratingsInput = findViewById<EditText>(R.id.ratingInput)
        val commentsInput = findViewById<EditText>(R.id.commentInput)
        val addButton = findViewById<Button>(R.id.addButton)
        val viewButton = findViewById<Button>(R.id.nextButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            val song = songInput.text.toString().trim()
            val artists = artistInput.text.toString().trim()
            val rating = ratingsInput.text.toString().trim()
            val comment = commentsInput.text.toString().trim()

            if (song.isEmpty() || artists.isEmpty() || ratings.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ratings = rating.toIntOrNull()
            if (ratings == null || ratings < 0) {
                Toast.makeText(this, "Please enter a valid rating.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add to Playlists
            song.add(song)
            artists.add(artists)
            rating.add(rating)
            comments.add(comment)

            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()

            // Optionally clear fields
            songInput.text.clear()
            artistInput.text.clear()
            ratingsInput.text.clear()
            commentsInput.text.clear()
        }

        viewButton.setOnClickListener {
            val intent = Intent(this, ViewActivity::class.java)
            intent.putStringArrayListExtra("song", song)
            intent.putStringArrayListExtra("artist", artists)
            intent.putIntegerArrayListExtra("ratings", ArrayList(ratings))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity() // Close all activities
        }
    }
}
