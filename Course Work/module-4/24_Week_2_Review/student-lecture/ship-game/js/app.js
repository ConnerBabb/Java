/*
* Add Events and functions to move the Ship and Win the game
*/
// Creates the Grid -- this should only occur after the DOM loads
createGrid();

/**
 * Reset the Game
 */
function resetGame() {

}

/**
 * Creates the game grid
 */
function createGrid() {

    // Add Code to create the game grid
    for (let i = 0; i < 10 ; i++) {
        buildRow(frame); 
    }
    resetGame();
}

/**
 * Builds the grid rows
 * @param {element} frame 
 */
function buildRow(frame) {
   // Add code to create rows
    for (let i = 0; i < 10 ; i++) {
        buildSquare(row, i); 
    }    
}

/**
 * Builds the grid squares 
 * @param {element} row 
 * @param {int} count 
 */
function buildSquare(row, count) {
   // Add code to create the grid Squares

    // Randomly creates pirates, but not on the first elemment
    if (count > 1) {
        if (rollDie(100) <= 15) {
            // Add the pirates here
        } 
    }
    
}

/**
 * Rolls a fair die 
 * 
 * @param {int} sides 
 * @returns {int} value in the range 1 <= x <= sides
 */
function rollDie(sides){
    let result = Math.floor(Math.random() * sides) + 1;
    return result;
}

/**
  *Utiltiy function to demonstrate rollDie
 * Display to console the result of rolling the
 * die enought to get about 100 values from 
 * each side of the die. User console to run 
 * and see results.
 * Not called for this application
 * 
 * @param {int} sides 
 * 
 */
function testRollDie(sides){
    let result = [];
    for (let i = 0; i <= sides; i++){
        result[i] = 0;
    }
    for (let i = 0; i < sides*100; i++){
        result[rollDie(sides)] +=1;
    }
    console.table(result);
}



