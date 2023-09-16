package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    //DECLARATION BLOCK
    var counter = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val firstWord = findViewById<TextView>(R.id.Answer1) //Displays the first word entered
        val secondWord = findViewById<TextView>(R.id.Answer2) //Displays the second word entered
        val thirdWord = findViewById<TextView>(R.id.Answer3) //Displays the third word entered

        val answ1 =
            findViewById<TextView>(R.id.letsee1) //displays correct/incorrect characters in "+,O,X" format
        val answ2 =
            findViewById<TextView>(R.id.letsee2) //displays correct/incorrect characters in "+,O,X" format
        val answ3 =
            findViewById<TextView>(R.id.letsee3) //displays correct/incorrect characters in "+,O,X" formatd

        val hiddenWord = findViewById<TextView>(R.id.SecretWord) //Displays Hidden random word

        val userinput = findViewById<EditText>(R.id.Inputbox) //gets the word entered by user
        val submitbtn = findViewById<Button>(R.id.Enterbtn) //submit button
        val resetbtn = findViewById<Button>(R.id.Reset) //Reset button

        /*============================Get 4 letter word========================================*/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }

        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber].uppercase()
        }

        val selectedWord = getRandomFourLetterWord() //Word the user needs to guess.

        /*=============================End of 4 letter word=====================================*/
        /*=============================Validation of correct word or letters======================*/
        fun checkGuess(guess: String): String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == selectedWord[i]) {
                    result += "O"
                } else if (guess[i] in selectedWord) {
                    result += "+"
                } else {
                    result += "X"
                }
            }
            return result
        }
        /*=============================End of Validation of correct word or letters===============*/
        resetbtn.visibility = View.INVISIBLE //hides reset button
        hiddenWord.visibility = View.INVISIBLE //hides reset button
        hiddenWord.setText(selectedWord) //displays secretword after game ends.

        submitbtn.setOnClickListener {
            if (counter == 1) {
                firstWord.setText(userinput.text) //displays user word
                answ1.setText(checkGuess(userinput.text.toString())) //displays user word
                val outp1 = checkGuess(userinput.text.toString())//return of secret word validation
                if (outp1 == "OOOO") //if the word equals the guessed word, user won!
                {
                    Toast.makeText(it.context, "You WON!!!", Toast.LENGTH_SHORT).show()
                    submitbtn.isEnabled = false
                    resetbtn.visibility = View.VISIBLE
                    hiddenWord.visibility = View.VISIBLE //hides reset button
                    resetbtn.setOnClickListener{
                        counter *=0  //reset counter to zero
                        submitbtn.isEnabled = true //enables submit button
                        userinput.getText().clear() //clears user input box
                        firstWord.setText("Answer") //clears guess box
                        answ1.setText("Let see") //clears answer box
                        resetbtn.visibility = View.INVISIBLE //hides reset button
                        hiddenWord.visibility = View.INVISIBLE //hides reset button
                        userinput.getText().clear()
                        return@setOnClickListener
                    }

                }else{
                    Toast.makeText(it.context, "Wrong!! NEXT TRY!", Toast.LENGTH_SHORT).show()
                    userinput.getText().clear()
                }
                counter++
                //Log.v("Hello World $counter ", "Button clicked!")
            } else if (counter == 2) {
                secondWord.setText(userinput.text) //displays user word
                answ2.setText(checkGuess(userinput.text.toString())) //displays user word
                val outp2 = checkGuess(userinput.text.toString())//return of secret word validation
                if (outp2 == "OOOO")//if the word equals the guessed word, user won!
                {
                    Toast.makeText(it.context, "You WON!!!", Toast.LENGTH_SHORT).show()
                    submitbtn.isEnabled = false
                    resetbtn.visibility = View.VISIBLE
                    hiddenWord.visibility = View.VISIBLE //hides reset button
                    resetbtn.setOnClickListener{
                        counter *=0  //reset counter to zero
                        submitbtn.isEnabled = true //enables submit button
                        userinput.getText().clear() //clears user input box
                        firstWord.setText("Answer") //clears guess box
                        answ1.setText("Let see") //clears answer box
                        secondWord.setText("Answer") //clears guess box
                        answ2.setText("Let see") //clears answer box
                        resetbtn.visibility = View.INVISIBLE //hides reset button
                        hiddenWord.visibility = View.INVISIBLE //hides reset button
                        return@setOnClickListener
                    }

                }else{
                    Toast.makeText(it.context, "Wrong!! NEXT TRY!", Toast.LENGTH_SHORT).show()
                    userinput.getText().clear()
                }
                counter++
            }else if (counter == 3) {
                thirdWord.setText(userinput.text) //displays user word
                answ3.setText(checkGuess(userinput.text.toString())) //displays user word
                val outp3 = checkGuess(userinput.text.toString()) //return of secret word validation
                if (outp3 == "OOOO")//if the word equals the guessed word, user won!
                {
                    Toast.makeText(it.context, "You WON!!!", Toast.LENGTH_SHORT).show()
                    submitbtn.isEnabled = false
                    resetbtn.visibility = View.VISIBLE
                    hiddenWord.visibility = View.VISIBLE //hides reset button
                    resetbtn.setOnClickListener{
                        counter *=0  //reset counter to zero
                        submitbtn.isEnabled = true //enables submit button
                        userinput.getText().clear() //clears user input box
                        firstWord.setText("Answer") //clears guess box
                        answ1.setText("Let see") //clears answer box
                        secondWord.setText("Answer") //clears guess box
                        answ2.setText("Let see") //clears answer box
                        thirdWord.setText("Answer") //clears guess box
                        answ3.setText("Let see") //clears answer box
                        resetbtn.visibility = View.INVISIBLE //hides reset button
                        hiddenWord.visibility = View.INVISIBLE //hides reset button
                        userinput.getText().clear()
                        return@setOnClickListener
                    }


                }else{
                    Toast.makeText(it.context, "Wrong!! You have Exceeded the number of tries!", Toast.LENGTH_SHORT).show()
                    submitbtn.isEnabled = false
                    resetbtn.visibility = View.VISIBLE
                    hiddenWord.visibility = View.VISIBLE //hides reset button
                    resetbtn.setOnClickListener{
                        counter *=0  //reset counter to zero
                        submitbtn.isEnabled = true //enables submit button
                        userinput.getText().clear() //clears user input box
                        firstWord.setText("Answer") //clears guess box
                        answ1.setText("Let see") //clears answer box
                        secondWord.setText("Answer") //clears guess box
                        answ2.setText("Let see") //clears answer box
                        thirdWord.setText("Answer") //clears guess box
                        answ3.setText("Let see") //clears answer box
                        resetbtn.visibility = View.INVISIBLE //hides reset button
                        hiddenWord.visibility = View.INVISIBLE //hides reset button
                        userinput.getText().clear()
                        return@setOnClickListener
                }
            }
            }

        }
    }}


        //================================================
