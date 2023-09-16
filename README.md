# Wordle
WORDLE GAME
After 3 guesses, user should no longer be able to submit another guess
This could be accomplished in different ways. For example, you can:
Show a Toast telling the user they've exceeded their number of guesses
Gray out and disable the 'Submit' button after 3 guesses have been made
Show a 'Reset' button instead of a 'Submit' button after 3 guesses have been made
After each guess, user sees the "correctness" of the guess
This includes which letters are in the target word, and which letters are also in the correct position in the target word.
To display the 'correctness' of a guess, one simple option is to generate a string:
'O' can represent the right letter in the right place
'+' can represent the right letter in the wrong place
'X' can represent a letter not in the target word
For example, if the target word is "STAR", and the user guessed 'SAIL', the string generated would be "O+XX".
