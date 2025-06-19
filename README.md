Playlist Manager
1. Purpose of the App
The purpose of Playlist Manager is to assist users in storing song details and allows the user to create and manage playlists. Users will be able to rate songs, add comments about the song (it’s genre to be specific), as well as input information about the artists.
2. Design Considerations
Simplicity: The app has a user-friendly design that is easy to navigate and understand. Just press “Add to playlist” and the app will require you to input the song, artist, rating, as well as comments (the type of song it is). After inserting the required information, press “Add to playlist once more and the song will be entered to the desired playlist. On the second screen, you will be able to display the list of songs the user has entered by pressing “Display list of songs” and to view the average rating, simply press “Average rating”. To go back to the main screen, press “Return to main screen” and then to exit the app, you will have to be on the main screen. Press “Exit” and the app will shut down.
Consistency: In each screen, the background will remain the same as well as the buttons, the font and the name of the app (this also includes the sizes of these things)
Readability: The app has a clutter-free design so that the user can clearly see what is happening and should be able to read the text with ease and has a font size that can accommodate everyone.
Feedback: Every button that you press is guaranteed to give you a response to what is happening. If you didn’t enter anything in the first screen, then the app will tell you “Please fill in all the required fields”. If you enter a number below 0 for ratings, then the app will ask you to “Please enter a valid rating”. If everything is filled in properly, then the app will tell you that the song has been added. Same thing applies to the second screen, if the user doesn’t fill everything required, then the app will tell what to do or if the user does something that is required, the app will tell you what is going to happen
Placement: The buttons are placed at the bottom of the screen so that users can use just their thumb to click. The written texts, except for the app name, is placed on the centre of the screen as it is the focus. The name of the app is placed at the top of every screen so that if a user shows the app to someone who doesn’t know about the app, that person will be able to see it.
3. Link to GitHub
https://github.com/ST10491892/Practicum.git 
4. The Design and Code of the app
First screen: This where the user will enter the required information to compile a playlist. The user must press “Add to Playlist after filling in the necessary information. To view the playlist, the user must press “View playlist” and to exit, press “Exit”.
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
 
Second Screen: This is where the playlist will be displayed (in the text view) 
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

 
